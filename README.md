# LightUtils
Light utils for more productivity

[![Release](https://jitpack.io/v/e16din/LightUtils.svg)](https://jitpack.io/#e16din/LightUtils)

## Init
```java
//Application onCreate
@Override
public void onCreate() {
    super.onCreate();

    LightUtils.init(this);//set application context
}
```

## Dependencies:
[JodaTime](https://github.com/JodaOrg/joda-time)


## Download (Gradle)

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

buildscript {
    repositories {
        maven { url "https://jitpack.io" }
    }
}

dependencies {
    compile 'com.github.e16din:LightUtils:1.+'
}
```