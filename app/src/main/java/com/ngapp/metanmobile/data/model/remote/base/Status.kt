package com.ngapp.metanmobile.data.model.remote.base

import com.ngapp.framework.network.moshi.IValueEnum
import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

@FallbackEnum(name = "unknown")
enum class Status(override val value: String) : IValueEnum {
    @Json(name = "Operate")
    Operate("Operate"),

    @Json(name = "Not Operate")
    NotOperate("Not Operate"),

    @Json(name = "unknown")
    Unknown("unknown");
}