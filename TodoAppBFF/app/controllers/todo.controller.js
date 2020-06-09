const fs = require('fs');
const path = require('path');
const helper  = require('./helpers.js'); 

function createTodo(todoDetails) { 
    const p = helper.getDataPath();
    let newTodoID = 0;

    const Todo = {
        id: newTodoID,
        task_description: todoDetails.task_description,
        user_name: todoDetails.user_name,
        createdDate: new Date()
    }


    fs.readFile(p, (err, content) => {
        let todos = [];        
        if (!err) {
            if (content) {
                todos = JSON.parse(content);
            }            
        }
        
        if (todos) {
            newTodoID = todos.length + 1;
        }else {
            newTodoID++;
        }
        Todo.id = newTodoID;
        
        todos.push(Todo);        
        
        fs.writeFile(p, JSON.stringify(todos), (err) => {
            if (!err) {
                return {
                    message: 'todo added successfully'
                }
            }else {
                return err
            }        
        });
    });
    
};

async function getTodos(cb) {            
    const p = helper.getDataPath();

    fs.readFile(p, (err, content) => {
        if (err) {
            console.log(err)
            cb([]);
        }        
        cb(JSON.parse(content));
    }); 
   
};

async function getTodoByID(todoID) {    
    let todo;
    await getTodos(todos => {
        todo = todos.find(x => x.id == todoID);
        console.log('todo', todoID, todo);
        return todo;
    });

};

function updateTodo(todoID) {
    if (!req.body.content) {
        return res.status(400).send({
            message: "Todo content cannot be empty"
        });
    }

    const todoId = req.param.todoId;

};

function deleteTodo(todoID) {
    const todoId = req.param.todoId;
};

module.exports = {
    createTodo,
    getTodos,
    getTodoByID,
    updateTodo,
    deleteTodo
}