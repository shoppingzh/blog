<template>
  <div>
    <el-tree
      ref="tree"
      node-key="id"
      lazy
      :props="{ label: 'name', children: 'children' }"
      :load="handleLoadTree"
      :show-checkbox="choose"
      :check-strictly="true">
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
      :modal="false"
      @opened="$refs.nameInput.focus()">
      <el-form
        v-if="editCat !== null"
        size="mini"
        mode="horizontal"
        @submit.native.prevent>
        <el-form-item
          label="标题">
          <el-input
            ref="nameInput"
            v-model="editCat.name"
            @keydown.native.enter="handleSave">
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
  name: 'CategoryTree',
  props: {
    choose: {
      type: Boolean,
      required: false,
      default: () => {
        return false
      }
    }
  },
  data() {
    return {
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
    handleLoadTree(node, resolve) {
      api.children(node.level <= 0 ? '' : node.data.id).then((resp) => {
        resolve(resp.data)
      })
    },
    handleAdd(data) {
      this.addParent = data
      this.editCat = {
        parent: {
          id: data ? data.id : ''
        }
      }
    },
    handleEdit(data) {
      this.editCat = data
      this.$nextTick(() => {
        this.$refs.nameInput.select()
      })
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
            const parent = this.addParent || this.$refs.tree.root
            this.$refs.tree.append(this.editCat, parent)
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

<style>

</style>
