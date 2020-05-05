<template>
  <div>
    <div v-if="article == null"></div>
    <div v-else class="article">
      <h3 style="text-align: center;">{{ article.title }}</h3>
      <div class="content-container">
        <img v-if="article.thumbnail" :src="'http://localhost:8888/api' + article.thumbnail.refPath">
        <div v-html="article.content"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { get } from '@/api/article'

export default {
  props: {
    id: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      article: null
    }
  },
  mounted() {
    console.log(this.$height)
    get(this.id).then((resp) => {
      if (resp.success) {
        this.article = resp.data
      }
    })
  }
}
</script>

<style scoped>
  .article .content-container{ padding: 0 30px; word-break: break-all; }
</style>
