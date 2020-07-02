package com.example.swenson

import junit.framework.Assert.assertEquals
import org.junit.Test


class Fibonacci{

    private val testingArr = arrayOf(0,1, 1, 2, 3, 5, 8, 13, 21, 34)


    private fun recursiveFibonacciNumber(n:Int): Int
    {
        return if(n<=1) testingArr[n]
        else (testingArr[n-1])+ (testingArr[n-2])
    }

    fun iterativeFibonacciNumber(n:Int):Int{

         if(n<=1) return testingArr[n]

        var N = n
        var a = 0
        var b = 1
        var sum = a+b

        while (N>1){
            sum = a+b
            a = b
            b= sum
            N --
        }

        return sum
    }




    @Test
    fun findFibonacci() {
        assertEquals(iterativeFibonacciNumber(6), 8)
        assertEquals(recursiveFibonacciNumber(6), 8)

    }
}