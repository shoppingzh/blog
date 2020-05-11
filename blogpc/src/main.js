import Vue from 'vue'
import App from './App.vue'
import router from './router'

// plugins
import './plugins/ant-design-vue.js'
import './plugins/vue-moment.js'
import './plugins/v-viewer.js'

// app styles
import '@/styles/app.css'


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
