<template>
  <div>
    <router-view class="tabbar-wrapper"/>
    <tabbar :menus="this.$router.options.routes[0].children"></tabbar>
  </div>
</template>

<script>
import Tabbar from './components/Tabbar'

export default {
  components: {
    Tabbar
  },
  mounted () {
  }
}
</script>

<style>
  .tabbar-wrapper { margin-bottom: 50px; }
</style>
