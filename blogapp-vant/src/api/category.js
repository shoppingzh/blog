import request from '@/util/request'

export function children (pid) {
  return request({
    url: '/category/children/' + (pid || ''),
    method: 'get'
  })
}
