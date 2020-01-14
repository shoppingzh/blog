var Mock = require('mockjs')

var user = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

// var tag = Mock.mock({
//   'data|20': [{
//     'id|+1': 1,
//     'name': /\w{5}\s?/
//   }]
// }).data

var tag = [
  {
    id: 1,
    name: 'Vue'
  },
  {
    id: 2,
    name: 'Vue Router'
  },
  {
    id: 3,
    name: 'Vuex'
  }
]

// var article = Mock.mock({
//   'data|200': [{
//     'id|+1': 1,
//     'title|3-5': /\w{5}\s?/,
//     'summary|5': /\w{15}/,
//     'content|5-30': /\w{30}/,
//     'createTime': '@datetime',
//     'draft|2-5': true,
//     'preview': '@image'
//   }]
// }).data
var article = []

var article_tag = {}

module.exports = {
  user,
  tag,
  article,
  article_tag
}
