# Pokedex
 Pokedex build with PokeAPI using Android Kotlin

## Topics
### Architecture Components
- [x] Model-View-View Model Pattern
- [x] Kotlin Coroutines
- [x] Retrofit
- [x] Hilt Dependency Injection
- [x] Room
- [] Wear OS

### Navigation Components
- [x] Androidx Navigation
- [x] Navigation Drawer
- [x] View Pager 2
- [x] Paging3 Adapter

##### Navigation Drawer:
issue: navigation drawer don't change fragment
fix: should move <NavigationView> below <include> in activity_main  
https://stackoverflow.com/questions/57929874/clicking-on-item-of-navigation-drawer-doesn-t-open-fragments  

##### Retrofit:
dynamic url = https://futurestud.io/tutorials/retrofit-2-how-to-use-dynamic-urls-for-requests  

##### RecyclerView:
https://medium.com/@info.anikdey003/kotlin-recyclerview-in-a-proper-and-re-usable-way-bb14717daa93  
https://www.codepolitan.com/cara-membuat-pagination-atau-load-more-menggunakan-recyclerview-part-1-59c689b1b2e76  
https://androidwave.com/pagination-in-recyclerview/  

##### Pagination 3.0 (only loadMore, not loadInitial, loadBefore, loadAfter):  
https://developer.android.com/codelabs/android-paging
https://androidwave.com/android-paging-library/
https://proandroiddev.com/playing-with-4f21bc67a7f9

##### ViewPager2:
https://developer.android.com/guide/navigation/navigation-swipe-view-2
https://www.raywenderlich.com/8192680-viewpager2-in-android-getting-started
https://androidwave.com/viewpager2-with-tablayout-android-example/

##### Room local cache from retrofit:
https://itnext.io/android-architecture-hilt-mvvm-kotlin-coroutines-live-data-room-and-retrofit-ft-8b746cab4a06
https://proandroiddev.com/android-architecture-starring-kotlin-coroutines-jetpack-mvvm-room-paging-retrofit-and-dagger-7749b2bae5f7

##### Wear OS:
adb -d forward tcp:5601 tcp:5601
https://developer.android.com/training/wearables/apps/creating
https://www.raywenderlich.com/254-getting-started-with-android-wear-with-kotlin#toc-anchor-019
https://code.tutsplus.com/tutorials/get-wear-os-and-android-talking-exchanging-information-via-the-wearable-data-layer--cms-30986
https://www.dashdevs.com/blog/wear-os-app-development-tutorial-p2/
https://infinum.com/the-capsized-eight/make-your-app-work-with-android-wear-in-4-easy-steps
https://medium.com/rosberryapps/wear-os-application-development-practical-aspects-a761019f7ec4
https://medium.com/mindorks/a-beginner-guide-to-android-watch-app-wear-2-0-71b27a802d11
https://www.codeproject.com/Articles/1040547/Android-Wear-Notification
https://www.programmableweb.com/news/how-to-develop-android-wear-application/how-to/2014/10/17