import axios from 'axios'

const http = axios.create({
  baseURL: 'http://localhost:8888/api',
  timeout: 5000
})

http.interceptors.response.use((resp) => {
  return resp.data
}, (err) => {
  return Promise.reject(err)
})

export default http
