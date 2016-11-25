# LightUtils
A collection of useful utils.

[![Release](https://jitpack.io/v/e16din/LightUtils.svg)](https://jitpack.io/#e16din/LightUtils)

## Usage
```java
//several examples

Resources res = U.getResources();

String title = U.getString(R.string.title);

int color = U.getColor(R.color.red);

int dp16 = U.dpToPx(16);

Size screenSize = U.getScreenSize(activity);


U.logLongD(TAG, longText);//log all long text

U.logJsonI(TAG, jsonString);//log long formated json

//...

```

## Init
```java
//Application onCreate
@Override
public void onCreate() {
    super.onCreate();
    
    //set application context
    LightUtils.init(this, BuildConfig.DEBUG);
}
```

## Download
Step 1. Add it in your root build.gradle at the end of repositories:
```groovy
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```
Step 2. Add the dependency
```groovy
    dependencies {
        compile 'com.github.e16din:LightUtils:1.5.7'
    }
```

## License MIT
Copyright (c) 2015 [Александр Кундрюков (e16din)](http://goo.gl/pzjc8x)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
