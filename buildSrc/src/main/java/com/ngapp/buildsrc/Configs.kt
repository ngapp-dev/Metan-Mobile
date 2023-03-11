package com.ngapp.buildsrc


object Configs {
    private const val versionMajor = 0
    private const val versionMinor = 0
    private const val versionPatch = 1
    private const val versionQualifier = "alpha1"

    private fun generateVersionCode(): Int {
        return versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun generateVersionName(): String {
        return "$versionMajor.$versionMinor.${versionPatch}${versionQualifier}"
    }

    const val Id = "com.ngapp.metanmobile"
    val VersionCode = generateVersionCode()
    val VersionName = generateVersionName()
    const val MinSdk = 23
    const val TargetSdk = 33
    const val CompileSdk = 33
    const val AndroidJunitRunner = "androidx.test.runner.AndroidJUnitRunner"
    val FreeCompilerArgs = listOf(
        "-Xjvm-default=all",
        "-opt-in=kotlin.RequiresOptIn",
        "-opt-in=kotlin.Experimental",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=kotlinx.coroutines.InternalCoroutinesApi",
        "-opt-in=kotlinx.coroutines.FlowPreview",
        "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
        "-opt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi",
        "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        "-opt-in=com.google.accompanist.permissions.ExperimentalPermissionsApi"
    )

    object Release {
        const val BaseUrl = "https://metan.by/"
        const val GithubUrl = "https://api.github.com/"
        const val DbName = "MetanMobileDb"
    }

    object Debug {
        const val BaseUrl = "https://metan.by/"
        const val GithubUrl = "https://api.github.com/"
        const val DbName = "MetanMobileDb"
    }
}