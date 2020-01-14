import request from './request'
import { Message } from 'element-ui'

function showError(message) {
  Message({
    message: message || '未知错误',
    type: 'error'
  })
}

export default function(conf) {
  return new Promise((resolve, reject) => {
    request(conf).then((result) => {
      if (result.success) {
        resolve(result.data)
      } else {
        showError(result.msg)
        reject(result.msg || '')
      }
    }).catch((e) => {
      showError('服务器错误')
    })
  })
}
