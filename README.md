# Common

Some Tiny Kits For Android Programming

## Usage

Step 1. Add it in your root build.gradle at the end of repositories:

```java
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

Step 2. Add the dependency:

```java
	dependencies {
		compile 'com.github.BingyanStudio:Common:0.1.0'
	}
```

Step 3. Add `Common.init(this)` at Application's `onCreate()` ( you can find the way in the example )

## Guide

### Pack 1 - activity

- ActivityHelper : balabalabala
- BaseActivity : ....

To be continued ...

## ChangeLog

### v0.1.0
- init project


## License
    Copyright 2015 Bingyan Studio

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
