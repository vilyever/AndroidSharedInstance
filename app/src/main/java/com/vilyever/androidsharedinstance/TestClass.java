package com.vilyever.androidsharedinstance;

import com.vilyever.sharedinstance.SharedInstance;

/**
 * TestClass
 * AndroidSharedInstance <com.vilyever.androidsharedinstance>
 * Created by vilyever on 2015/9/6.
 * Feature:
 */
public class TestClass {
    private final TestClass self = this;

    public String identifier;
    public int number;
    
    /* #Constructors */    
    
    /* #Overrides */    
    
    /* #Accessors */     
     
    /* #Delegates */     
     
    /* #Private Methods */
    @SharedInstance.InstanceInit
    private void init() {
        System.out.println("InstanceInit");
    }
    
    /* #Public Methods */

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}