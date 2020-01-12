<template>
  <div>
    <router-view/>
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
    console.log(this.$router.options.routes)
  }
}
</script>

<style>

</style>
