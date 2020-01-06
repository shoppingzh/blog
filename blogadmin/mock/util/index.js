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
    data: (data || []).slice(begin, pageSize > 0 ? begin + pageSize : data.length - 1)
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

export default {
  page,
  findById
}
