<template>
  <div>
    <div v-if="article == null"></div>
    <div v-else class="article">
      <div class="article__title">
        {{ article.title }}
      </div>
      <div class="article__tags">
        <article-tag
          v-for="tag in article.tags"
          :key="tag.id"
          :tag="tag"/>
      </div>
      <div class="article__content" v-html="article.content"></div>
    </div>
  </div>
</template>

<script>
import ArticleTag from '@/components/ArticleTag'
import { get } from '@/api/article'

export default {
  components: {
    ArticleTag
  },
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
  .article{
    padding: 30px;
  }
  .article__title{
    color: #303133;
    font-size: 30px;

  }
  .article__content{
    margin-top: 15px;
    color: #606266;
  }
  .article__tags{
    padding: 5px 0;
  }
  .article__tags .tag{
    height: 25px;
    line-height: 25px;
  }
</style>
