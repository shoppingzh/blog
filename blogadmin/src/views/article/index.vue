<template>
  <div>
    <el-table
      border
      :data="page.data"
      fit>
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
      <el-table-column
        label="发表日期">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>
      <el-table-column
        label="标签">
        <template slot-scope="scope">
          <el-tag
            v-for="tag in scope.row.tags" 
            :key="tag"
            type="info"
            :style="{ marginRight: '5px', marginBottom: '3px'}">{{tag}}</el-tag>
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
          :page-size="pageSize"
          @current-change="pageChange"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script>

import { list, remove } from '@/api/article'

export default {
  data() {
    return {
      page: {},
      pageSize: 15,
      viewCurrent: false,
      current: null
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
      list({ page: page, pageSize: this.pageSize }).then((resp) => {
        if (resp.success) {
          this.page = resp.data
        }
      })
    },
    remove(scope) {
      this.$confirm('确定要删除吗？').then(() => {
        remove(scope.row.id).then((resp) => {
          if (resp.success) {
            window.console.log(`重新请求：${this.page.page}`)
            this.$message({
              message: '删除成功！',
              type: 'success'
            })
            this.fetchData(this.page.page)
          }
        })
      }).catch(() => {
        // nothing
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
