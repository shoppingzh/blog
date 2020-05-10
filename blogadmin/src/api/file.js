import request from '@/utils/request'

export function upload(file) {
  const formData = new FormData()
  formData.append('file', file, file.name)
  return request({
    url: '/file',
    method: 'POST',
    data: formData
  })
}
