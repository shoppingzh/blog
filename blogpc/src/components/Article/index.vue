<template>
  <div class="article" :class="article.thumbnail ? 'article--media' : ''">
    <div class="article__header">
      <a href="##" class="article__title" @click.prevent="handleRoute">
        {{ article.title }}
      </a>
      <div class="article__time">
        <span><a-icon type="history" /> {{ article.createTime | moment("YYYY-MM-DD HH:mm") }}</span>
      </div>
    </div>
    <div class="article__content" v-html="article.summary">
    </div>
    <img v-if="article.thumbnail" class="article__thumbnail"
      :src="'http://localhost:8888/api/file/' + article.thumbnail.id">
    <div v-if="article.tags && article.tags.length" class="article__tags">
      <a-tag v-for="tag in article.tags" :key="tag.id" :color="tag.theme" class="article__tag">{{ tag.name }}</a-tag>
    </div>
  </div>
</template>

<script>
export default {
  name: 'zx-article',
  props: {
    article: {
      type: Object,
      required: true
    }
  },
  methods: {
    handleRoute() {
      this.$router.push(`/a/${this.article.id}`)
    }
  }
}
</script>

<style scoped>
  .article{
    padding: 10px 15px;
    background-color: #fff;
    border: 1px solid #eee;
    border-radius: 4px;
    position: relative;
    overflow: hidden;
  }
  .article--media{
    padding-right: 260px;
  }
  .article__header{
    font-size: 18px;
    padding: 5px 0;
  }
  .article__title{
    color: #1890ff;
    margin-bottom: 8px;
  }
  .article__content{
    min-height: 50px;
  }
  .article__content:after{
    content: '...';
  }
  .article__tags{
    margin-top: 12px;
  }
  .article__tag{
    cursor: pointer;
    opacity: .85;
  }
  .article__tag:hover{
    opacity: 1;
  }
  .article__time{
    font-size: 12px;
    color: rgba(0, 0, 0, .65);
  }
  .article__thumbnail{
    width: 220px;
    height: 120px;
    object-fit: contain;
    position: absolute;
    right: 10px;
    top: 50%;
    transform: translate(0, -50%);
  }
  @media (max-width: 768px) {
    .article{
      padding-right: 20px;
    }
    .article__thumbnail {
      display: none;
    }
  }
</style>
