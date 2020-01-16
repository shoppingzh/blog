import request from '@/utils/request'

export function tree() {
  return request({
    url: '/category',
    method: 'get'
  })
}
