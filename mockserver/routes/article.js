var express = require('express');
var db = require('../utils/db')
var _ = require('lodash')
var { page, findById, lastId, result } = require('../utils/api')

var router = express.Router();
var data = db.article

router.get('/', function(req, res, next) {
  setTimeout(() => {
    res.send(result(true, page(data, req.query)))
  }, 500);
})

router.get('/:id', function(req, res, next) {
  var id = req.params.id 
  if (!id) {
    res.send(result(false, null, '参数错误'))
  } else {
    var article = data.find((obj) => {
      return obj.id === parseInt(id)
    })
    if (!article) {
      res.send(result(false, null, '不存在的文章'))
    } else {
      res.send(result(true, article))
    }
  }
})

router.post('/:id?', function(req, res, next) {
  var entity
  if (req.body.id) {
    entity = findById(data, req.body.id)
  }
  if (!entity) {
    entity = {
      id: lastId(data),
      createTime: new Date(),
      draft: false
    }
    data.push(entity)
  }
  entity.title = req.body.title
  entity.content = req.body.content
  entity.plainContent = req.body.plainContent
  if (entity.plainContent) {
    var summary = entity.plainContent.slice(0, 75)
    entity.summary = summary + (entity.plainContent.length > 75 ? '...' : '')
  }

  res.send(result(true))
})

router.delete('/:id', function(req, res, next) {
  _.remove(data, (obj) => {
    return obj.id === parseInt(req.params.id)
  })
  res.send(result(true))
})

module.exports = router;
