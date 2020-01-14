<template>
  <div>
    <el-table
      v-loading="loading"
      border
      :data="page.data"
      fit
      stripe>
      <el-table-column
        label="ID"
        width="80"
        align="center"
        prop="id"
      />
      <el-table-column
        label="标题"
        prop="title"
      />
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
        label="标签"
        align="center"
        :min-width="80">
        <template slot-scope="scope">
          <el-row
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
        label="状态"
        width="120"
        align="center">
        <template slot-scope="scope">
          <el-tag v-if="!scope.row.draft" type="success">已发布</el-tag>
          <el-tag v-else type="info">草稿</el-tag>
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
            size="mini"
            type="info"
            @click="view(scope)"> 查看
          </el-button>
          <el-button
            size="mini"
            type="primary"
            @click="edit(scope)">
            编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="remove(scope)">删除
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

import { list, remove } from '@/api/article'
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
      list({ page: page, pageSize: this.page.pageSize }).then((resp) => {
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
        remove(scope.row.id).then((resp) => {
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
    view(scope) {
      this.$router.push({ path: `/article/view/${scope.row.id}` })
    },
    // 编辑文章
    edit(scope) {
      this.$router.push({ path: `/article/write`, query: { id: scope.row.id }})
    },
    // 写文章
    write() {
      this.$router.push({ path: '/article/write' })
    }
  }
}
</script>

<style>
</style>
