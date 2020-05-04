import request from '@/utils/request'

export function list(params) {
  return request({
    url: '/article',
    method: 'get',
    params
  })
}

export function remove(id) {
  return request({
    url: '/article/' + id,
    method: 'delete'
  })
}

export function save(data) {
  return request({
    url: '/article',
    method: data.id ? 'put' : 'post',
    data
  })
}

export function get(id, params = {}) {
  return request({
    url: '/article/' + id,
    method: 'get',
    params
  })
}

export function publish(id) {
  return request({
    url: '/article/publish/' + id,
    method: 'post'
  })
}
