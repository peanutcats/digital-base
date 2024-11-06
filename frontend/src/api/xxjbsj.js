import axios from 'axios'

// 创建axios实例
const request = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000
})

// 响应拦截器
request.interceptors.response.use(
  response => {
    return response
  },
  error => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

export const getXxjbsjList = () => {
  return request.get('/xxjbsj/list')
} 