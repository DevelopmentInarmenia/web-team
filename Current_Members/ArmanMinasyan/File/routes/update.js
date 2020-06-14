const route = require('express').Router();
const fs = require('fs');
const path = require('path');
const valid = require('../config/valid');

route.put('/update_data', valid, function (req, res) {
    const file_name = req.body.file_name;
    const data = req.body.data;

    let path_file = path.resolve(__dirname + '/..' + '/users/' + file_name);

    if (fs.existsSync(path_file))
        fs.appendFileSync(path_file, ' ' + data);
    else res.send('Data not found');
    res.send('Data updated');
})

module.exports = route;