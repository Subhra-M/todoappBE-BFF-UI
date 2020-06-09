const express = require('express');
const bodyParser = require('body-parser');
require('dotenv').config();

const app = express();
const PORT = process.env.PORT || 3000;
const hostname = process.env.HOST;

app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header("Content-type", "application/json; charset=UTF-8");
    next();
  });
  
app.use(bodyParser.urlencoded({ extended: true }));

app.use(bodyParser.json());

app.get('/', (req, res) => {
    res.json({"message": "Welcome to EasyNotes application. Take notes quickly. Organize and keep track of all your notes."})
});

require('./app/routes/todo.routes.js')(app);

app.listen(PORT, hostname, () => {
    console.log('Server is listening on port ', PORT, 'ENV-', process.env.NODE_ENV);
    console.log( 'BASEURL - ' , process.env.BASEURL);
})