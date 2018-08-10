# RepoExplorer
### Task
Load repositories from github and bitbucket apis,
then display it in list, on item click open detailed view
with additional description. User must be able to sort list alphabetically.

### Architecture
 - Project implement Mvvm principles with dataBinding and LiveComponents
 - Api is called with Retrofit and consumed by Rx subscribers
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
