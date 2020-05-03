import axios from 'axios'
import { Notify } from 'vant'

const service = axios.create({
  baseURL: 'http://localhost:8888/api',
  timeout: 5000
  // withCredentials: true // 支持跨域
})

function requestError (msg) {
  Notify({
    type: 'warning',
    message: `操作失败：${msg || '未知错误'}`,
    duration: 1500
  })
}

export default function (conf) {
  return new Promise((resolve, reject) => {
    service(conf).then((resp) => {
      const result = resp.data
      if (result.success) {
        resolve(result.data)
      } else {
        requestError(result.msg)
        reject(result.msg || '')
      }
    }).catch((e) => {
      requestError('服务器错误')
    })
  })
}
