# GitHub Hub
GitHub Lagos is an Android application that fetches a list of GitHub users from the GitHub API (Application Programming Interface). The app has two screens List and Detail, built using modern Android development libraries. Clicking on the GitHub icon on both screens opens the user profile on a web browser.
This application was created for my case study as a process in my application to join Oze as an Android developer.

## Screenshot
<img width="337" alt="Screenshot 2022-05-10 at 14 06 34" src="https://user-images.githubusercontent.com/34775925/167648106-83720823-2cb6-4327-b57e-fe7f094a6ca0.png"> <img width="337" alt="Screenshot 2022-05-10 at 14 06 21" src="https://user-images.githubusercontent.com/34775925/167650890-6f95fc9a-6da6-471d-97b7-674a96352724.png">


## Libraries used:
-  Kotlin
-  Jetpack Compose - UI
-  Hilt  -  dependency injection
-  Retrofit - HTTP Request
-  Kotlin Coroutines  - for asynchronous tasks
-  Coil - image loading
-  MockK - creating mocks for tests
-  Turbine - testing coroutines Flow

## Installation
1. Download the file or clone the repo and
2. Open it in Android Studio

### Note 
Making multiple sucessive API calls may lead to a 403 error. This is caused by Github’s rate limiting. Authenticated requests get a higher rate limit. Check out the documentation for more details https://docs.github.com/rest/overview/resources-in-the-rest-api#rate-limiting .


## Architecture and Design
I used Model View View-Model (MVVM), repository pattern, and clean architecture. I used all 3 together to achieve a more structured code and less tight coupling between components. At a high level, I have 3 layers in the project which I have implemented as packages, all in one module. These layers include:
1. `Data Layer:` Responsible for getting data from any source, whether the local source or remote source. For this project, I implemented only the remote source(GitHub API) with Retrofit.
2. `Domain layer:` Houses the core business logic of the app, interacting with the data layer via an abstraction(Repository Interface), afterward returning data to the UI layer.
3. `UI layer:` Responsible for displaying content on the screen. The View-Model in this layer is responsible for interacting with the domain layer via a use-case class to send and retrieve data, based on user actions.



## Improvements
1. Navigation: Abstracting navigation implementation into a separate class. This gives the desired flexibility because it is easily injectable into the ViewModel, thereby delegating calls to the navigator from the ViewModel.
2. Local Source using Room: Adding a local source would improve the User Experience(UX) of the application. Users can always have content displayed when they use the app for a second time or maybe when they have no internet connection.
3. Multi Modules: Currently there is only one module that manages everything. We can add more modules that would help extract all the layers and features into their separate module. While this might be cumbersome, it has huge benefits such as increased build times, separation of concerns, etc.
4. UX: The app can capture more user content and notify user's internet availability
5. Unit Test: A higher test coverage would be nice to have
6. Pagination: Currently, the user can only scroll through 30 items on the list. Implementing pagination would load more items to the screen when the user scrolls to the end of the current list.



## Challenges
I experienced some difficulties abstracting the navigation implementation and injecting it using hilt.

## Resources
- https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/
- https://www.answertopia.com/jetpack-compose/screen-navigation-in-jetpack-compose/
- https://medium.com/google-developer-experts/navigating-in-jetpack-compose-78c78d365c6a
- https://github.com/cashapp/turbine
- https://codingwithmohit.com/coroutines/learning-shared-and-state-flows-with-tests/
- https://fabiosanto.medium.com/unit-testing-coroutines-state-flow-c6e6de580027
- https://developer.android.com/jetpack/compose/testing 
