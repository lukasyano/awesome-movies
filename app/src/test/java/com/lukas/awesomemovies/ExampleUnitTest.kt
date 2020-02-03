package com.lukas.awesomemovies

import com.lukas.awesomemovies.data.JsonParser
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun printFakDAte(){
        val tekstas = JsonParser().parseJsonMovies()
        print(
            """
                
                Page : ${tekstas.page}
                Results : ${tekstas.result.size}
                title of first item ${tekstas.result[0].title}
                
            """.trimIndent()
        )
    }
}
