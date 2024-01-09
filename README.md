# Movie TMDB
A simple movie app that uses the [TMDB Api](https://developer.themoviedb.org/reference/intro/getting-started) to display the most popular movies.</br>

This approach only makes use of the API Key for ```GET``` requests, the AccessToken was not used for ```POST``` requests since its expiration time is unknown and the TMDB API doesn't provide a service to obtain it other than copying it from the web environment.</br>

Because of this, the favorites section doesn't consume the web services provided by the API, instead a Room database was created to demonstrate its use.</br>

<img src="https://github.com/hugo-figueroa/Movie-TMDB/assets/33041982/b2c32429-c34d-4fb6-bcdb-68a4cf617df1" alt="Splash" width="300"/>
<img src="https://github.com/hugo-figueroa/Movie-TMDB/assets/33041982/785a3d30-7906-42f8-beef-921074ba8e0a" alt="Popular" width="300"/>
<img src="https://github.com/hugo-figueroa/Movie-TMDB/assets/33041982/51bb503a-5363-4765-a8ed-d9fa43df7a13" alt="Details" width="300"/>
<br/>
<img src="https://github.com/hugo-figueroa/Movie-TMDB/assets/33041982/3843eb23-c55e-4262-b20e-b15958d7445a" alt="Now Playing" width="300"/>
<img src="https://github.com/hugo-figueroa/Movie-TMDB/assets/33041982/b92b8ab3-a0bb-4274-b378-693dd8047b84" alt="Favorites" width="300"/>

## How to run it...
For security reasons, to be able to use this project you will need to add your [TMDB Api Key](https://www.themoviedb.org/settings/api) to your ```local.properties``` file like in the following example. </br></br>

```TMDB_KEY=xxxxxxxxxxxxxxxxxxxxxxxf9e54247e```

## Added concepts
- MVVM + Clean Architecture
- Repository Pattern, Builder Pattern, Observer Patter (with LiveData)
- ViewBinding
- ConstrainLayout
- LiveData, ViewModel
- Jetpack Compose
- Dagger Hilt
- Room
- Coroutines with custom CallAdapterFactory
- Extension Funtions
- Desing System
- Navigation Graph
- Network Error Handling
- Use Cases

## Pending concepts
- AuthorizationInterceptor
- Unit Testing
- Integration Testing

## Feature modules

### splash
This is a feature where we download a basic configuration before go to the Home of the app. <br/> Here you can usually use WorkManager to download the information in the background, however on this occasion the download was only done normally using a coroutine
### movieList
In this feature we download the popular movies and the now playing movies.
### movieDetails
This feature allow to show more information about a movie and also allow to add or remove a movie to favorites.
### favorites (Local)
In this feature we can check the favorites movies that we previously mark as favorite.

## Other modules
### core
Here you will find some base classes and classes commonly used among the different feature modules.
### networking
This module is used to create the configuration of Retrofit and Okttp
### material
Is used to add the design system classes and to add custom views when is needed.

## Concepts to improve
- Screen Designs
- Error Views
- BottomNavigationBar
- Navigation

## Architecture used
![architecture](https://github.com/hugo-figueroa/hugo-figueroa/assets/33041982/fb915f19-5b48-4abe-8ce9-6266a54b85c4)
