

# 手写spring底层核心组件

- 扫描路径下标注了@Component、@Service的class类，生成BeanDefinition对象；
- 生成Bean对象；
- autowired依赖注入；
- Aware
- PostContructor，BeanPostProcessor.before，初始化前
- InitialingBean，初始化中
- BeanPostProcessor.after，初始化后
- AOP

# 扫描功能点，生成BeanDefinition
