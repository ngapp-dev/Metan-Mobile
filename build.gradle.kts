buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.4.1")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

plugins {
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

apply<codequality.DependencyUpdatePlugin>()

apply(plugin = "codeanalyzetools.jacoco-multi-report")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}