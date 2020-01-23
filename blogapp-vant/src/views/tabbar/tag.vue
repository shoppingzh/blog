<template>
  <div>
    <van-nav-bar
      title="标签"
      border>
    </van-nav-bar>
    <div class="app-container">
      <van-tag
        v-for="item in list"
        :key="item.id"
        class="tag"
        type="warning"
        :color="item.theme">
        {{ item.name }}
      </van-tag>
    </div>
  </div>
</template>

<script>
import * as api from '@/api/tag'

export default {
  data () {
    return {
      list: []
    }
  },
  mounted () {
    api.all().then((result) => {
      this.list = result.data
    }).catch((msg) => {
      console.log(msg)
    })
  }
}
</script>

<style scoped>
  .app-container{ padding: 10px; }
  .tag{ margin-right: 5px; margin-bottom: 5px; }
</style>
