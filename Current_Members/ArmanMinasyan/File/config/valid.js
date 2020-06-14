const jwt = require('jsonwebtoken');


let valid = (req, res, next) => {
    if (req.cookies.token) {
        jwt.verify(req.cookies.token, 'test', function (err, decoded) {
            if (err) res.status(401).send('Unauthorized');
            if (decoded) {
                next();
            } res.status(401).send('Unauthorized');
        })
    } else res.status(401).send('Unauthorized');
}

module.exports = valid;




