package org.springframework.beans.factory;

/**
 *
 * @author rongsheng
 * @date 2018/4/23下午4:43
 */
public interface BeanFactory {

    Object getBean(String name);

    <T> T getBean(Class<T> requiredType);

}
