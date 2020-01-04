<template>
  <div>
    <el-table
      border
      :data="page.list"
      fit>
      <el-table-column
        label="序号"
        width="60"
        align="center">
        <template slot-scope="scope">
          {{ (page.page - 1) * pageSize + scope.$index + 1 }}
        </template>
      </el-table-column>
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
            type="primary"
            @click="current = scope.row; viewCurrent = true;"> 查看
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

    <el-dialog
      title="看文章"
      :visible.sync="viewCurrent" >
      <div v-html="current ? current.content : ''" style="word-break: break-all;"></div>
    </el-dialog>
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
      window.console.log(scope)
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
    view() {
      this.$message({
        message: '暂不支持查看',
        type: 'error'
      })
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
