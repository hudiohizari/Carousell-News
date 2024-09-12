package com.hizari.common.extention

fun Int?.orZero(): Int {
    return this ?: 0
}

fun Long?.orZero(): Long {
    return this ?: 0
}