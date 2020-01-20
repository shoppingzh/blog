<template>
  <div  class="app-container">
    <el-tree
      ref="tree"
      :data="tree"
      node-key="id"
      :props="{ label: 'name', children: 'children' }"
      lazy
      accordion
      highlight-current
      :load="loadNode">
      <div
        slot-scope="scope"
        style="width: 100%;"
        @mouseenter="operating = scope.data"
        @mouseleave="operating = null">
        <el-row type="flex" align="middle">
          <el-col :span="18">
            {{ scope.data.name }}
          </el-col>
          <el-col
            v-show="operating && operating.id === scope.data.id"
            :span="6"
            style="text-align: right; padding-right: 10px;"
            class="operation">
            <el-link v-if="!scope.data.parent" type="primary" @click.stop="handleAdd()" ><i class="el-icon-share"></i></el-link>
            <el-link type="success" @click.stop="handleAdd(scope.data)" ><i class="el-icon-plus"></i></el-link>
            <el-link type="warning" @click.stop="handleEdit(scope.data)" ><i class="el-icon-edit"></i></el-link>
            <el-link type="danger" @click.stop="handleRemove(scope.data)"><i class="el-icon-delete"></i></el-link>
          </el-col>
        </el-row>
      </div>
    </el-tree>
    <el-dialog
      :visible.sync="editing"
      :title="editCat ? '编辑' : '添加'"
      @opened="$refs.nameInput.focus()">
      <el-form
        v-if="editCat !== null"
        size="mini"
        mode="horizontal">
        <el-form-item
          label="标题">
          <el-input ref="nameInput" v-model="editCat.name">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleSave">提交</el-button>
          <el-button @click="editing = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as api from '@/api/category'

export default {
  data() {
    return {
      tree: [],
      operating: null,
      addParent: null,
      editCat: null
    }
  },
  computed: {
    editing: {
      get: function() {
        return this.editCat !== null
      },
      set: function(newVal) {
        this.editCat = null
      }
    }
  },
  mounted() {
  },
  methods: {
    loadNode(node, resolve) {
      api.children(node.level <= 0 ? '' : node.data.id).then((resp) => {
        if (resp.success) {
          resolve(resp.data)
        }
      })
    },
    handleAdd(data) {
      this.addParent = data
      this.editCat = {
        parent: data ? data.id : ''
      }
    },
    handleEdit(data) {
      this.editCat = data
    },
    handleRemove(data) {
      this.$confirm('将删除该分类及其子分类，继续？').then(() => {
        api.remove(data.id).then((resp) => {
          this.$refs.tree.remove(data)
        })
      }).catch(() => {})
    },
    handleSave() {
      if (this.editCat === null) {
        return
      }
      api.save(this.editCat).then((resp) => {
        if (resp.success) {
          if (!this.editCat.id) {
            this.editCat.id = resp.data
            if (this.addParent) {
              if (!this.addParent.children) {
                this.$set(this.addParent, 'children', [])
              }
              this.addParent.children.push(this.editCat)
            } else {
              this.tree.push(this.editCat)
            }
          }
          this.editCat = null
        } else {
          this.$message({
            message: `操作失败：` + resp.msg || '',
            type: 'error'
          })
        }
      })
    }
  }
}
</script>

<style scoped>
    .operation .el-link{ margin-right: 5px; }
</style>
