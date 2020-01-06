import Mock from 'mockjs'
import lodash from 'lodash'
import util from './util'

const data = [
  {
    id: 1,
    name: 'vue'
  },
  {
    id: 2,
    name: 'vue router'
  },
  {
    id: 3,
    name: 'webpack'
  }
]

// const data = Mock.mock({
//   'data|100': [{
//     'id|+1': 1,
//     'name|1': /\w{5}\s?/
//   }]
// }).data

export {
  data
}

export default [
  {
    url: '/tag/list',
    type: 'get',
    response(conf) {
      const orderData = data.slice(0).reverse()
      return {
        success: true,
        code: 20000,
        data: util.page(orderData, conf.query)
      }
    }
  },
  {
    url: '/tag/get',
    type: 'get',
    response(conf) {
      if (!conf.query.id) {
        return {
          success: false,
          code: 20000,
          msg: '参数错误'
        }
      }
      const entity = util.findById(data, parseInt(conf.query.id))
      return {
        success: entity !== null,
        code: 20000,
        data: entity
      }
    }
  },
  {
    url: 'tag/save',
    type: 'post',
    response(conf) {
      const nameEntity = data.find((obj) => {
        return obj.name === conf.query.name.trim()
      })
      if (nameEntity) {
        return {
          success: false,
          code: 20000,
          msg: '该标签已存在'
        }
      }

      let entity
      if (conf.query.id) {
        entity = util.findById(data, parseInt(conf.query.id))
      }
      if (entity) {
        entity.name = conf.query.name
      } else {
        const last = data[data.length - 1]
        entity = {
          id: last ? last.id + 1 : 1,
          name: conf.query.name
        }
        data.push(entity)
      }
      return {
        success: true,
        code: 20000,
        data: {
          id: entity.id
        }
      }
    }
  },
  {
    url: '/tag/remove',
    type: 'post',
    response(conf) {
      if (!conf.query.id) {
        return {
          success: false,
          code: 20000,
          msg: '参数错误'
        }
      }
      lodash.remove(data, (entity) => {
        return entity.id === parseInt(conf.query.id)
      })
      return {
        success: true,
        code: 20000
      }
    }
  }
]
