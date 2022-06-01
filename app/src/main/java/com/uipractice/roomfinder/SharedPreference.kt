package com.uipractice.roomfinder

import android.content.Context
import com.uipractice.roomfinder.webServices.APP_NAME

class SharedPreference {

    companion object {

        fun addSharedPreference(context: Context, key: String, value: Any) {
            val editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).edit()
            when (value) {
                is String -> {
                    editor.putString(key, value)
                }
                is Int -> {
                    editor.putInt(key, value)
                }
                is Float -> {
                    editor.putFloat(key, value)
                }
                is Long -> {
                    editor.putLong(key, value)
                }
                is Boolean -> {
                    editor.putBoolean(key, value)
                }
            }
            editor.apply()
        }

        fun getSharedPreference(context: Context, key: String, defaultValue: Any): Any? {
            val editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE)
            when (defaultValue) {
                is String -> {
                    editor.getString(key, defaultValue)?.let { return it }
                }
                is Int -> {
                    return editor.getInt(key, defaultValue)
                }
                is Float -> {
                    return editor.getFloat(key, defaultValue)
                }
                is Long -> {
                    return editor.getLong(key, defaultValue)
                }
                is Boolean -> {
                    return editor.getBoolean(key, defaultValue)
                }
                else -> return null
            }
            return null
        }

        fun removeAllSharedPreference(context: Context) {
            val editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).edit()
            editor.clear().apply()
        }

        fun removeSharedPreference(context: Context, key: String) {
            val editor = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE).edit()
            editor.remove(key).apply()
        }

    }

}