# WeatherApp (Android)

This app shows the weather and forecast for the next 7 days. There is an edit box where the user can find the desired city for weather updates.

## Architecture overview
---------------------

Weather App follows the [MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern#:~:text=MVVM%20stands%20for%20Model%2C%20View%2C%20ViewModel.&text=Generally%2C%20it%27s%20recommended%20to%20expose,It%20observes%20the%20ViewModel.),
and [JUnit](https://junit.org/) is used for unit testing.
Tools/Libraries/Patterns Used:
---------------------
* MVVM Design Pattern
* Coroutine (use to handle multithread)
* Retrofit2 (use to calls API)
* Okhttp3 (use to support Retrofit2)
* RecyclerView (use to load list)
* Room Persistence (use to store data to local database)
* Gson (use to parse json to object)
* Proguard (use to secure app)
* Mockito & JUnit (use for unittest)
* Git (source control)

## Project structure
---------------------
* "data" package contains all classes that related to data handling (get, set data, etc...).
* "main" package contains all classes that related to UI & viewmodel.
* "util" package contains all classes that related to kotlin extensions & handling other stuff (check network, parse time...).
* "widgets" package contains a divider class

## Checklist
---------------------
Done:

* Programming language: Kotlin is required, Java is optional.
* Design app's architecture (suggest MVVM)
* Apply LiveData mechanism
* UI should be looks like in attachment.
* Write UnitTests
* Acceptance Tests
* Exception handling
* Caching handling
* Secure Android app from: Decompile APK & Encryption for sensitive information
* Readme file

Not Completed:
* Secure Android app from: Rooted device & Data transmission via network
* Accessibility for Disability Supports
* Entity relationship diagram for the database and solution diagrams for the components, infrastructure design if any
