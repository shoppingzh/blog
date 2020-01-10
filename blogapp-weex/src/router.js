/* global Vue */
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Nice from '@/components/Nice'

Vue.use(Router)

export const router = new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/nice',
      name: 'Nice',
      component: Nice
    }
  ]
})
