import http from '@/utils/http'

export function list() {
  return http({
    url: '/tag',
    method: 'get'
  })
}
