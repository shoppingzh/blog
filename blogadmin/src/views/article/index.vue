<template>
  <div class="app-container">
    <el-table
      v-loading="loading"
      border
      :data="page.data"
      fit
      stripe>
      <el-table-column
        label="ID"
        width="180"
        align="center"
        prop="id">
      </el-table-column>
      <el-table-column
        label="标题">
        <template slot-scope="scope">
          <a href="javascript:;" class="c-primary" type="primary" @click="handleView(scope.row)">{{ scope.row.title }}</a>
        </template>
      </el-table-column>
      <el-table-column
        label="摘要"
        prop="summary"
      />
      <!-- <el-table-column
        label="发表日期">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column> -->
      <el-table-column
        label="分类"
        align="center"
        :width="100">
          <template slot-scope="scope">
            {{ scope.row.category ? scope.row.category.name : '' }}
          </template>
      </el-table-column>
      <el-table-column
        label="标签"
        align="center"
        :min-width="80">
        <template slot-scope="scope">
          <el-row
            v-if="scope.row.tags.length"
            type="flex"
            justify="center"
            :style="{ flexWrap: 'wrap' }">
            <article-tag
              v-for="tag in scope.row.tags"
              :key="tag.id"
              :tag="tag"/>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column
        label="已发布"
        width="70"
        align="center">
        <template slot-scope="scope">
          <i  v-if="!scope.row.draft" class="el-icon-check c-primary"></i>
        </template>
      </el-table-column>
      <el-table-column
        label="热度"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ scope.row.hot }}
        </template>
      </el-table-column>
      <el-table-column>
        <template slot="header">
          <div>
            操作
            <el-button
              type="text"
              @click="write">
              （<span><i class="el-icon-edit"></i> 写文章</span>）
            </el-button>
          </div>
        </template>
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.draft"
            size="mini"
            type="success"
            @click="handlePublish(scope.row)"> 发布
          </el-button>
          <el-button
            size="mini"
            type="primary"
            @click="edit(scope)">
            <i class="el-icon-edit"></i>
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="remove(scope)"><i class="el-icon-delete"></i>
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex" class="row-bg" justify="end">
      <el-col :style="{width: 'auto', padding: '10px 20px'}">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="page.total"
          :page-size="page.pageSize"
          @current-change="pageChange"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>

import * as api from '@/api/article'
import ArticleTag from '@/components/ArticleTag'

export default {
  components: {
    ArticleTag
  },
  data() {
    return {
      page: {
        page: 1,
        pageSize: 5,
        total: 0,
        data: []
      },
      loading: false
    }
  },
  mounted() {
    this.fetchData(1)
  },
  methods: {
    pageChange(page) {
      this.fetchData(page)
    },
    fetchData(page) {
      this.loading = true
      this.page.page = page
      api.list({ page: page, pageSize: this.page.pageSize }).then((resp) => {
        this.loading = false
        if (resp.success) {
          this.page = resp.data
        }
      }).catch(() => {
        this.loading = false
      })
    },
    remove(scope) {
      this.$confirm('确定要删除吗？').then(() => {
        this.loading = true
        api.remove(scope.row.id).then((resp) => {
          this.loading = false
          if (resp.success) {
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.fetchData(this.page.page)
          }
        })
      }).catch(() => {
        this.loading = false
      })
    },
    // 看文章
    handleView(article) {
      this.$router.push({ path: `/article/view/${article.id}` })
    },
    // 编辑文章
    edit(scope) {
      this.$router.push({ path: `/article/write`, query: { id: scope.row.id }})
    },
    // 写文章
    write() {
      this.$router.push({ path: '/article/write' })
    },
    // 发布文章
    handlePublish(article) {
      this.$confirm('发布，确定？').then(() => {
        api.publish(article.id).then((resp) => {
          if (resp.success) {
            article.draft = false
          }
        })
      }).catch(() => {})
    }
  }
}
</script>

<style>
</style>
