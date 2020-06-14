const route = require('express').Router();
const fs = require('fs');
const path = require('path');
const valid = require('../config/valid');

route.delete('/delete_data', valid, function (req, res) {

    const file_name = req.body.file_name;
    let path_file = path.resolve(__dirname + '/..' + '/users/' + file_name);
    if (fs.existsSync(path_file)) fs.unlink(path_file, function (err) {
        if (!err) res.send('Data deleted');
    }); else res.send('Data not found');
})

module.exports = route;