package com.hizari.common

import com.hizari.common.extention.isNotNullOrEmpty
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test String isNotNullOrEmpty with non-empty string`() {
        val value = "Hello"
        assertTrue(value.isNotNullOrEmpty())
    }

    @Test
    fun `test String isNotNullOrEmpty with empty string`() {
        val value = ""
        assertFalse(value.isNotNullOrEmpty())
    }

    @Test
    fun `test String isNotNullOrEmpty with null value`() {
        val value: String? = null
        assertFalse(value.isNotNullOrEmpty())
    }
}