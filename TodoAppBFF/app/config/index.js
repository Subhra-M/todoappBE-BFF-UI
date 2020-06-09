const dotenv = require('dotenv');
dotenv.config({path: __dirname + '/.env'});

module.exports = {
    baseUrl: process.env.BASEURL,
    port: process.env.PORT,
    hostname: process.env.HOST,
    environment: process.env.NODE_ENV
}