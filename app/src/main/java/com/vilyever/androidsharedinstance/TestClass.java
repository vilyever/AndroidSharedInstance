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
    @SharedInstance.VDInstanceInitial
    private void initial() {
        System.out.println("VDInstanceInitial");
    }
    
    /* #Public Methods */

    /* #Classes */

    /* #Interfaces */     
     
    /* #Annotations @interface */    
    
    /* #Enums */
}