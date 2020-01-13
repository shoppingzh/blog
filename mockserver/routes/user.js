var express = require('express');
var Mock = require('mockjs')
var db = require('../utils/db')

var router = express.Router();
router.post('/login', function(req, res, next) {
  res.send({
    code: 20000,
    data: {
      token: 'admin-token'
    }
  })
})

router.get('/info', function(req, res, next) {
  res.send({
    code: 2000,
    data: db.user[req.query.token]
  })
})

module.exports = router;
