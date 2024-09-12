package com.hizari.common.util

import com.hizari.common.BuildConfig
import com.hizari.common.extention.isNotNullOrEmpty
import android.util.Log

object Logger {

    // Log type
    enum class Type {
        D, E, I, V, W, WTF
    }

    // Getting tag for
    private fun tag(): String {
        return this.javaClass.simpleName
    }

    // Check if build is debug
    private fun ifDebug(work: () -> Unit) {
        if (BuildConfig.DEBUG) work()
    }

    // Get where the log is called
    private fun getWhereLogIsCalled(): String {
        return try {
            // Call through private fun log
            getStringLocation(Thread.currentThread().stackTrace[6])
        } catch (e: Exception) {
            try {
                // Call without private fun log
                getStringLocation(Thread.currentThread().stackTrace[4])
            } catch (e: Exception) {
                "Unknown class and line"
            }
        }
    }

    private fun getStringLocation(it: StackTraceElement): String {
        return "${it.className.substringAfterLast(".")}.${it.methodName}(${it.fileName}:${it.lineNumber})"
    }

    private fun log(
        type: Type,
        message: String,
        tr: Throwable? = null,
        toFirebase: Boolean = false
    ) {
        var formattedMessage = getWhereLogIsCalled()
        if (message.isNotNullOrEmpty()) formattedMessage += ": $message"
        ifDebug {
            when (type) {
                Type.D -> Log.d(tag(), formattedMessage, tr)
                Type.E -> Log.e(tag(), formattedMessage, tr)
                Type.I -> Log.i(tag(), formattedMessage, tr)
                Type.V -> Log.v(tag(), formattedMessage, tr)
                Type.W -> Log.w(tag(), formattedMessage, tr)
                Type.WTF -> Log.wtf(tag(), formattedMessage, tr)
            }
        }
    }

    fun d(message: String, tr: Throwable? = null) {
        log(Type.D, message, tr)
    }

    fun e(message: String, tr: Throwable? = null) {
        log(Type.E, message, tr)
    }

    fun i(message: String, tr: Throwable? = null) {
        log(Type.I, message, tr)
    }

    fun v(message: String, tr: Throwable? = null) {
        log(Type.V, message, tr)
    }

    fun w(message: String, tr: Throwable? = null) {
        log(Type.W, message, tr)
    }

    fun wtf(message: String, tr: Throwable? = null) {
        log(Type.WTF, message, tr)
    }

}