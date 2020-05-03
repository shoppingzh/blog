import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/layout'
import Index from '@/views/index'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '',
    children: [
      {
        path: '',
        name: 'Index',
        component: Index
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router