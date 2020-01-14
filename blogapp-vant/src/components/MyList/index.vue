<template>
  <van-pull-refresh
    v-model="refresh"
    @refresh="handleRefresh">
    <div slot="loading">
      <van-loading size="20">刷新中..</van-loading>
    </div>
    <van-list
      v-model="loadMore"
      :finished="finishedLoadMore"
      :offset="0"
      @load="handleLoadMore">
      <template
        v-for="item in list">
        <slot :item="item"></slot>
      </template>
    </van-list>
  </van-pull-refresh>
</template>

<script>
export default {
  name: 'MyList',
  props: {
    url: {
      type: String,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: () => {
        return 12
      }
    },
    data: {
      type: Object,
      required: false,
      default: () => {
        return {}
      }
    }
  },
  data () {
    return {
      list: [],
      refresh: false,
      loadMore: false,
      finishedLoadMore: false,
      page: 0
    }
  },
  methods: {
    fetchList () {
      this.axios({
        url: this.url,
        method: 'get',
        params: Object.assign({ page: this.page, pageSize: this.pageSize }, this.data)
      }).then((resp) => {
        const result = resp.data
        const newList = result.data.data || []
        if (newList.length) {
          if (this.refresh) {
            this.list = []
          }
          this.list.push(...newList)
        } else {
          this.finishedLoadMore = true
        }
        this.refresh = false
        this.loadMore = false
      }).catch(() => {
        this.refresh = false
        this.loadMore = false
      })
    },
    handleRefresh () {
      this.page = 1
      this.refresh = true
      this.fetchList()
    },
    handleLoadMore () {
      this.page++
      this.loadMore = true
      this.fetchList()
    }
  }
}
</script>

<style>

</style>
