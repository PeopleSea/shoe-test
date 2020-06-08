package shoes.services;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.context = applicationContext;
	}

//  獲取applicationContext
	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static void clearHolder() {
		context = null;
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return context.getBean(name, requiredType);
	}

	private static void checkApplicationContext() {
		if (context == null) {
			throw new IllegalStateException("applicaitonContext未注入,請在applicationContext.xml中定義SpringContextUtil");
		}
	}

	public static synchronized void setBean(String beanName, Class<?> clazz, Map<String, Object> original) {
		checkApplicationContext();
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context
				.getAutowireCapableBeanFactory();
		if (beanFactory.containsBean(beanName)) {
			return;
		}
		// BeanDefinition beanDefinition = new RootBeanDefinition(clazz);
		GenericBeanDefinition definition = new GenericBeanDefinition();
		// 類class
		definition.setBeanClass(clazz);
		// 屬性賦值
		definition.setPropertyValues(new MutablePropertyValues(original));
		// 註冊到spring上下文
		beanFactory.registerBeanDefinition(beanName, definition);
	}

}
