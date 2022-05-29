package com.lukas.awesomemovies.repository

import com.lukas.awesomemovies.FilterType
import com.lukas.awesomemovies.data.network.MoviesService
import com.lukas.awesomemovies.repository.entity.MovieEntity
import com.lukas.awesomemovies.repository.mapper.Mapper
import io.reactivex.Scheduler
import io.reactivex.Single

class TrendingMoviesRepository(
    private val movieService: MoviesService,
    private val schedulerIO: Scheduler,
    private val schedulerMain: Scheduler
)
{
    fun getTrendingMovies(
        pageNr: Int,
        filterType: FilterType
    ): Single<List<MovieEntity>> {

        return movieService.getTrendingMovies(pageNr)
            .subscribeOn(schedulerIO)
            .observeOn(schedulerMain)
            .map {
                when (filterType) {
                    FilterType.UNFILTERED -> {
                        Mapper.mapMovies(it.results)
                    }
                    FilterType.POPULARITY -> {
                        Mapper.mapMovies(it.results.sortedByDescending { it.popularity })
                    }
                    FilterType.VOTES -> {
                        Mapper.mapMovies(it.results.sortedByDescending { it.voteAverage })
                    }
                    FilterType.DATE -> {
                        Mapper.mapMovies(it.results.sortedByDescending { it.releaseDate })
                    }
                }
            }
    }
}
