<template>
  <div class="article">
    <a-row v-if="!article" type="flex" justify="center" style="padding: 150px 0;">
      <a-spin />
    </a-row>
    <template v-else>
      <div class="article__title">{{ article.title }}</div>
      <div class="article__time">{{ article.createTime | moment("YYYY-MM-DD HH:mm") }}</div>
      <div v-if="article.tags && article.tags.length" class="article__tags">
        <a-tag v-for="tag in article.tags" :key="tag.id" :color="tag.theme" class="article__tag">{{ tag.name }}</a-tag>
      </div>
      <div class="article__content" v-html="article.content" v-viewer="{ toolbar: false, movable: false, fullscreen: false, inline: false }"></div>
    </template>
  </div>
</template>

<script>
import * as api from '@/api/article'

export default {
  props: {
    id: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      article: null
    }
  },
  mounted() {
    api.get(this.id).then((resp) => {
      if (resp.success) {
        this.article = resp.data
      }
    })
  }
}
</script>

<style scoped>
  .article{
    margin: 20px 10px;
    padding: 30px;
    background-color: #fff;
    border-radius: 6px;
  }
  .article__title{
    font-size: 30px;
    color: #404040;
    font-weight: 700;
  }
  .article__content{
    word-break: break-all;
    margin-top: 30px;
    min-height: 150px;
  }
  .article__content img{
    cursor: pointer;
  }
  .article__tags{
    margin-top: 8px;
  }
</style>
