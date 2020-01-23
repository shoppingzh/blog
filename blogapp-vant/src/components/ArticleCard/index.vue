<template>
  <div class="article-card" @click="$emit('click', article)">
    <div class="article-image">
      <van-image
        :src="article.preview || 'https://img.yzcdn.cn/vant/cat.jpeg'"
        width="100%"
        height="150"
        fit="cover">
      </van-image>
    </div>
    <div class="article-body">
      <div v-if="article.tags && article.tags.length" class="article-tags">
        <van-tag
          class="article-tag"
          v-for="tag in article.tags"
          :key="tag.id"
          :color="tag.theme"
          type="primary"
          plain>
          {{ tag.name }}
        </van-tag>
      </div>
      <div class="van-multi-ellipsis--l2 article-title">{{ article.title }}</div>
      <div class="van-ellipsis article-summary">{{ article.summary }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    article: {
      type: Object,
      required: true
    }
  },
  data () {
    return {}
  }
}
</script>

<style scoped>
  .article-card { background-color: #fff; border-radius: 8px; overflow: hidden; }
  .article-card .article-body{ padding: 5px 10px; }
  .article-card .article-title{ font-size: 16px; color: #333; }
  .article-card .article-summary{ font-size: 12px; color: #777; padding: 5px 0; }

  .article-tags .article-tag { margin-right: 5px; margin-bottom: 5px; }
</style>
