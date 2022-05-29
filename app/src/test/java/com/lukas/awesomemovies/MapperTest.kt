package com.lukas.awesomemovies

import com.lukas.awesomemovies.repository.mapper.Mapper
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
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

            assertTrue(movieEntity.posterPath.isNotEmpty())
            assertNotEquals(movieEntity.voteAverage ,0.0)
        }
        /**
         * original lang should not be empty
         * original lang should be "Unknown" if original_language not in list of spoken_languages.iso_639_1
         */
        @Test
        fun testMovieDetailsOriginalLanguageSetCorrectly() {
            val fakeOriginalLanguage  = "Unknown"

            val fakeMovieDetails = fakeMovieDetailsResult
            val movieDetailsEntity = Mapper.mapMovieDetails(fakeMovieDetails)

            assertTrue(movieDetailsEntity.originalLanguage.isNotEmpty())
            assertEquals(movieDetailsEntity.originalLanguage, fakeOriginalLanguage)
        }
    }