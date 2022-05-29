package com.lukas.awesomemovies

import com.lukas.awesomemovies.repository.mapper.Mapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertFalse
import kotlin.test.assertTrue

    @RunWith(MockitoJUnitRunner::class)
    internal class MapperTest {
        /**
         * All movies which have empty poster paths are excluded
         * All moves which have vote average of 0.0 are excluded
         */
        @Test
        fun testMoviesCorrectlyFiltered() {
            val fakeMovieList = fakeListOfMovieResults //list of 3 Api Movies
            val result = Mapper.mapMovies(fakeMovieList)

            assertTrue(result.size == 1) // 2 objects should be filtered
            val movieEntity = result[0]

            assertFalse(movieEntity.posterPath.isNotEmpty())
            assertTrue(movieEntity.voteAverage != 0.0)
        }
    }