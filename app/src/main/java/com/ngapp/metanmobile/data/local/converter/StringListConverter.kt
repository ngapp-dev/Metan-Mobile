package com.ngapp.metanmobile.data.local.converter

import androidx.room.TypeConverter
import com.ngapp.framework.extension.fromJson
import com.ngapp.framework.extension.toJson

class StringListConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}