function page(list, query) {
  var page = parseInt(query.page || '1')
  var pageSize = parseInt(query.pageSize || '0')
  var begin = (page - 1) * pageSize
  list = list.slice(0, list.length).reverse()
  return {
    page: page,
    pageSize: pageSize,
    total: list.length,
    data: list.slice(begin, pageSize > 0 ? begin + pageSize : list.length)
  }
}

function result(success, data, msg) {
  return {
    success,
    code: 20000,
    data,
    msg
  }
}

function findById(list = [], id) {
  return list.find((obj) => {
    return obj.id === parseInt(id)
  })
}

function lastId(list = []) {
  var last = list[list.length - 1]
  return last ? (last.id + 1) : 1
}

module.exports = {
  page,
  result,
  findById,
  lastId
}
