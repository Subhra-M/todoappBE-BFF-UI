const fs = require('fs');
const path = require('path');

function getDataPath() {
    return path.join(__dirname, '../', 'data/todos.json');
}

function readFileContent(cb) {
    const p = getDataPath();
        console.log('adfjjda', p);
    fs.readFile(p, (err, content) => {
        if (err) {
            console.log(err)
            cb([]);
        }        
        cb(JSON.parse(content));
    });  
}



module.exports = {
    getDataPath,
    readFileContent
}