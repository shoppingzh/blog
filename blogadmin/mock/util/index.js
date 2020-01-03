export function page(data, params) {
  let page = parseInt(params.page || '1')
  let pageSize = parseInt(params.pageSize || '10')
  if (page <= 0) {
    page = 1
  }
  if (pageSize < 0) {
    pageSize = 10
  }
  const begin = (page - 1) * pageSize
  const end = begin + pageSize
  return {
    page: page,
    total: data.length,
    list: (data || []).slice(begin, end)
  }
}
