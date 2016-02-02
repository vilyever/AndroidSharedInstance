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

    /* Constructors */
    public VDSharedInstance(Class<T> instanceClazz) {
        this.instanceClazz = instanceClazz;
    }

    /* Public Methods */
    /** @see #getInstance(InitialDelegate) */
    public synchronized T getInstance() {
        return getInstance(null);
    }

    /**
     * 获取单例
     * 若单例此时生成：
     * 1.单例Class内声明了带有{@link com.vilyever.sharedinstance.VDSharedInstance.VDInstanceInitial}的方法，此方法将被调用
     * 2.初始化回调不为空，调用初始化回调
     * @param delegate 初始化回调
     * @return 单例
     */
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
                delegate.requireInitial(instance);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return instance;
    }

    /** @see #setInstance(Object, boolean) */
    public synchronized T setInstance(T instance) {
        return setInstance(instance, false);
    }

    /**
     * 设置单例
     * @param instance 单例
     * @param forceReplace 是否强制替换，若原先存在单例判断是否替换
     * @return 泛型T对应的单例，可能不是传入的单例
     */
    public synchronized T setInstance(T instance, boolean forceReplace) {
        if (!Instances.containsKey(self.instanceClazz.getName())
                || forceReplace) {
            Instances.put(self.instanceClazz.getName(), instance);
        }

        return (T) Instances.get(self.instanceClazz.getName());
    }

    /**
     * 销毁单例
     * 此处仅移除关联
     * 若其他位置仍持有该单例，则该单例可被gc回收的时机由其他位置决定
     */
    public synchronized void destoryInstance() {
        if (Instances.containsKey(self.instanceClazz.getName())) {
            Instances.remove(self.instanceClazz.getName());
        }
    }

    /* Interfaces */
    public interface InitialDelegate<T> {
        void requireInitial(T instance);
    }

    /* Annotations @interface */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface VDInstanceInitial {
    }
}