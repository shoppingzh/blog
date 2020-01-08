import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/tag/list',
    method: 'get',
    params
  })
}

export function get(params) {
  return request({
    url: '/tag/' + params.id,
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/tag',
    method: 'post',
    params
  })
}

export function remove(params) {
  return request({
    url: '/tag',
    method: 'delete',
    params
  })
}
