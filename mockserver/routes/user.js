var express = require('express');
var Mock = require('mockjs')
var db = require('../utils/db')

var router = express.Router();
router.post('/login', function(req, res, next) {
  setTimeout(() => {
    res.send({
      code: 20000,
      data: {
        token: 'admin-token'
      }
    })
  }, 1500);
})

router.get('/info', function(req, res, next) {
  res.send({
    code: 20000,
    data: db.user[req.query.token]
  })
})

router.post('/logout', function(req, res, next) {
  res.send({
    code: 20000,
    data: 'success'
  })
})

module.exports = router;
