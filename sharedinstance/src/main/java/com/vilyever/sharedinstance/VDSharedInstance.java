package com.vilyever.sharedinstance;

import java.util.ArrayList;
import java.util.List;

/**
 * VDSharedInstance
 * AndroidSharedInstance <com.vilyever.sharedinstance>
 * Created by vilyever on 2015/9/6.
 * Feature:
 */
public class VDSharedInstance<T> {
    private final VDSharedInstance self = this;

    final Class<T> instanceClazz;

    private static List Instances = new ArrayList();

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
        for (int i = 0; i < Instances.size(); i++) {
            Object instance = Instances.get(i);
            if (instance.getClass() == self.instanceClazz) {
                return (T) instance;
            }
        }

        T instance;
        try {
            instance = (T) self.instanceClazz.newInstance();
            Instances.add(instance);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return instance;
    }

    public synchronized void destoryInstance() {
        for (int i = 0; i < Instances.size(); i++) {
            Object instance = Instances.get(i);
            if (instance.getClass() == self.instanceClazz) {
                Instances.remove(instance);
                break;
            }
        }
    }

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}