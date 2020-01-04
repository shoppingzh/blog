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
    'createTime': '@datetime'
  }]
})

export default [
  {
    url: '/article/list',
    type: 'get',
    response: conf => {
      return {
        success: true,
        code: 20000,
        data: page(all.data, conf.query)
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
      all.data.unshift({
        id: '11',
        title: conf.query.title,
        summary: conf.query.content.slice(0, 75),
        content: conf.query.content,
        createTime: new Date()
      })
      return {
        success: true,
        code: 20000,
        data: null
      }
    }
  }
]
