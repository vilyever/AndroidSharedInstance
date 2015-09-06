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
  compile 'com.github.vilyever:AndroidSharedInstance:1.0.1'
}
```

## Usage
```java

// to get shared instance
Carrier carrier = new VDSharedInstance<>(Carrier.class).getInstance();

// to destory instance
new VDSharedInstance<>(Carrier.class).destoryInstance();

```

## License
[Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)

