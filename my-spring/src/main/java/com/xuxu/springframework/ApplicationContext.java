package com.xuxu.springframework;

import cn.hutool.core.util.StrUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @auther xuxu
 * @date 2022/6/15
 */
public class ApplicationContext implements BeanFactory {

	private Class<?> configClazz;
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
	private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

	public ApplicationContext(Class<?> configClazz) {
		this.configClazz = configClazz;

		//扫描所有的beandefinition
		scan(configClazz);

		//生成singleton对象
		createBean();
	}

	public void scan(Class<?> configClazz) {
		if (!configClazz.isAnnotationPresent(ComponentScan.class)) {
			return;
		}

		ComponentScan componentScan = configClazz.getAnnotation(ComponentScan.class);
		String scanPath = componentScan.value();
		scanPath = scanPath.replace(".", "/");

		//如何扫描呢?
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL url = classLoader.getResource(scanPath);
		File file = new File(url.getFile());

		//只作两层遍历
		List<File> clazzFile = new ArrayList<>();
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.isDirectory()) {
					for (File f1 : f.listFiles()) {
						if (!f1.isDirectory()) {
							clazzFile.add(f1);
						}
					}
				} else {
					clazzFile.add(f);
				}
			}
		}

		clazzFile.forEach(clzFile -> {
			String absolutePath = clzFile.getAbsolutePath();
			String className = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"))
					.replace("\\", ".");

			try {
				Class<?> aClass = classLoader.loadClass(className);
				if (!aClass.isAnnotationPresent(Component.class)) {
					return;
				}
				Component component = aClass.getAnnotation(Component.class);
				String beanName = component.value();
				BeanDefinition beanDefinition = new BeanDefinition();
				beanDefinition.setClazzObject(aClass);
				beanDefinition.setLazy(aClass.isAnnotationPresent(Lazy.class));
				if (aClass.isAnnotationPresent(Scope.class)) {
					beanDefinition.setScope(aClass.getAnnotation(Scope.class).value());
				} else {
					beanDefinition.setScope(BeanDefinition.Scope.SCOPE_SINGLE);
				}

				if (StrUtil.isEmpty(beanName)) {
					beanName = decapitalize(aClass.getSimpleName());
				}

				beanDefinitionMap.put(beanName, beanDefinition);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}

		});

	}

	public static String decapitalize(String name) {
		if (name == null || name.length() == 0) {
			return name;
		}
		if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) &&
				Character.isUpperCase(name.charAt(0))) {
			return name;
		}
		char[] chars = name.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return new String(chars);
	}

	public void createBean() {
		this.beanDefinitionMap.forEach((beanName, beanDefinition) -> {
			if (!beanDefinition.isLazy() && beanDefinition.getScope().equals(BeanDefinition.Scope.SCOPE_SINGLE)) {
				Object bean = doCreateBean(beanName, beanDefinition);
				this.singletonObjects.put(beanName, bean);
			}
		});
	}

	//最核心的方法，包括：7大核心流程
	//1、根据BeanDefinition，推断构造函数，创建bean对象
	//2、根据autowired、resource注解，依赖注入
	//3、根据对象是否实现Aware接口，执行aware回调
	//4、初始化前
	//5、初始化中
	//6、初始化后
	//7、AOP
	public Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Class<?> clazzObject = (Class<?>) beanDefinition.getClazzObject();
		try {
			//步骤1，省略构造方法推断
			Object o = clazzObject.newInstance();

			//步骤2，依赖注入
			Field[] declaredFields = clazzObject.getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.isAnnotationPresent(Autowired.class)) {
					Object bean = getBean(field.getName());
					field.setAccessible(true);
					field.set(o, bean);
				}
			}

			//步骤3，aware回调
			if (o instanceof BeanNameAware) {
				((BeanNameAware) o).setBeanName(beanName);
			}

			//步骤4，初始化前
			Method[] declaredMethods = clazzObject.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (method.isAnnotationPresent(PostConstructor.class)) {
					method.setAccessible(true);
					method.invoke(o);
				}
			}

			//步骤5，初始化中
			//类似，步骤3，跳过

			//步骤6，初始化后
			//类似，步骤3，跳过

			//步骤7，AOP
			if (clazzObject.isAnnotationPresent(Transactional.class)) {
				Enhancer enhancer = new Enhancer();
				enhancer.setSuperclass(clazzObject);
				Object target = o;
				enhancer.setCallback((MethodInterceptor) (proxy, method, objects, methodProxy) -> {
					System.out.println("开启事务");
					Object result = method.invoke(target, objects);
					System.out.println("提交事务");
					return result;
				});
				o = enhancer.create();
			}

			return o;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object getBean(String name) throws Exception {

		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if (beanDefinition == null) {
			throw new Exception("not find beanDefinition");
		}

		if (beanDefinition.getScope().equals(BeanDefinition.Scope.SCOPE_SINGLE)) {
			Object o = this.singletonObjects.get(name);
			//为什么这里有可能找不到呢？
			//lazy加载
			if (o == null) {
				o = doCreateBean(name, beanDefinition);
				singletonObjects.put(name, o);
			}
			return o;
		}

		return doCreateBean(name, beanDefinition);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getBean(Class<T> requiredType) {
		String beanName = decapitalize(requiredType.getSimpleName());

		try {
			return (T) getBean(beanName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
