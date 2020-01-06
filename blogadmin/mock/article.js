/* eslint-disable no-return-assign */
// import Mock from 'mockjs'
import lodash from 'lodash'
import util from './util'
import { data as  tagData } from './tag'

// const all = Mock.mock({
//   'data|2': [{
//     'id|+1': 1,
//     'title|3-5': /\w{5}\s?/,
//     'summary|5': /\w{15}/,
//     'content|5-30': /\w{30}/,
//     'createTime': '@datetime',
//     'draft|2-5': true
//   }]
// })

const all = {
  data: []
}

export default [
  {
    url: '/article/list',
    type: 'get',
    response: conf => {
      var data = all.data.slice(0).reverse()
      const page = util.page(data, conf.query)
      console.log(page)
      if (page.data && page.data.length) {
        page.data.forEach((obj) => {
          const tags = []
          if (obj.tagIds && obj.tagIds.length) {
            obj.tagIds.forEach((tagId) => {
              const tag = util.findById(tagData, tagId)
              if (tag) {
                tags.push(tag.name)
              }
            })
            obj.tags = tags
          }
        })
      }
      return {
        success: true,
        code: 20000,
        data: page
      }
    }
  },
  {
    url: '/article/get',
    type: 'get',
    response(conf) {
      const article = all.data.find((obj) => {
        return obj.id === parseInt(conf.query.id)
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
        return obj.id === parseInt(id)
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
          return obj.id === parseInt(conf.body.id)
        })
      }
      if (!article) {
        const last = data[data.length - 1]
        article = {
          id: last ? last.id + 1 : 1,
          createTime: new Date(),
          draft: false
        }
        all.data.push(article)
      }

      article.title = conf.body.title
      article.summary = conf.body.plainContent.slice(0, 75) + '...'
      article.content = conf.body.content

      // 这里的tags为标签的name数组
      const tags = conf.body.tags
      if (tags && tags.length) {
        const tagIds = []
        // 遍历所有标签，如果标题与关联标签的标题一致，则直接使用，否则创建新标签
        tags.forEach((tag) => {
          let exist = tagData.find((obj) => {
            return obj.name === tag
          })
          if (!exist) {
            const lastTag = tagData[tagData.length - 1]
            exist = {
              id: lastTag ? lastTag.id + 1 : 1,
              name: tag,
              createTime: new Date()
            }
            tagData.push(exist)
          }
          tagIds.push(exist.id)
        })
        article.tagIds = tagIds
      }

      return {
        success: true,
        code: 20000,
        data: null
      }
    }
  }
]
