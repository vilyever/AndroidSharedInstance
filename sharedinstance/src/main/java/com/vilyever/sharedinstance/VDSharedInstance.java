package com.vilyever.sharedinstance;

import com.vilyever.reflectkit.VDReflectKit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * VDSharedInstance
 * AndroidSharedInstance <com.vilyever.sharedinstance>
 * Created by vilyever on 2015/9/6.
 * Feature:
 */
public class VDSharedInstance<T> {
    private final VDSharedInstance self = this;

    final Class<T> instanceClazz;

    private static Map<String, Object> Instances = new HashMap<>();

    /* #Constructors */
    public VDSharedInstance(Class<T> instanceClazz) {
        this.instanceClazz = instanceClazz;
    }

    /* #Overrides */

    /* #Accessors */

    /* #Delegates */

    /* #Private Methods */

    /* #Public Methods */
    public synchronized T getInstance() {
        return getInstance(null);
    }

    public synchronized T getInstance(InitialDelegate delegate) {
        if (Instances.containsKey(self.instanceClazz.getName())) {
            return (T) Instances.get(self.instanceClazz.getName());
        }

        T instance;
        try {
            instance = (T) self.instanceClazz.newInstance();
            Instances.put(self.instanceClazz.getName(), instance);

            ArrayList<Method> methods = VDReflectKit.getMethods(self.instanceClazz);
            for (Method method : methods) {
                if (method.getAnnotation(VDInstanceInitial.class) != null) {
                    method.setAccessible(true);
                    method.invoke(instance);
                    break;
                }
            }

            if (delegate != null) {
                delegate.instanceDidInitial(instance);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return instance;
    }

    public synchronized T setInstance(T instance) {
        return setInstance(instance, false);
    }

    public synchronized T setInstance(T instance, boolean forceReplace) {
        if (!Instances.containsKey(self.instanceClazz.getName())
                || forceReplace) {
            Instances.put(self.instanceClazz.getName(), instance);
        }

        return (T) Instances.get(self.instanceClazz.getName());
    }

    public synchronized void destoryInstance() {
        if (Instances.containsKey(self.instanceClazz.getName())) {
            Instances.remove(self.instanceClazz.getName());
        }
    }

    /* #Classes */

    /* #Interfaces */
    public interface InitialDelegate<T> {
        void instanceDidInitial(T instance);
    }

    /* #Annotations @interface */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface VDInstanceInitial {
    }

    /* #Enums */
}