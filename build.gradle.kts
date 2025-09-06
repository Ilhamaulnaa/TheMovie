// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    id("com.android.application") version "8.1.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
//    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
//    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    kotlin("plugin.serialization") version "1.8.10"
//    id("com.android.library") version "8.1.1" apply false
    id("com.android.application") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21" apply false
}