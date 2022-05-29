package com.lukas.awesomemovies

import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.data.network.model.MoviesResponse
import com.lukas.awesomemovies.di.SchedulerProvider
import com.lukas.awesomemovies.repository.TrendingMoviesRepository
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class TrendingMoviesRepositoryTest {

    private lateinit var classUnderTest: TrendingMoviesRepository
    private val moviesService: MoviesService = mock()
    private val schedulerProvider = SchedulerProvider().io()

    @Before
    fun setUp() {
        classUnderTest = TrendingMoviesRepository(moviesService, schedulerProvider, schedulerProvider)

        // mock service function moviesService.getTrendingMovies(pageNr)
        whenever( // pass any because the result is mocked anyways
            moviesService.getTrendingMovies(pageNr = 1)
        )
            .thenReturn(
                Single.just(
                    MoviesResponse(
                        page = 10,
                        results = fakedTrendingMovies,
                        total_pages = 10,
                        total_results = 10
                    )
                )
            )
    }

    @Test
    fun testTrendingMoviesByUnfiltered() {
        classUnderTest.getTrendingMovies(
            pageNr = 1,
            filterType = FilterType.UNFILTERED
        )
            .test()
            .assertNoErrors()

        verify(moviesService).getTrendingMovies(1)
    }

    @Test
    fun testTrendingMoviesByPopularity() {
        classUnderTest.getTrendingMovies(
            pageNr = 1,
            filterType = FilterType.POPULARITY
        )
            .test()
            .assertNoErrors()

        verify(moviesService).getTrendingMovies(1)
    }

    @Test
    fun testTrendingMoviesByVotes() {
        classUnderTest.getTrendingMovies(
            pageNr = 1,
            filterType = FilterType.VOTES
        )
            .test()
            .assertNoErrors()

        verify(moviesService).getTrendingMovies(1)
    }
}