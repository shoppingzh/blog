<template>
  <div>
    <van-tag
      v-for="item in list"
      :key="item.id"
      type="warning"
      plain>
      {{ item.name }}
    </van-tag>
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

<style>

</style>
