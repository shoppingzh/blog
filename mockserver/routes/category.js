var express = require('express');
var db = require('../utils/db')
var _ = require('lodash')
var { page, findById, lastId, result } = require('../utils/api')

var router = express.Router();

function iterateTree (list) {
    list.forEach((obj) => {
        var children = []
        db.category.forEach((cat) => {
            if(cat.parent === obj.id) {
                children.push(cat)
            }
        })
        obj.children = children
        if(children.length) {
            iterateTree(children)
        }
    })
}

router.get('/',  function(req, res,  next) {
    var list = []
    db.category.forEach((cat) => {
        if(!cat.parent) {
            list.push(cat)
        }
    });
    iterateTree(list)
    res.send(result(true, list))
})

router.get('/children/:id?', function(req, res, next){
    const children = []
    db.category.forEach((obj) => {
        if(obj.parent === parseInt(req.params.id) || (!req.params.id  && obj.parent === null)){
            children.push(obj)
        }
    })
    setTimeout(() => {
        res.send(result(true, children))
    }, 500);
})

router.get('/:id', function(req, res, next) {
    var cat = db.category.find((obj) => {
        return obj.id === parseInt(req.params.id)
    })
    if(!cat) {
        return res.send(result(false, null, '不存在的分类'))
    }
    return res.send(result(true, cat))
})

router.post('/:id?', function(req, res, next) {
    let cat
    if(req.params.id) {
        cat = db.category.find((obj) => {
            return obj.id === parseInt(req.params.id)
        })
    }
    if(!cat) {
        cat = {
            id: lastId(db.category),
            createTime: new Date()
        }
        db.category.push(cat)
    }
    cat.name = req.body.name
    cat.parent = req.body.parent || null

    res.send(result(true, cat.id))
})

router.delete('/:id', function(req, res, next) {
    _.remove(db.category, (obj) => {
        return obj.id === parseInt(req.params.id)
    })
    res.send(result(true))
})

module.exports = router;
