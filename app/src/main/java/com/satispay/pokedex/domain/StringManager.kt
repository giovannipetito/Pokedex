package com.satispay.pokedex.domain

import android.content.Context
import androidx.annotation.StringRes

sealed class StringManager {
    data class DynamicString(val value: String) : StringManager()
    class ResourceString(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : StringManager()

    fun getString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is ResourceString -> context.getString(id, *args)
        }
    }
}