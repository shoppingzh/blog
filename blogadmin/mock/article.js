/* eslint-disable no-return-assign */
import Mock from 'mockjs'
import lodash from 'lodash'
import { page } from './util'

const all = Mock.mock({
  'data|2': [{
    'id|+1': 1,
    'title|3-5': /\w\W\s\S\d\D/,
    'summary|5': /\w{15}/,
    'content|5-30': /\w{30}/,
    'createTime': '@datetime',
    'draft|2-5': true
  }]
})

export default [
  {
    url: '/article/list',
    type: 'get',
    response: conf => {
      var data = all.data.slice(0).reverse()
      return {
        success: true,
        code: 20000,
        data: page(data, conf.query)
      }
    }
  },
  {
    url: '/article/get',
    type: 'get',
    response(conf) {
      const article = all.data.find((obj) => {
        return obj.id == conf.query.id
      })
      if (!article) {
        return {
          success: false,
          code: 20000,
          data: null
        }
      }
      return {
        success: true,
        code: 20000,
        data: article
      }
    }
  },
  {
    url: '/article/remove',
    type: 'post',
    response(conf) {
      const id = conf.query.id
      lodash.remove(all.data, (obj) => {
        return obj.id == id
      })
      return {
        success: true,
        code: 20000,
        data: null
      }
    }
  },
  {
    url: '/article/save',
    type: 'post',
    response(conf) {
      const data = all.data
      let article
      if (conf.body.id) {
        article = data.find((obj) => {
          return obj.id == conf.body.id
        })
      }
      if (article) {
        article.title = conf.body.title
        article.content = conf.body.content
        article.summary = conf.body.plainContent.slice(0, 75) + '...'
      } else {
        const last = data[data.length - 1]
        all.data.push({
          id: last ? (last.id + 1) : 1,
          title: conf.body.title,
          summary: conf.body.plainContent.slice(0, 75) + '...',
          content: conf.body.content,
          createTime: new Date()
        })
      }
      return {
        success: true,
        code: 20000,
        data: null
      }
    }
  }
]
