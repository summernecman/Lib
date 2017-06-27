# Lib
android lib
gradle
Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Step 2. Add the dependency

dependencies {
	        compile 'com.github.summernecman:Lib:1.0.0'
	}
  
[![](https://jitpack.io/v/summernecman/Lib.svg)](https://jitpack.io/#summernecman/Lib)
