# GitHub Hub
GitHub Hub is an Android application that fetches a list of GitHub users from the GitHub API (Application Programming Interface). The app has two screens List and Detail, built using modern Android development libraries. Clicking on the GitHub icon on both screens opens the user profile on a web browser.
This application was created for my case study as a process in my application to join Trivago as a Junior Android developer.

## Screenshot
<img width="328" alt="Screenshot 2022-05-10 at 14 06 34" src="https://user-images.githubusercontent.com/34775925/167646829-f6c5e7c8-43a6-450d-9803-8cbc197a1a34.png">
<img width="337" alt="Screenshot 2022-05-10 at 14 06 21" src="https://user-images.githubusercontent.com/34775925/167646856-30d2f5af-33a5-4bc1-bfd4-159996268915.png">


## Libraries used:
-  Kotlin
-  Jetpack Compose - UI
-  Hilt  -  dependency injection
-  Retrofit - HTTP Request
-  Kotlin Coroutines  - for asynchronous tasks
-  Coil - image loading
-  MockK - mocking
-  Turbine - testing coroutines Flow

## Installation
1. Download the file or clone the repo and
2. Open it in Android Studio


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


## Challenges
I experienced some difficulties abstracting the navigation implementation and injecting it using hilt.

## Resources
https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/
https://www.answertopia.com/jetpack-compose/screen-navigation-in-jetpack-compose/
https://medium.com/google-developer-experts/navigating-in-jetpack-compose-78c78d365c6a
https://github.com/cashapp/turbine
https://codingwithmohit.com/coroutines/learning-shared-and-state-flows-with-tests/
https://fabiosanto.medium.com/unit-testing-coroutines-state-flow-c6e6de580027
