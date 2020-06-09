# TodoApp-demo

This is a demo application to demonstrate layering and containerization of application

This contains following features
1. Swagger API implementation
2. In Memory database 
3. Liquibase migrations 
4. Controller advice for global exception handling
5. Unit test cases for all the layers

Following are the Api end point 

**Base Url : localhost:8090/todoapp/api** 

## Get Api
1 Get All tasks in the system .

**url: /v1/task**

Response will be status code 200 
with a list of all the tasks present in the system.

_*Response Json : *_
[
    {
        "id": 1,
        "userId": 2,
        "task_description": "Task 1",
        "createdDate": 1591258171041,
        "modifiedDate": 1591258170963,
        "deleted": false
    }
]

2 Get All tasks in the system by username .

**url: /v1/task/{username}**

Response will be status code 200 
with a list of all the tasks present in the system.

_*Response Json : *_
[
    {
        "id": 1,
        "userId": 2,
        "task_description": "Task 1",
        "createdDate": 1591258171041,
        "modifiedDate": 1591258170963,
        "deleted": false
    }
]

## Post Api
3 Create task

**url: /v1/task**

_*Request Json : *_

{
	"task_description": "Task 1",
	"user_name":"Shnamde1"
}

Response will be status code 201 with created task

_*Response Json : *_

{
    "id": 1,
    "userId": 2,
    "user_name": "Shnamde1",
    "task_description": "Task 1",
    "createdDate": 1591258170963,
    "modifiedDate": 1591258170963,
    "deleted": false
}

## Put Api
4 Update task

**url: /v1/task**

_*Request Json : *_

{
    "id": 2,
    "user_name": "cerb_usr_001",
    "task_description": "Task 2 updated"
}

Response will be status code 200 with updated task

_*Response Json : *_

{
    "id": 2,
    "user_name": "cerb_usr_001",
    "task_description": "Task 2 updated",
    "modifiedDate": 1591102817432
}

## Delete Api
5 Delete Task

**url: /v1/task/{taskId}**

Response will be status code 200 with deleted task

_*Response Json : *_

{
    "id": 2,
    "userId": 1,
    "user_name": "cerb_usr_001",
    "task_description": "Task 2 updated",
    "createdDate": 1591093906000,
    "modifiedDate": 1591102817000,
    "deleted": true
}

## Database details 

**database url : http://localhost:8090/h2-console/**

JDBC Url : jdbc:h2:mem:testdb

user : sa

password : password 


--------------------------------------------------------------------------------
Docker commands:
1) SpringBoot App: docker run -p 8090:8090 --name todoappbe escortnotice/todoappbe  

2) NodeJS BFF Layer: docker run --env BASEURL=http://todoappbe:8090/todoapp/api/v1/task/  -p 4000:4000 --name todoappbff --link todoappbe escortnotice/todoappbff

3) React UI: docker run -p 3000:3000 --name todoappui --restart=always --link todoappbff escortnotice/todoappui