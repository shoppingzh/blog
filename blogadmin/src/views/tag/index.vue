<template>
  <div class="app-container">
    <div class="filter-container">
      <el-row
        type="flex" >
        <el-col
          :span="24"></el-col>
        <el-button
          type="primary"
          size="small"
          @click="handleAdd" >
          <i class="el-icon-edit"></i> 添加
        </el-button>
      </el-row>
    </div>
    <el-table
      :data="page.data"
      border
      v-loading="loading" >
      <el-table-column
        label="ID"
        prop="id"
        :width="80"
        align="center" />
      <el-table-column
        label="名称" >
        <template slot-scope="scope">
          <el-input
            v-if="editingRow && scope.row.id === editingRow.id"
            ref="editInput"
            size="small"
            v-model="editingRow.name"
            @focus="handleEditInuptFocus"
            @change="handleEdit" >
          </el-input>
          <div v-else @dblclick="handleStartEdit(scope.row)" >{{ scope.row.name }}</div>
        </template>
      </el-table-column>
      <el-table-column
        label="主题色"
        align="center"
        :width="120">
        <el-color-picker
          slot-scope="scope"
          v-model="scope.row.theme"
          @change="handleChangeTheme(scope.row)">
        </el-color-picker>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center">
        <template slot-scope="scope">
          <div v-if="editingRow === null || editingRow.id !== scope.row.id">
            <el-button
              type="primary"
              size="mini"
              @click="handleStartEdit(scope.row)" >
              编辑
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click="handleRemove(scope)" > 删除
            </el-button>
          </div>
          <div v-else>
            <el-button
              type="success"
              size="mini"
              @click="handleEdit">
              提交
            </el-button>
            <el-button
              size="mini"
              @click="editingRow = null" > 取消
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="page-container">
      <el-row
        type="flex"
        justify="end">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="page.total"
          :page-size="page.pageSize"
          @current-change="handleChangePage" />
      </el-row>
    </div>
  </div>
</template>

<script>
import * as api from '@/api/tag'

export default {
  data() {
    return {
      page: {
        page: 1,
        pageSize: 10,
        total: 0,
        data: []
      },
      loading: false, // 是否正在加载中
      editingRow: null
    }
  },
  mounted() {
    this.list(1)
  },
  methods: {
    list(page) {
      this.loading = true
      api.list({ page: page, pageSize: this.page.pageSize }).then((resp) => {
        if (resp.success) {
          this.page = resp.data
        }
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    save(tag) {
      if (!tag.name || !tag.name.trim()) {
        this.$message({
          message: '请输入标签名',
          type: 'warning'
        })
        return
      }
      api.save(tag).then((resp) => {
        if (resp.success) {
          this.list(this.page.page)
          if (tag.id) {
            this.$message({
              message: '修改成功！',
              type: 'success'
            })
          } else {
            this.list(this.page.pages)
          }
        } else {
          this.$message({
            message: `保存失败：${resp.msg || ''}`,
            type: 'error'
          })
        }
      })
    },
    handleAdd() {
      this.$prompt('新标签').then((e) => {
        const data = {
          name: e.value
        }

        this.save(data)
      }).catch(() => {})
    },
    handleStartEdit(tag) {
      this.editingRow = Object.assign({}, tag)
      // 自动选中
      this.$nextTick(() => {
        this.$refs.editInput.focus()
      })
    },
    handleEdit() {
      const exist = this.page.data.find((obj) => {
        return obj.id === this.editingRow.id && obj.name.trim() === this.editingRow.name.trim()
      })
      if (!exist) {
        this.save(this.editingRow)
      }
      this.editingRow = null
    },
    handleRemove(scope) {
      this.$confirm('删除该标签？').then(() => {
        api.remove(scope.row.id).then((resp) => {
          if (resp.success) {
            this.list(this.page.page)
          } else {
            this.$message({
              message: `删除失败：${resp.msg || ''}`,
              type: 'error'
            })
          }
        })
      }).catch(() => {})
    },
    handleChangePage(index) {
      this.list(index)
    },
    handleEditInuptFocus() {
      this.$refs.editInput.select()
    },
    handleChangeTheme(tag) {
      api.save(tag).then((resp) => {
      }).catch(() => {
        this.$message({ message: '主题色更改失败', type: 'error' })
      })
    }
  }
}
</script>

<style scoped>
  .app-container{ padding: 20px; }
  .filter-container{ margin-bottom: 15px; }
  .page-container{ padding: 10px 15px; }
</style>
