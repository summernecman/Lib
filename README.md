# Lib
android lib
gradle

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
			 maven { url "https://raw.github.com/bmob/bmob-android-sdk/master" }
		}
	}
  
Step 2. Add the dependency

dependencies {
	        compile 'com.github.summernecman:Lib:1.0.3'
	}
  
(https://jitpack.io/#summernecman/Lib)

step3:
    compileSdkVersion
    buildToolsVersion
    //android 6.0 之后 取消支持 Apache HTTP 客户端，要继续使用 Apache HTTP API，您必须先在 build.gradle 文件中声明以下编译时依赖项：
    useLibrary 'org.apache.http.legacy'//add this

