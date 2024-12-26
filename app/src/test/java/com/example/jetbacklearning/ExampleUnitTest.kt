package com.example.jetbacklearning

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    public val createObject  = 1
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `Given valid Value when call add api call then we should expect user is added`(){ // given add condition
        // when add method calling controller.add()
        // then expectation
       // assertEquals(1,controller.getList().size)

    }
    @Test
    fun `Given number is less than when add button register then expect failed`(){

    }
}