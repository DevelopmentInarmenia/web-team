const express = require('express'),
    app = express();


const body_parser = require('body-parser');
const cookie_parser = require('cookie-parser');
const mongoose = require('mongoose');
const valid = require('./config/valid');

app.use(body_parser.json());
app.use(body_parser.urlencoded({ extended: true }));
app.use(cookie_parser());

mongoose.connect('mongodb://localhost:27017/example', {
    useNewUrlParser: true,
    useUnifiedTopology: true
})

app.use('/user', require('./routes/get'));
app.use('/user', require('./routes/insert'));
app.use('/user', require('./routes/update'));
app.use('/user', require('./routes/delete'));
app.use('/user', require('./routes/reg'));
app.use('/user', require('./routes/login'));
app.use('/user', require('./routes/logout'));

app.listen(3000, function () {
    console.log('Start ...');
})