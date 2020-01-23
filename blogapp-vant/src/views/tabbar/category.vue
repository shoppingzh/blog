<template>
  <div>
    <van-nav-bar
      title="分类"
      border>
    </van-nav-bar>
    <van-cell
      v-for="item in list"
      :key="item.id"
      is-link
      @click="handleClickCategory(item)">
      <template slot="title">
        <van-icon name="points"></van-icon>
        {{ item.name }}
      </template>
    </van-cell>
  </div>
</template>

<script>
import * as api from '@/api/category'

export default {
  data () {
    return {
      list: []
    }
  },
  mounted () {
    this.load()
  },
  methods: {
    load (pid) {
      api.children(pid).then((data) => {
        this.list = data
      })
    },
    handleClickCategory (category) {
      this.$toast('暂不支持查看分类下的文章')
    }
  }
}
</script>

<style>

</style>
