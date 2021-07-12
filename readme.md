# How to use the Application
A tutorial on how to use my java application.
___
## Dedication to Rey
This project was dedicated to Thicc subway daddy Rey with their secret java sauce and binary cookies.
## How to add an item
> Note: Both the Date and description must be filled in before you can add an event.
___
### Filling in the DatePicker
You can fill in the date by clicking on the calendar icon on the DatePicker. 
> Note: You can also manually type in the date in the format of [MM-dd-yyyy].
### Filling in the Event Description
You can fill in the event description by left-clicking on the TextField and entering a description.
> Note: The TextField is limited to 1 and 256 characters in length.
### Choosing Status 
The status of an event is represented by the Checkbox. You can designate as item as complete by marking the checkbox.
> Note: By default, the Checkbox will be set to false.
### Displaying Information
The TableView is separated into three columns each with a corresponding data type. 
- The leftmost column (indicated by "Status") displays the status of the event on the same row.
- The middle column (indicated by "Due Date") displays the due date of the event in the format of [yyyy-MM-dd].
- The rightmost column (indicated by "Event Description") displays the description of the event.
## Removing an item
You can remove an item simply by left-clicking on the item in TableView, then clicking the "Delete Event" Button 
in the bottom right corner of the application.
> Note: The "Delete Event" Button will be disabled until an item is selected.
## Removing an all items
You can remove all items from the lest by clicking the "Delete All" Button in the bottom left of the application.
## Editing an item
> Note: An item must be selected before you can edit an item.
---
### Modifying the Event Description
After selecting an item, the TextField will be filled in accordingly.\
To edit the event description, simply click on the TextField and modify the Event Description to your liking. (See Filling in the Event Description).\
Afterwards, simply click on the "Modify Event" Button to update the item in the TableView.
### Modifying the Due Date
After selecting an item, the DatePicker will be filled in accordingly.\
To edit the event description, simply click on the DatePicker and modify the Due Date to your liking. (See Filling in the DatePicker).\
Afterwards, simply click on the "Modify Event" Button to update the item in the TableView.
### Modifying the Event Status
(See Choosing Status)\
Afterwards, simply click on the "Modify Event" Button to update the item in the TableView.
> Note: You can also modify the status of an item by left-clicking on the "Change Status" button in the bottom left corner of the application.
## Displaying All Existing Items
In order to see all existing items in the list, simply left-click the "Show All" Button at the bottom of the application.
> Note: This will open a new window displaying all corresponding events
## Displaying Only Completed Items
In order to see all existing items in the list, simply left-click the "Show Completed" Button at the bottom of the application.
> Note: This will open a new window displaying all corresponding events
## Displaying Only Incomplete Items
In order to see all existing items in the list, simply left-click the "Show Incomplete" Button at the bottom of the application.
> Note: This will open a new window displaying all corresponding events
## Exporting a List
In order to export a list, simply click the "Export..." Button in the top left of the application.\
Afterwards, a new window will open up. Choose a filename and save it to your selected destination on the computer.
## Importing a List
In order to import a list, simply click the "Import..." Button in the top left of the application.\
Afterwards, a new window will open up. Select a file on your computer and click open. The list will be loaded and displayed in TableView.
> Note: Importing a list will not overwrite any existing items.
