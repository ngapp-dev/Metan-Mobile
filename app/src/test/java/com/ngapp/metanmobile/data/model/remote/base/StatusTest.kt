package com.ngapp.metanmobile.data.model.remote.base

import org.junit.Assert
import org.junit.Test

class StatusTest {

    @Test
    fun checkFieldValues() {
        Assert.assertEquals(Status.Operate.value, "Operate")
        Assert.assertEquals(Status.NotOperate.value, "Not Operate")
        Assert.assertEquals(Status.Unknown.value, "unknown")
    }
}