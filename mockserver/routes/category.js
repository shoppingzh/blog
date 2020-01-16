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

module.exports = router;
