import request from '@/util/request'

export function all () {
  return request({
    url: '/tag'
  })
}
