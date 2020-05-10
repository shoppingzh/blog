<template>
  <div>
    <a-row>
      <a-col :xs="24" :sm="24" :md="24" :lg=" 16" class="article-list">
        <zx-article
          v-for="article in list"
          :key="article.id"
          class="article-list__item" :article="article"></zx-article>
        <a-row type="flex" justify="center">
          <a-button v-show="hasMore" size="large" type="primary" ghost @click="handleLoadMore">
            <a-icon type="rocket" />加载更多
          </a-button>
        </a-row>
      </a-col>
      <a-col :xs="24" :sm="24" :md="24" :lg=" 8" style="padding: 20px;">
        
        <a-affix :offset-top="75">
          <a-alert
            style="margin-bottom: 15px;"
            message="每日一刻"
            description="时光一去永不回，往事只能回味"
            type="success"
            show-icon
          />
          <a-card>
            <a-calendar v-model="day" :fullscreen="false" mode="month" :disabledDate="handleDisabledDate">
              <!-- <div slot="dateFullCellRender" slot-scope="scope" class="ant-fullcalendar-value">
                <a-row type="flex" justify="center">
                  <span :style="{ color: stat && stat[$moment(scope).format('YYYY-MM-DD')] > 0 ? 'orange' : '' }">{{scope | moment('DD')}}</span>
                </a-row>
              </div> -->
              <div slot="headerRender" class="article-calendar__header">
                {{ month }}博客日历
              </div>
            </a-calendar>
          </a-card>
        </a-affix>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import ZxArticle from '@/components/Article'

import * as api from '@/api/article'

export default {
  components: {
    ZxArticle
  },
  data() {
    return {
      list: [],
      page: 1,
      pageSize: 5,
      hasMore: true,
      stat: null,
      month: null,
      day: null
    }
  },
  mounted() {
    this.month = this.$moment(+new Date()).format('YYYY年M月')

    this.loadMore()
    api.statWithDay().then((resp) => {
      if (resp.success) {
        const stat = {}
        Object.keys(resp.data).forEach((key) => {
          stat[this.$moment(key).format('YYYY-MM-DD')] = resp.data[key]
        })
        this.stat = stat
      }
    })
  },
  methods: {
    loadMore() {
      api.getNew(this.page, this.pageSize).then((resp) => {
        if (resp.success) {
          const pageInfo = resp.data
          this.page = pageInfo.page + 1
          this.list.push(...pageInfo.data)
          this.hasMore = pageInfo.page < pageInfo.pages
        }
      })
    },
    handleLoadMore() {
      this.loadMore()
    },
    handleDisabledDate(moment) {
      const has = this.stat && this.stat[moment.format('YYYY-MM-DD')] > 0
      return !has
    }
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

  /** 日历 */
  .article-calendar__header {
    font-weight: 600;
    padding-bottom: 10px;
    text-align: center;
  }
</style>
