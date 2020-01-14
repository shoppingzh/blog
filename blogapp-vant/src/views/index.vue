<template>
  <div>
    <router-view class="tabbar-wrapper"/>
    <van-tabbar
      v-model="active"
      router>
      <van-tabbar-item
        v-for="(menu, index) in $router.options.routes[0].children"
        :key="menu.path"
        :to="menu.path"
        replace
        :icon="(menu.meta && menu.meta.icon) ? menu.meta.icon : 'home-o'">
        {{ (menu.meta && menu.meta.title) ? menu.meta.title : ('标签' + (index + 1)) }}
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>

export default {
  components: {
  },
  data () {
    return {
      active: 0
    }
  },
  mounted () {
  }
}
</script>

<style>
  .tabbar-wrapper { margin-bottom: 50px; }
</style>
