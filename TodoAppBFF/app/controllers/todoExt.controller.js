let todos = [];

function createTodo(todoDetails) {     
    let newTodoID = 0;
    if (todos.length > 0) {
        newTodoID = todos.length;
    }    

    let newTodo = {
        id: newTodoID,
        task_description: todoDetails.task_description,
        user_name: todoDetails.user_name,
        createdDate: new Date()
    }

    todos = [...todos, newTodo];
    
    return {
        message: 'successfully added item',
        todos: todos
    }
    
};

function getTodos() {            
    return todos;
};

function getTodoByID(todoID) {    
     
    let selectedTodo;   
    todos.forEach(todo => {        
        if (todo.id == todoID) {
            selectedTodo = todo;
        }
    });
    if (selectedTodo == undefined) {
        return {
            message: 'Given todo Id is wrong. Please check'
        }
    }
    return selectedTodo;
};

function updateTodo(todoID, payload) {

    todos.forEach(todo => {
        if (todo.id == todoID) {
            todo.task_description = payload.task_description
        }
    })
    
    return {
        message: 'successfully updated todo',
        todos: todos
    };
};

function deleteTodo(todoID) {
    return todos.filter(todo => todo.id != todoID);
};

module.exports = {
    createTodo,
    getTodos,
    getTodoByID,
    updateTodo,
    deleteTodo
}