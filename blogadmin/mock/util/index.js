export function page(data, params) {
  let page = parseInt(params.page || '1')
  const pageSize = parseInt(params.pageSize || '0')
  if (page <= 0) {
    page = 1
  }
  const begin = (page - 1) * pageSize
  return {
    page: page,
    total: data.length,
    pageSize: pageSize,
    data: (data || []).slice(begin, pageSize > 0 ? begin + pageSize : data.length)
  }
}

export function findById(data, id) {
  if (!data) {
    return null
  }
  return data.find((obj) => {
    return obj.id === id
  })
}

/**
 * 从URL中提取参数
 * 如使用正则表达式//article/\d+/gi从“/article/2”中提取参数“2”
 * @param {String} url  URL
 * @param {String} pattern 正则表达式
 */
export function extractParam(url, pattern, group) {
  const regex = new RegExp(pattern, 'gi')
  let result
  while ((result = regex.exec(url)) !== null) {
    return result[group]
  }
  return null
}

export default {
  page,
  findById,
  extractParam
}
