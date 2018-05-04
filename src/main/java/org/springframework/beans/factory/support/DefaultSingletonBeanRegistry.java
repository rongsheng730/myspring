package org.springframework.beans.factory.support;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.core.SimpleAliasRegistry;

/**
 * TODO
 *
 * @author rongsheng
 * @date 2018/4/23下午5:27
 */
public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    /** Logger available to subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 保存BeanName和创建bean实例之间的关系，bean name --> bean instance
     * Cache of singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);

    /**
     * 保存BeanName和创建bean的工厂之间的关系，bean name --> ObjectFactory
     * Cache of singleton factories: bean name --> ObjectFactory
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>(16);

    /**
     * 保存BeanName和创建bean实例之间的关系，与singletonObjects的不同之处在于:
     *   当一个单例bean被放到这里面后，那么当bean还在创建过程中，就可以通过getBean方法获取到了，
     *   其目的是用来检测循环引用。
     * Cache of early singleton objects: bean name --> bean instance
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    /**
     * 保存当前所有已注册的bean
     * Set of registered singletons, containing the bean names in registration order
     */
    private final Set<String> registeredSingletons = new LinkedHashSet<String>(64);

    /**
     * 标识指定name的Bean对象是否处于创建状态
     * Names of beans that are currently in creation (using a ConcurrentHashMap as a Set)
     */
    private final Map<String, Boolean> singletonsCurrentlyInCreation = new ConcurrentHashMap<String, Boolean>(16);

    /**
     * Return the (raw) singleton object registered under the given name.
     * <p>Checks already instantiated singletons and also allows for an early
     * reference to a currently created singleton (resolving a circular reference).
     * @param beanName the name of the bean to look for
     * @param allowEarlyReference whether early references should be created or not
     * @return the registered singleton object, or {@code null} if none found
     */
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return (singletonObject != NULL_OBJECT ? singletonObject : null);
    }

    /**
     * Return whether the specified singleton bean is currently in creation
     * (within the entire factory).
     * @param beanName the name of the bean
     */
    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.containsKey(beanName);
    }
}
