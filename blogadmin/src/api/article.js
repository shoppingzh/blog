import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/article/list',
    method: 'get',
    params
  })
}

export function remove(id) {
  return request({
    url: '/article/remove',
    method: 'post',
    params: {
      id: id
    }
  })
}
