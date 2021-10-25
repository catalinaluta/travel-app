# 1. define objective
-view rooms

-booking rooms

-car rentals

# 2. define entities

## user
-can register and login

-can view rooms

-can book rooms

-can rent a car

## admin
-can add rooms

-can remove rooms

-can add cars

-can remove cars

## booking rooms
    -can be filtered by:property type, room type

## view rooms
    user can view rooms

## car rentals
    -can be filtered by:location, duration, car specifications

## task
    tasks must be filtered by project

# 3. create epics
- user

-view rooms

   -book rooms

   -rent a car
  
- project
- sprint
- task

# 4. create backlog
- add user stories

## register
- view register page
- register with email and password
- login with email and password
- logout

## forgot password
- view forgot password page
- reset password

## manage project
- view project list
- create project
- update project info
- delete project
- as project lead, add user to project
- view backlog page
- view board page

## manage sprint
- create sprint
- update sprint info
- delete sprint
- add task to sprint
- remove task from sprint
- assign user to task
- un-assign user from task
- start sprint
- complete sprint
- view sprint total story points

## manage task
- view task list
- create task
- update task info
- delete task
- add task to project
- search task

## manage user
- view users page
- update user info
- as admin, deactivate user
- as admin, activate user
