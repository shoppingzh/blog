import axios from 'axios'

const service = axios.create({
  baseURL: 'http://localhost:3000',
  timeout: 5000
  // withCredentials: true // 支持跨域
})

export default service
