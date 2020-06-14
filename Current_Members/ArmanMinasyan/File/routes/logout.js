const route = require('express').Router();
route.delete('/logout', async (req, res) => {
    res.status(200).clearCookie('token');
    res.end('Logout');
})


module.exports = route;
