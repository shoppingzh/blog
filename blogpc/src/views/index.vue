<template>
  <div>
    <a-row>
      <a-col :xs="24" :sm="24" :md="18" :lg=" 18" :xl="16" class="article-list">
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
        <zx-article class="article-list__item"></zx-article>
      </a-col>
      <a-col :xs="24" :sm="24" :md="6" :lg=" 6" :xl="8" class="">
      </a-col>
    </a-row>
  </div>
</template>

<script>
import ZxArticle from '@/components/Article'

export default {
  components: {
    ZxArticle
  }
}
</script>

<style scoped>
  .article-list{
    padding: 20px;
  }
  .article-list__item{
    margin-bottom: 10px;
  }
</style>
