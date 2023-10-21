package com.linkedlist.linkllet.feature.link.addeditlink

import org.junit.Assert.*
import org.junit.Test

class UrlValidatorTest {

    private val urlValidator = UrlValidator()

    @Test
    fun `유효한 Url이 들어오면 true를 반환한다`() {
        // given
        val urls = listOf(
            "https://www.naver.com",
            "https://github.com/Nexters/Linkllet-Android",
            "https://algosketch.tistory.com/176"
        )

        // when & then
        urls.forEach {
            val result = urlValidator.validateUrl(it)
            assertTrue(result)
        }
    }

    @Test
    fun `유효하지 않은 Url이 들어오면 false를 반환한다`() {
        // given
        val urls = listOf(
            "https://www.naver.",
            "ttps://www.naver.",
            "https:/www.naver.com",
        )

        // when & then
        urls.forEach {
            val result = urlValidator.validateUrl(it)
            assertFalse(result)
        }
    }
}