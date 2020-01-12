<template>
  <div>
    <van-tabbar
      v-model="active"
      @change="handleIndexChange">
      <van-tabbar-item
        v-for="(menu, index) in menus"
        :key="menu.path"
        :icon="(menu.meta && menu.meta.icon) ? menu.meta.icon : 'home-o'">
        {{ (menu.meta && menu.meta.title) ? menu.meta.title : ('标签' + (index + 1)) }}
      </van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script>
export default {
  props: {
    menus: {
      type: Array,
      required: false,
      default: () => {
        return []
      }
    }
  },
  data () {
    return {
      active: 0
    }
  },
  mounted () {
    console.log(this.menus)
  },
  methods: {
    handleIndexChange (index) {
      this.$router.push({ path: this.menus[index].path })
    }
  }
}
</script>

<style>

</style>
