# **Android Kotlin internal project**

---

### **Purpose**
To show possibilities and good practices using Kotlin language.


### **Description**
Application connects to SpaceX API to download 3 types of data:

* SpaceX rocket fleet
* Upcoming flights
* Past events

Data is presented with generic adapter approach and is saved in the database.

Clicking on each item navigates user to a browser to read more information on the Web.

Use swipe-down gesture to refresh downloaded data.


### **Libraries/concepts used**

* MVVM architecture
* Retrofit - for networking
* Moshi converter - for JSON parsing
* ObjectBox - for NoSQL database
* Koin - for service locator pattern implementation
* Picasso - for image loading
* Timber - for logging
* Android Architecture Components (LiveData, ViewModel classes) - for observer pattern and MVVM implementation
* Kotlin coroutines - to manage threads gracefully
* Kotlin View Binding - to ease connection with XML code
* Generic adapter - to use single adapter for multiple object types