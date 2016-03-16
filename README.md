# AndroidSharedInstance
单例生成与销毁

## Import
[JitPack](https://jitpack.io/)

Add it in your project's build.gradle at the end of repositories:

```gradle
repositories {
  // ...
  maven { url "https://jitpack.io" }
}
```

Step 2. Add the dependency in the form

```gradle
dependencies {
  compile 'com.github.vilyever:AndroidSharedInstance:1.1.0'
}
```

## Usage
```java

// to get shared instance
Carrier carrier = new SharedInstance<>(Carrier.class).getInstance();

// to set the shared instance
// true means force to replace existed instance, false means keep the existed instance
new SharedInstance<>(Carrier.class).setInstance(new Carrier(), true);

// to get shared instance with InitDelegate
// the delegate will only called once, even get instance with the InitDelegate again
Carrier carrier = new SharedInstance<>(Carrier.class).getInstance(new SharedInstance.InitDelegate<Carrier>() {
    @Override
    public void onInit(Carrier instance) {
        // init
    }
});

// to destory instance
new SharedInstance<>(Carrier.class).destoryInstance();

public class Carrier {
    // the method with annotation InstanceInit will auto call once right after the instance created
    // it is run before the InitDelegate
    @SharedInstance.InstanceInit
    private void init() {
        // init
    }
}

```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

