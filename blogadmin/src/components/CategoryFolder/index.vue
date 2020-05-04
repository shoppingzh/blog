<template>
  <div>
    <div class="router">
      <span @click="handleListRoot" class="router-item router-item--root">根分类</span>
      <span v-for="parent in parents" :key="parent.id" @click="handleList(parent)" class="router-item">
        {{parent.name}}
      </span>
    </div>
    <el-row>
      <el-col
        v-for="cat in list" :key="cat.id" class="category"
        :xs="24" :sm="24" :md="24" :lg="24" :xl="24"
        @click.native="handleList(cat)">
        <i class="el-icon-menu" style="color: #E6A23C;"></i> {{ cat.name }}
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as api from '@/api/category'

export default {
  name: 'ZxCategoryFolder',
  data() {
    return {
      parents: [],
      list: []
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
    padding: 5px 5px;
    cursor: pointer;
    color: #333;
  }
  .category:hover{
    background-color: rgba(128, 128, 128, .25)
  }
</style>
