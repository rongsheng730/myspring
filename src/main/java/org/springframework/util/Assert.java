package org.springframework.util;

/**
 * TODO
 *
 * @author rongsheng
 * @date 2018/4/23下午5:36
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object) {
        notNull(object, "[Assertion failed] - this argument is required; it must not be null");
    }

}
