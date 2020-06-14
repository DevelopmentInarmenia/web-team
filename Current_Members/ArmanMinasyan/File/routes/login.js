const route = require('express').Router();
const user = require('../models/user');
const jwt = require('jsonwebtoken');
route.post('/login', async (req, res) => {
    let data = req.body;
    user.findOne({ username: data.username }, function (err, result) {
        if (err) res.send('Error').status(500);
        if (result) {
            if (result.password == data.password) {
                let jwt_sign = jwt.sign({
                    username: result.username,
                    id: result.id
                }, 'test');


                //res.setHeader('token', jwt_sign);
                 res.cookie('token', jwt_sign);
                
                res.send('Login').status(200);
            } else res.send('Incorrect password').status(500);
        } else res.send('Incorrect username').status(500);
    })
})


module.exports = route;
