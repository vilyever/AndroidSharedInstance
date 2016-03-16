package com.vilyever.androidsharedinstance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vilyever.sharedinstance.SharedInstance;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("onCreate " + MainActivity.class.getName());

        Carrier carrier = new Carrier();

        System.out.println("identifier " + new SharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new SharedInstance<>(TestClass.class).getInstance().number);
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("onResume");

        System.out.println("identifier " + new SharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new SharedInstance<>(TestClass.class).getInstance().number);
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("onPause");

        System.out.println("identifier " + new SharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new SharedInstance<>(TestClass.class).getInstance().number);

        new SharedInstance<>(TestClass.class).destoryInstance();
    }

    public static class Carrier {
        public Carrier() {
            TestClass tc = new SharedInstance<>(TestClass.class).getInstance(new SharedInstance.InitDelegate<TestClass>() {
                @Override
                public void onInit(TestClass instance) {
                    System.out.println("onInit");
                }
            });
            tc.identifier = "carrier";
            tc.number = 88;
        }
    }
}
