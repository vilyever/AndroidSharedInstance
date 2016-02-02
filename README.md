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
Carrier carrier = new VDSharedInstance<>(Carrier.class).getInstance();

// to set the shared instance
// true means force to replace existed instance, false means keep the existed instance
new VDSharedInstance<>(Carrier.class).setInstance(new Carrier(), true);

// to get shared instance with initial delegate
// the delegate will only called once, even get instance with the initial delegate again
Carrier carrier = new VDSharedInstance<>(Carrier.class).getInstance(new VDSharedInstance.InitialDelegate<Carrier>() {
    @Override
    public void requireInitial(Carrier instance) {
        // initial
    }
});

// to destory instance
new VDSharedInstance<>(Carrier.class).destoryInstance();

public class Carrier {
    // the method with annotation VDInstanceInitial will auto call once right after the instance created
    // it is run before the initial delegate 
    @VDSharedInstance.VDInstanceInitial
    private void initial() {
        // initial
    }
}

```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

