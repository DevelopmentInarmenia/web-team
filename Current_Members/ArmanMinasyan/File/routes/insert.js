const route = require('express').Router();
const fs = require('fs');
const path = require('path');
route.post('/insert_data', function (req, res) {

    const { file_name, data } = req.body;
    let path_file = path.resolve(__dirname + '/..' + '/users/' + file_name);
    let path_folder = path.resolve(__dirname + '/..' + '/users');

    if (!fs.existsSync(path_folder)) fs.mkdirSync(path_folder);
    fs.writeFile(path_file, data, function (err) {
        if (err) res.send('There are errors'); else res.send('Data inserted');
    });
})

module.exports = route;