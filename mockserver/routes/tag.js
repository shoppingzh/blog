var express = require('express');
var db = require('../utils/db')
var _ = require('lodash')
var { page, result } = require('../utils/api')

var router = express.Router();
var data = db.tag

router.get('/', function(req, res, next) {
  res.send(result(true, page(data, req.query)))
})

router.post('/:id?', function(req, res, next) {
  var exist
  if (req.params.id) {
    exist = data.find((obj) => {
      return obj.id === parseInt(req.params.id)
    })
  } else {
    const nameExist = data.find((obj) => {
      return obj.name === req.query.name
    })
    if (nameExist) {
      return res.send(result(false, null, '已存在该标签'))
    }
  }
  if (!exist) {
    var last = data[data.length - 1]
    exist = {
      id: last ? last.id + 1 : 1
    }
    data.push(exist)
  }
  exist.name = req.query.name
  exist.theme = req.query.theme
  res.send(result(true, exist.id))
})

router.delete('/:id', function(req, res, next) {
  _.remove(data, (obj) => {
    return obj.id === parseInt(req.params.id)
  })
  res.send(result(true))
})

module.exports = router
