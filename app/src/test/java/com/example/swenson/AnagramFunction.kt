package com.example.swenson

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AnagramFunction {


    //TODO: Sum of Ascii codes for each word and check equality.

    private val testData1 = "debit card"
    private val testData2 = "bad credit"


    private fun isAnagrams(stringOne: String, stringTwo: String): Boolean {
        val firstString = stringOne.clearString()
        val secondString = stringTwo.clearString()

        var sumStringOne = 0
        var sumStringTwo = 0

        for (char in firstString) {
            sumStringOne += char.toInt()
        }

        for (char in secondString) {
            sumStringTwo += char.toInt()
        }

        return sumStringOne == sumStringTwo
    }


    @Test
    fun isAnagram() {
        assertEquals(true, isAnagrams(testData1, testData2))
    }


    private fun String.clearString(): String {
        return this.toLowerCase().trim().replace(" ", "")
    }
}