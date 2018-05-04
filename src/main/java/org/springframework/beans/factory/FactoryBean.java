package org.springframework.beans.factory;

/**
 * TODO
 *
 * @author rongsheng
 * @date 2018/4/24上午11:48
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
