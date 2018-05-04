package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * TODO
 *
 * @author rongsheng
 * @date 2018/4/24上午11:05
 */
public interface ObjectFactory<T> {

    /**
     * Return an instance (possibly shared or independent)
     * of the object managed by this factory.
     * @return an instance of the bean (should never be {@code null})
     * @throws BeansException in case of creation errors
     */
    T getObject() throws BeansException;

}
