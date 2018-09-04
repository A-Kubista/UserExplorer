# UserExplorer
### Task
Load users from github and dailymotion apis, then display it in recycle view.
Show origin of data. On item click open detailed view
with additional description. Save state to bundle.
Use MVVM

### Architecture
 - Project implements Mvvm principles with dataBinding and LiveComponents
 - Api is called with Retrofit and consumed by Rx subscribers, and served via repository class
 - data is cached with Room, Pull Down to refresh cache
 - Dagger 2 is userd for dependency injections.
 - RecycleView is used as list container for data
 - Picasso for async loading images

### Additional ToDo
 - unit tests
 - integration tests
 - shared element transition animations


### Setup
in case classes are not generated
android studio:
`build -> rebuild project` 
