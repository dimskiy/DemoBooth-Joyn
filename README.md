# DemoBooth
Simple photo booth-like app allowing to search and browse photos from pixabay.com.

The project is written fully in Kotlin and uses RxJava2 as main reactive\async transport for all app layers.

## App architecture
Project created using *Clean architecture* design approach splitting the logic onto a few independent layers glued with *Mappers*.
Presentation layer created using MVVM pattern.
Some core components re-used from my previous projects - you can find them in 'commonshared' package.
Mapper classes placed in the separate top-level package because they are actually 'glue' linking different architecture layers.

## UI
Photos list allows screen to search items - use three more letters for query. You will see the snackbar message if something goes wrong.

I placed all the details screen logic in its activity because it mostly related to
android platform, and placing it in domain or presentation level is nonsense.

## REST implementation
** You need to place your own API key in 'local.properties' file - parameter 'pixabay.api.key' **

The project uses Retrofit2 for backend interaction. I'm not 100% satisfied on how I integrated the API key to the requests, but let's leave it for future improvements :)

## Testing
There were no requirements to implement unit tests, but I have added a few - just in case.

## DI
All app layers are glued with Dagger framework. The main app component located in the root "di" package, and all activities
have their own Dagger components with the separate scopes, thus allowing to save memory resources in runtime.
