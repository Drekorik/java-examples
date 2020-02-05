package org.fireplume.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.stream.Stream;

@Component
public class CustomBPP implements BeanPostProcessor {
    private static final HashMap<String, Class> MAP = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (Stream.of(bean.getClass().getDeclaredFields())
                .anyMatch(field -> field.isAnnotationPresent(CustomAnnotation.class))) {
            MAP.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (MAP.containsKey(beanName)) {
            Stream.of(MAP.get(beanName).getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(CustomAnnotation.class))
                    .forEach(field -> {
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, bean, field.getDeclaredAnnotation(CustomAnnotation.class).value());
                        field.setAccessible(false);
                    });
        }
        return bean;
    }
}
