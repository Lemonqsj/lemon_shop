import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/fonts/iconfont.css'

import axios from 'axios'
import moment from 'moment'

import Router from 'vue-router'
const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error => error)
}

Vue.filter('dateFormat', function(data, formateStr) {
  console.log('----------' + data)
  return moment(data).format(formateStr)
})

axios.defaults.baseURL = 'http://127.0.0.1:7777'

axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config
})

axios.interceptors.response.use(
  res => {
    console.log('---info message-----', res.data)
    console.log('---info message-----', res.data.code)
    if (res) {
      if (res.data.code === 201) {
        // router.push({ name: '/login' })
        router.replace({
          path: '/login'
          // query: {redirect: router.currentRoute.fullPath}//登录成功后跳入浏览的当前页面
        })
      }
      return res
    }
  },
  err => {
    console.log('--err message--', err.data)
    return Promise.reject(err)
  }
)

Vue.prototype.$dateFormat = moment
Vue.prototype.$http = axios

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
