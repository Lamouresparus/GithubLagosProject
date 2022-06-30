package com.githublagos.ui.extensions

import org.junit.Assert
import org.junit.Test

class StringExtensionsKtTest{

    @Test
    fun `getPlural returns correct plural of word`() {
        val expectedPlural = "users"
        val word = "user"

        Assert.assertEquals(expectedPlural, word.getPlural(2))

        Assert.assertEquals(expectedPlural, word.getPlural(0))

        Assert.assertEquals(word, word.getPlural(1))
    }
}