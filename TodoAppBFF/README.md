# TodoApp

# To setup and run in local system follow the below commands
1. clone the repo - https://github.ibm.com/tahmed47/TodoAppBFF.git
2. npm install
3. npm start


# Routes are

1. Post a task 
    url - http://localhost:3000/todos
    method - POST
    payload - {
        "user_name": "cerb_usr_001",
        "task_description":"Task 2 updated"
    }

2. Get all tasks
    url - http://localhost:3000/todos
    method - GET

3. Update a task
    url - http://localhost:3000/todos/:todoId
    method - PUT
    payload - {
        "user_name": "cerb_usr_001",
        "task_description":"Task 2 updated"
    }

4. Delete a task
    url - http://localhost:3000/todos/:todoId
    method - DELETE


5. Docker commands:

Build the image: docker build -t todoappbff .
Run the container: docker run --env BASEURL=http://todoappbe:8090/todoapp/api/v1/task  -p 4000:4000 --name todoappbff --link todoappbe escortnotice/todoappbff

here the todoappbe is the name of the backend app (springboot app)