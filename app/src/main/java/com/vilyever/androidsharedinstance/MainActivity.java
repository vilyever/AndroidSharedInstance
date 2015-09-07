package com.vilyever.androidsharedinstance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.vilyever.sharedinstance.VDSharedInstance;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("onCreate " + MainActivity.class.getName());

        Carrier carrier = new Carrier();

        System.out.println("identifier " + new VDSharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new VDSharedInstance<>(TestClass.class).getInstance().number);
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("onResume");

        System.out.println("identifier " + new VDSharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new VDSharedInstance<>(TestClass.class).getInstance().number);
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("onPause");

        System.out.println("identifier " + new VDSharedInstance<>(TestClass.class).getInstance().identifier);
        System.out.println("number " + new VDSharedInstance<>(TestClass.class).getInstance().number);

        new VDSharedInstance<>(TestClass.class).destoryInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class Carrier {
        public Carrier() {
            TestClass tc = new VDSharedInstance<>(TestClass.class).getInstance(new VDSharedInstance.InitialDelegate<TestClass>() {
                @Override
                public void instanceDidInitial(TestClass instance) {
                    System.out.println("instanceDidInitial");
                }
            });
            tc.identifier = "carrier";
            tc.number = 88;
        }
    }
}
