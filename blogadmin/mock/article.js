/* eslint-disable no-return-assign */
import Mock from 'mockjs'
import lodash from 'lodash'
import { page } from './util'

const all = Mock.mock({
  'data|666': [{
    'id|+1': 1,
    'title|3-5': /\w\W\s\S\d\D/,
    'summary|5': /\w{15}/,
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
  }
]
