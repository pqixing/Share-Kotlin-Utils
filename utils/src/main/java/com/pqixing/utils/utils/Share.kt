package com.pqixing.utils.utils

import android.content.Context
import com.pqixing.utils.context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by pqixing on 17-7-31.
 */
class Share<T>(val name: String, val default: T, val prefName: String = context().packageName) : ReadWriteProperty<Any?, T> {
    val prefs by lazy { context().getSharedPreferences(prefName, Context.MODE_PRIVATE) }


    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    open fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    open fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}