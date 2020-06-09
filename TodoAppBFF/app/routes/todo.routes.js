module.exports = (app) => {
    // const todosController = require('../controllers/todoExt.controller.js');    
    const todosControllerExt = require('../controllers/externalApi.controller.js');

    //create a new todo
    app.post('/todos', (req, res, next) => {
        if (!req.body) {
            res.status(400).send({
                message: 'Invalid Request'
            })
        }
        
        todosControllerExt.postTodo(req.body).then(resp => {
            console.log('EXT RESP', resp);
        });
        
        res.status(200).send({
            message: 'successfully added a todo'
        });
    });

    //Retrieve all todos
    app.get('/todos', (req, res, next) => {
        console.log("testing todos");
        let todoList; //= todosController.getTodos();
        todosControllerExt.callExternalApi().then(resp => {            
            todoList = resp;
                        
            if (todoList.length > 0) {
                res.status(200).send(todoList);
            }else if (todoList && todoList.length == 0){
                res.status(200).send({
                    message: 'There is no item in task list'
                })
            }else {
                res.status(500).send({
                    message: 'Internal server error'
                })
            }
        });
  
        

    });

    

    //get a single todo with Todo Id
    // app.get('/todos/:todoId', (req, res, next) => {        
    //     const todo = todosController.getTodoByID(req.params.todoId);
    //     return res.send(todo);
    // });

    //Update a todo with todoId
    app.put('/todos/:todoId', (req, res, next) => {
        if (!req.body) {
            res.status(400).send({
                message: 'Invalid Request'
            })
        }
        // const todo = todosController.updateTodo(req.params.todoId, req.body);
        // return res.send(todo);

        let updateTodoResp;
        todosControllerExt.updateTodo_id(req.body).then(resp => {
            console.log('EXT RESP', resp);
            updateTodoResp = resp;

            res.status(200).send(updateTodoResp);
        });
        
        
    });

    //Update a todo
    app.put('/todos/', (req, res, next) => {
        if (!req.body) {
            res.status(400).send({
                message: 'Invalid Request'
            })
        }
        // const todo = todosController.updateTodo(req.params.todoId, req.body);
        // return res.send(todo);

        let updateTodoResp;
        todosControllerExt.updateTodo(req.body).then(resp => {
            console.log('EXT RESP', resp);
            updateTodoResp = resp;

            res.status(200).send(updateTodoResp);
        });
        
        
    });

    //Delete a todo with todoId
    app.delete('/todos/:todoId', (req, res, next) => {
        // const todo = todosController.deleteTodo(req.params.todoId);
        // return res.send(todo);

        todosControllerExt.deleteTodo(req.params.todoId).then(resp => {
            console.log('DELETE RESP', resp);             

            res.status(200).send({
                message: 'success'
            });
        });
    });

} 