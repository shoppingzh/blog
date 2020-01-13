import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/tag',
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
    url: `/tag/${params.id || ''}`,
    method: 'post',
    params
  })
}

export function remove(id) {
  return request({
    url: `/tag/${id}`,
    method: 'delete'
  })
}
