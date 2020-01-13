import request from '@/util/request'

export function page (params) {
  return request({
    url: '/article',
    params
  })
}
