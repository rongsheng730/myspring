package org.springframework.beans.factory.support;

import java.io.Serializable;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * TODO
 *
 * @author rongsheng
 * @date 2018/4/23下午5:13
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, Serializable {

    public Object getBean(String name) {
        return null;
    }
}
