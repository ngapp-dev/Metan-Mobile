package com.ngapp.framework.base.app

abstract class NetworkConfig {
    abstract fun baseUrl(): String

    abstract fun githubUrl(): String

    abstract fun timeOut(): Long

    open fun isDev() = false
}