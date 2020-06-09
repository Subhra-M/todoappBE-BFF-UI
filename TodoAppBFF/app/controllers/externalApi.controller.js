const rp = require('request-promise');
const {baseUrl} = require('../config/index');

async function postTodo(postContent) {
    console.log("checking post req to external api");
    try {
        const options = {
            method: 'POST',
            uri: `${baseUrl}`,
            body: postContent,
            json: true,   
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }

        const extReq = await rp(options);
        console.log('REQ', extReq);        
        return extReq;
    }catch (err) {
        console.log('Err', err);
        return err;
    }   
}

function callExternalApi() {
    console.log("Checking external api");
    try {
        const options = {
            method: 'GET',
            uri: `${baseUrl}`            
        }
        const todos = rp(options);        
        return todos;
    } catch (err) {
        console.log('todo GET err', err);
        return err;
    };    
}

async function updateTodo_id(todoID, postContent) {
    console.log("checking post req to external api");
    try {
        const options = {
            method: 'PUT',
            uri: `${baseUrl}` + todoID,
            body: postContent,
            json: true,   
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }

        const extReq = await rp(options);
        console.log('REQ UPDATE', extReq);        
        return extReq;
    }catch (err) {
        console.log('Err', err);
        return err;
    }   
}

async function updateTodo(postContent) {
    console.log("checking post req to external api");
    try {
        const options = {
            method: 'PUT',
            uri: `${baseUrl}`,
            body: postContent,
            json: true,   
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }

        const extReq = await rp(options);
        console.log('REQ UPDATE', extReq);        
        return extReq;
    }catch (err) {
        console.log('Err', err);
        return err;
    }   
}

async function deleteTodo(todoID) {
    console.log("checking delete req to external api: " + todoID);
    try {
        const options = {
            method: 'DELETE',
            uri: `${baseUrl}` + todoID,           
        }

        const extReq = await rp(options);
        console.log('REQ DELETE', extReq);        
        return extReq;
    }catch (err) {
        console.log('Err', err);
        return err;
    }   
}

module.exports = {
    callExternalApi,
    postTodo,
    updateTodo,
    deleteTodo,
    updateTodo_id
}