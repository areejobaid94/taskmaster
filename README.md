# TaskMaster

## This App is to manege tasks => add and show tasks.

### Tuesday at 4:40 finish class 26 with:
1- Homepage:

![](https://codefellows.github.io/code-401-java-guide/curriculum/class-26/taskmaster_homepage.png)

The main page should be built out to match the wireframe above. In particular, it should have a heading at the top of the page, an image to mock the “my tasks” view, and buttons at the bottom of the page to allow going to the “add tasks” and “all tasks” page.

2- Add a Task:

![](https://codefellows.github.io/code-401-java-guide/curriculum/class-26/taskmaster_add_task.png)

On the “Add a Task” page, allow users to type in details about a new task, specifically a title and a body. When users click the “submit” button, show a “submitted!” label on the page.

3- All Tasks:

![](https://codefellows.github.io/code-401-java-guide/curriculum/class-26/taskmaster_all_tasks.png)

The all tasks page should just be an image with a back button; it needs no functionality.

### Tuesday at 9:00 finish class 27 with:

1- Task Detail Page
Create a Task Detail page. It should have a title at the top of the page, and a Lorem Ipsum description.

2- Settings Page
Create a Settings page. It should allow users to enter their username and hit save.

3- Homepage
The main page should be modified to contain three different buttons with hardcoded task titles. When a user taps one of the titles, it should go to the Task Detail page, and the title at the top of the page should match the task title that was tapped on the previous page.

The homepage should also contain a button to visit the Settings page, and once the user has entered their username, it should display “{username}’s tasks” above the three task buttons.

### Wednesday at 4:40 AM finish class 28 with:

1- Task Model
Create a Task class. A Task should have a title, a body, and a state. The state should be one of “new”, “assigned”, “in progress”, or “complete”.

2- Homepage
Refactor your homepage to use a RecyclerView for displaying Task data. This should have hardcoded Task data for now.

Some steps you will likely want to take to accomplish this:

* Create a ViewAdapter class that displays data from a list of Tasks.
* In your MainActivity, create at least three hardcoded Task instances and use those to populate your RecyclerView/ViewAdapter.
Ensure that you can tap on any one of the Tasks in the RecyclerView, and it will appropriately launch the detail page with the correct Task title displayed.

### SaturDay at 6:37 AM finish class 29 with:

##### Task Model and Room
Following the directions provided in the Android documentation, set up Room in your application, and modify your Task class to be an Entity.

1- Add Task Form
Modify your Add Task form to save the data entered in as a Task in your local database.

2- Homepage
Refactor your homepage’s RecyclerView to display all Task entities in your database.

3- Detail Page
Ensure that the description and status of a tapped task are also displayed on the detail page, in addition to the title. (Note that you can accomplish this by passing along the entire Task entity, or by passing along only its ID in the intent.)


### SaturDay at 6:37 AM finish class 31 with:

##### Adding Espresso Testing


### Class 32 with:

##### * Add Task Form
Modify your Add Task form to save the data entered in as a Task to DynamoDB.

##### * Homepage
Refactor your homepage’s RecyclerView to display all Task entities in DynamoDB.


### Class 36 - Cognito with:

### User Login
Add Cognito to your Amplify setup. Add in user login and sign up flows to your application, using Cognito’s pre-built UI as appropriate. Display the logged in user’s username somewhere relevant in your app.

### User Logout
Allow users to log out of your application.

### Class 37 - S3

### Uploads
On the “Add a Task” activity, allow users to optionally select a file to attach to that task. If a user attaches a file to a task, that file should be uploaded to S3, and associated with that task.

### Displaying Files
On the Task detail activity, if there is a file that is an image associated with a particular Task, that image should be displayed within that activity. (If the file is any other type, you should display a link to it.)

### Class 38 - Notifications

Manage to send notifications to all using firebase


#### [screenshots](./screenshots.md)
