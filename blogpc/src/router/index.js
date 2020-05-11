import Vue from 'vue'
import VueRouter from 'vue-router'
import Layout from '@/layout'
import Index from '@/views/index'
import Article from '@/views/article'

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
      },
      {
        path: '/a/:id',
        alias: '/article/:id',
        name: 'Article',
        component: Article,
        props: true
      }
    ]
  }
]

const router = new VueRouter({
  routes,
  mode: 'history'
})

export default router