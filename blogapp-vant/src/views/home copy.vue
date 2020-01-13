<template>
  <div class="home">
    <van-pull-refresh
      v-model="refresh"
      @refresh="handleRefresh">
      <div slot="loading">
        <van-loading size="20">刷新中..</van-loading>
      </div>
      <van-list
        v-model="loading"
        :finished="finished"
        :offset="0"
        @load="handleLoad">
        <article-card
          v-for="item in list"
          :key="item.id"
          :article="item">
        </article-card>
      </van-list>
    </van-pull-refresh>
  </div>
</template>

<script>

import { page as pageApi } from '@/api/article'
import ArticleCard from '@/components/ArticleCard'

export default {
  name: 'home',
  components: {
    ArticleCard
  },
  data () {
    return {
      refresh: false,
      loading: false,
      finished: false,
      list: [],
      page: 1,
      pageSize: 15
    }
  },
  methods: {
    handleRefresh () {
      this.page = 1
      this.refresh = true
      this.handleLoad()
    },
    handleLoad () {
      this.loading = true
      pageApi({ page: this.page, pageSize: this.pageSize }).then((resp) => {
        const data = resp.data
        if (data.success) {
          const newList = data.data.data || []
          if (newList.length) {
            if (this.refresh) {
              this.list = []
            }
            this.list.push(...newList)
            console.log(this.list.length)
          } else {
            this.finished = true
          }
          this.page++
        }
        this.refresh = false
        this.loading = false
      }).catch((e) => {
        this.refresh = false
        this.loading = false
        this.$dialog.alert({
          title: '错误',
          message: e
        })
      })
    }
  }
}
</script>

<style scoped>
  .home{ background-color: #f3f4f7; padding: 15px 10px; }
  .home .article-card{ margin-bottom: 15px; }
</style>
