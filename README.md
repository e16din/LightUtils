# LightUtils
Light utils for more productivity

## IntentMaster
Simple way to start activity with your data

```java
//start with data
IntentMaster.startActivity(AuthActivity.this, MainActivity.class,
                                yourData);                              
//check extra
IntentMaster.hasExtra(this);

//get data
User user = (User) IntentMaster.getExtra(this);
```

## DataManager
Simple way to put your data to shared preferences

```java
//save
DataManager.getInstance().save(KEY_TOKEN, accessToken);

//load
DataManager.getInstance().loadString(KEY_TOKEN);
//or load
DataManager.getInstance().load(KEY_USER, User.class);
```

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
    compile 'com.github.e16din:LightUtils:1.2.7'
}
```