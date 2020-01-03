var express = require('express');
var Mock = require('mockjs')
var router = express.Router();

/* GET users listing. */
router.get('/:id?', function(req, res, next) {
  res.send(Mock.mock({
      'list|1-10': [{
          'id|+1': 1
      }]
  }));
});

module.exports = router;
