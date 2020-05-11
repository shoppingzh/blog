import http from '@/utils/http'

export function getNew(page, pageSize, data) {
  return http.request({
    url: '/article',
    params: Object.assign({
      page,
      pageSize
    }, data)
  })
}

export function statWithDay() {
  return http.request({
    url: '/article/stat/day'
  })
}

export function get(id) {
  return http.request({
    url: '/article/' + id,
    method: 'get'
  })
}

