import request from '@/utils/request'

export function tree() {
  return request({
    url: '/category',
    method: 'get'
  })
}

export function children(pid) {
  return request({
    url: '/category/children/' + (pid || ''),
    method: 'get'
  })
}

export function save(data) {
  return request({
    url: '/category/' + (data.id || ''),
    method: 'post',
    data
  })
}

export function remove(id) {
  return request({
    url: '/category/' + (id || ''),
    method: 'delete'
  })
}
