import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const tabbarRoutes = [
  {
    path: 'index',
    component: () => import('@/views/tabbar/home'),
    meta: {
      title: '首页',
      icon: 'home-o'
    }
  },
  {
    path: 'category',
    component: () => import('@/views/tabbar/category'),
    meta: {
      title: '分类',
      icon: 'bar-chart-o'
    }
  },
  {
    path: 'tag',
    component: () => import('@/views/tabbar/tag'),
    meta: {
      title: '标签',
      icon: 'label-o'
    }
  },
  {
    path: 'me',
    component: () => import('@/views/tabbar/me'),
    meta: {
      title: '我',
      icon: 'contact'
    }
  }
]

const routes = [
  {
    path: '/',
    component: () => import('@/views/index'),
    redirect: 'index',
    children: tabbarRoutes
  },
  {
    path: '*',
    component: () => import('@/views/error/index')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
