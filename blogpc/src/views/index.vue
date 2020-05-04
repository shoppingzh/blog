<template>
  <div>
    <a-row>
      <a-col :xs="24" :sm="24" :md="24" :lg=" 16" :xl="18" class="article-list">
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
      <a-col :xs="24" :sm="24" :md="24" :lg=" 8" :xl="6" style="padding: 20px 5px;">
        <a-affix :offset-top="75">
          <a-card>
            <a-calendar />
          </a-card>
        </a-affix>
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
