<template>
  <div class="home">
    <my-list
      url="http://localhost:3000/article">
      <template #default="{ item }">
        <article-card
          :article="item"
          @click="handleClickArticle(item)">
        </article-card>
      </template>
    </my-list>
  </div>
</template>

<script>
import MyList from '@/components/MyList'
import ArticleCard from '@/components/ArticleCard'

export default {
  name: 'home',
  components: {
    MyList,
    ArticleCard
  },
  methods: {
    handleClickArticle (article) {
      console.log(article)
    }
  }
}
</script>

<style scoped>
  .home{ background-color: #f3f4f7; padding: 15px 10px; }
  .home .article-card{ margin-bottom: 15px; }
</style>
