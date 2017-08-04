buildscript{
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${extra.properties["gradle_version"]}")
//        classpath(kotlinModule("gradle-plugin"))
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra.properties["kotlin_version"]}")
        print("Properties values = ${extra.properties}")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}