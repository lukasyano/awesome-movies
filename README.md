# Awesome movies - Android app for browsing free movie content
The entire project developed using **Kotlin** programming language. App uses [themoviedb](https://www.themoviedb.org/documentation/api) public free API.

## Features :

* On the **Home** page a user can browse or bookmark the movies using three different filters (Popularity, Release date, Vote average).
* On the **Discovery** page a user can search movies by title or by genre. These movies also can be bookmarked. 
* On the **Bookmarks** page a user can view and remove bookmarked movies.

## Architecture design pattern:

[MVVM (Model-View Viewmodel)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)

# Libraries 

### Android Jetpack Components :
* Local database – Room
* Livedata
* Navigation component
* ViewModel

### Third part Libraries :
* Dependency Injection - Koin/ {Dagger Hilt(Coming soon!)}
* Networking - Retrofit2 , okHttp
* Concurrency - RxJava2
* Image loading – Glide


