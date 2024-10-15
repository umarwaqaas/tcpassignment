# tcpassignment

**Task Manager App**
Platform: **Android**
Language: **100% Kotlin**
Framework: **Android SDK**
Status: **All stories fulfilled**
Github Link : https://github.com/umarwaqaas/tcpassignment


**Overview**

The Task Manager App helps employees manage daily tasks, track progress, and update task statuses, specifically designed for off-desk workers without constant supervision. This application allows employees to see their daily tasks, filter tasks by date, mark tasks as completed or unresolved, add comments, and more.

**Features & User Stories**

1	View Tasks for Today
◦	Employees can see a list of tasks assigned for the current day.
◦	If no tasks are assigned, they will see a message that everything is completed.

2	View Tasks for Other Days
◦	Employees can navigate to past or future tasks using navigation arrows.
◦	Tasks are displayed according to the selected date.

3	Task Prioritization
◦	Tasks are sorted by priority, with the highest priority task appearing first.
◦	Tasks with the same priority are sorted by due date.

4	View Task Details
◦	Employees can tap on a task to view more details such as the title, description, and due date.

5	Mark Task as Done or Can’t Resolve
◦	Tasks can be marked as either "Resolved" or "Can’t resolve."
◦	The status is updated locally and is visible in the task list with a status icon.

6	Add Comments to Tasks
◦	Employees can leave comments when marking a task as "Can’t resolve" or "Resolved" to explain the task’s outcome or any issues encountered.

7	Status Icon
◦	A status icon is shown next to each task in the list, indicating whether the task is "Resolved" or "Unresolved."

**Project Structure**

•	TaskListActivity: Displays the list of tasks for the day and provides navigation between different days.
•	TaskViewModel: Handles fetching, sorting, and filtering of tasks. Tasks are sorted by priority and due date.
•	TaskRepository: Manages the task data fetched from the API. Provides data to the ViewModel.
•	TaskAdapter: Custom adapter for displaying tasks in a RecyclerView. Handles icons for task status.
•	TaskDetailActivity: Displays details of the selected task. Allows marking a task as "Done" or "Can’t resolve" and adding comments.

**API Integration**

The app fetches tasks from the provided endpoint:
http://demo1414406.mockable.io/
•	The tasks are loaded and stored locally in memory, and all sorting and filtering are done locally.
•	For testing purposes, task status and comments are also stored locally.


**Local Data Management**

•	Task Status: Tasks are marked as "Resolved" or "Unresolved" locally (Using RoomDb) and status is shown in the list.
•	Task Comments: Comments added when marking tasks are stored locally and shown in the task details view.


**Sorting & Filtering**

•	Sorting: Tasks are sorted by priority in descending order, with a secondary sort by due date if priorities are the same.
•	Filtering: Tasks are filtered by date, allowing employees to view past and upcoming tasks.


**Prerequisites**

•	Android Studio: This project is built using Android Studio and Kotlin.
•	Kotlin: The app is written in Kotlin and uses modern Android development practices.

**How to Run the Project**
1	Clone the repository.
2	Open the project in Android Studio.
3	Sync the Gradle files and run the app on an emulator or device.
4	The tasks will be fetched from the mock API endpoint.




