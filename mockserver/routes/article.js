var express = require('express');
var db = require('../utils/db')
var _ = require('lodash')
var { page, findById, lastId, result } = require('../utils/api')

var router = express.Router();
var data = db.article

function articleDTO(article) {
  const dto = Object.assign({}, article)
  const tags = db.article_tag[dto.id]
  if (tags) {
    dto.tags = tags.slice(0, tags.length)
  }
  if (dto.category) {
    const category = db.category.find((cat) => {
      return cat.id === dto.category
    })
    dto.category = category
  }
  return dto
}

router.get('/', function(req, res, next) {
  setTimeout(() => {
    const p = page(data, req.query)
    if (p.data.length) {
      const list = []
      p.data.forEach((article) => {
        list.push(articleDTO(article))
      })
      p.data = list
    }
    res.send(result(true, p))
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
      return res.send(result(false, null, '不存在的文章'))
    }
    res.send(result(true, articleDTO(article)))
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
      createTime: new Date()
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
  var tagNames = req.body.tags
  if (tagNames && tagNames.length) {
    var tags = []
    tagNames.forEach((tagName) => {
      var exist = db.tag.find((tag) => {
        return tag.name === tagName
      })
      if (!exist) {
        exist = {
          id: lastId(db.tag),
          name: tagName
        }
        db.tag.push(exist)
      }
      tags.push(exist)
    })
    db.article_tag[entity.id] = tags
  } else {
    db.article_tag[entity.id] = []
  }
  // 设置分类
  if (req.body.category) {
    entity.category = req.body.category
  }
  entity.draft = req.body.draft || false

  res.send(result(true))
})

router.delete('/:id', function(req, res, next) {
  _.remove(data, (obj) => {
    return obj.id === parseInt(req.params.id)
  })
  res.send(result(true))
})

// 发布文章
router.post('/publish/:id', function(req, res, next) {
  const article = db.article.find((obj) => {
    return obj.id === parseInt(req.params.id)
  })
  if (!article) {
    return res.send(result(false, null, '不存在的文章'))
  }
  article.draft = false
  res.send(result(true))
})

module.exports = router;
