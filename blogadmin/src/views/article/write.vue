<template>
  <div>
    <el-row>
      <el-input
        v-model="title"
        placeholder="无标题文章"
        clearable
        class="title-input" />
    </el-row>
    <tinymce
      v-model="content"
      :height="400"></tinymce>
    <el-row
      type="flex"
      class="publish-area" >
      <el-button
        type="primary"
        class="publish-btn"
        :loading="loading"
        @click="publish" >发布文章</el-button>
    </el-row>
  </div>
</template>

<script>
import tinymce from '@/components/Tinymce'
import { save } from '@/api/article'

export default {
  components: {
    tinymce
  },
  data() {
    return {
      title: '',
      content: `
      `,
      loading: false
    }
  },
  methods: {
    publish() {
      this.loading = true;
      save({
        title: this.title,
        content: this.content
      }).then(resp => {
        if(resp.success){
          this.$message({
            message: '发表成功！',
            type: 'success'
          })
          this.$router.push({ path: '/article' })
        }
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    }
  }
}
</script>

<style>
  .title-input input{ border: none; padding-left: 10px; font-size: 20px; }
  .publish-area { padding: 20px 10px; }
</style>
