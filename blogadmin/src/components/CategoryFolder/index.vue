<template>
  <div>
    <div class="router">
      <span @click="handleListRoot" class="router-item router-item--root">根分类</span>
      <span v-for="parent in parents" :key="parent.id" @click="handleList(parent)" class="router-item">
        {{parent.name}}
      </span>
    </div>
    <div style="margin-bottom: 10px;">
      <el-button size="mini" type="primary" plain @click="handleAdd">添加子分类</el-button>
    </div>
    <el-row
      v-for="cat in list"
      :key="cat.id"
      type="flex"
      align="middle"
      class="category">
      <el-col
        :span="18"
        @click.native="handleList(cat)">
        <div>
          <i class="el-icon-menu" style="color: #E6A23C;"></i> {{ cat.name }}
        </div>
      </el-col>
      <el-col :span="6">
        <span class="category__operate c-primary" @click.capture.stop="handleRename(cat)"><i class="el-icon-edit"></i> 重命名</span>
        <span class="category__operate c-danger" @click.capture.stop="handleDelete(cat)"><i class="el-icon-delete"></i> 删除分类</span>
      </el-col>
    </el-row>
    <el-dialog
      title="添加新分类"
      :visible.sync="isAddOrEditCat"
      @open="handleEditDialogOpen">
      <el-form @submit.native.prevent>
        <el-form-item>
          <el-input ref="catNameInput" v-model="editingCat.name" placeholder="请输入分类名称" @keydown.native.enter="handleSave"></el-input>  
        </el-form-item>
        <el-form-item>
          <el-button size="small" type="primary" @click="handleSave">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import * as api from '@/api/category'

export default {
  name: 'ZxCategoryFolder',
  data() {
    return {
      parents: [],
      list: [],
      isAddOrEditCat: false,
      editingCat: {
        id: null,
        name: ''
      }
    }
  },
  computed: {
    currentCat: function() {
      return this.parents[this.parents.length - 1]
    }
  },
  mounted() {
    this.handleListRoot()
  },
  methods: {
    handleListRoot() {
      api.children().then((resp) => {
        this.list = resp.data
      })
      while (this.parents.shift());
    },
    handleList(cat) {
      const idx = this.parents.findIndex((p) => {
        return p.id === cat.id
      })
      if (idx < 0) {
        this.parents.push(cat)
      } else {
        this.parents.splice(idx + 1, this.parents.length)
      }
      api.children(cat.id).then((resp) => {
        this.list = resp.data
      })
    },
    deleteCat(cat) {
      if (cat) {
        api.remove(cat.id).then((resp) => {
          if (resp.success) {
            var idx = this.list.findIndex((o) => {
              return o.id === cat.id
            })
            this.list.splice(idx, 1)
          } else {
            this.$message(`删除失败：${resp.msg || ''}`)
          }
        })
      }
    },
    handleEditDialogOpen() {
      this.$nextTick(() => {
        this.$refs.catNameInput.focus()
      })
    },
    // 打开添加分类对话框
    handleAdd() {
      this.editingCat.id = null
      this.editingCat.name = ''
      this.isAddOrEditCat = true
    },
    // 添加分类
    handleSave() {
      const cat = {
        id: this.editingCat.id,
        name: this.editingCat.name,
        parent: {
          id: this.currentCat ? this.currentCat.id : null
        }
      }
      api.save(cat).then((resp) => {
        if (resp.success) {
          this.isAddOrEditCat = false
          if (!cat.id) {
            api.children(this.currentCat ? this.currentCat.id : null).then((resp) => {
              this.list = resp.data
            })
          }
        }
      })
    },
    // 重命名分类
    handleRename(cat) {
      this.editingCat = cat
      this.isAddOrEditCat = true
    },
    // 删除分类
    handleDelete(cat) {
      this.$confirm('确定删除当前分类(同时将删除该分类下的所有子分类)').then(() => {
        this.deleteCat(cat)
      }).catch(() => {})
    }
  }
}
</script>

<style scoped>
  /*  顶部路由 */
  .router{
    margin-bottom: 25px;
    padding: 5px 0;
    border-bottom: 1px solid #eee;
  }
  .router-item{
    font-size: 14px;
    color: #666;
    padding: 5px 3px;
    cursor: pointer;
    position: relative;
    margin-right: 3px;
  }
  .router-item:hover{
    background-color: rgba(64, 158, 255, .25);
  }
  .router-item:not(:last-child):after{
    content: '>';
    margin-left: 3px;
  }

  /* 分类 */
  .category{
    padding: 10px 5px;
    cursor: pointer;
    color: #333;
  }
  .category__operate{
    font-size: 12px;
    margin-right: 5px;
    cursor: pointer;
  }
  .category__operate:hover{
    text-decoration: underline;
  }
  .category:hover{
    background-color: rgba(200, 200, 200, .25)
  }
</style>
