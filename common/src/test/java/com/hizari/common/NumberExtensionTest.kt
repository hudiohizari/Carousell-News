package com.hizari.common

import com.hizari.common.extention.orZero
import org.junit.Assert.assertEquals
import org.junit.Test

class NumberExtensionTest {

    @Test
    fun `test Int orZero with non-null value`() {
        val value: Int = 5
        assertEquals(5, value.orZero())
    }

    @Test
    fun `test Int orZero with null value`() {
        val value: Int? = null
        assertEquals(0, value.orZero())
    }

    @Test
    fun `test Long orZero with non-null value`() {
        val value = 10L
        assertEquals(10L, value.orZero())
    }

    @Test
    fun `test Long orZero with null value`() {
        val value: Long? = null
        assertEquals(0L, value.orZero())
    }
}