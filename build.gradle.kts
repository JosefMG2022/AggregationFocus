buildscript {

    repositories {
        google()
        mavenCentral()
    }

} // Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id ("com.android.library") version "7.4.2" apply false
    id ("io.realm.kotlin") version "1.13.0" apply false

}