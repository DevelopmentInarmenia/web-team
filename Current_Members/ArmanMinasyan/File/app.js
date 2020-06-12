const express = require('express'),
    app = express();


const body_parser = require('body-parser');

app.use(body_parser.json());
app.use(body_parser.urlencoded({ extended: true }));


app.use('/user', require('./routes/get'));
app.use('/user', require('./routes/insert'));
app.use('/user', require('./routes/update'));
app.use('/user', require('./routes/delete'));


app.listen(3000, function () {
    console.log('Start ...');
})