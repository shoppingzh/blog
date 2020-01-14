<template>
  <div>
    <el-row>
      <el-input
        ref="titleInput"
        v-model="article.title"
        placeholder="无标题文章"
        clearable
        class="title-input"
        @focus="handleTitleFocus" />
    </el-row>
    <tinymce
      ref="editor"
      v-model="article.content"
      :height="400" />
    <div class="other-container">
      <el-select
        v-model="tagNames"
        multiple
        filterable
        allow-create
        placeholder="请选择文章标签"
        :style="{ display: 'block', width: '60%' }">
        <el-option
          v-for="tag in tags"
          :key="tag.name"
          :label="tag.name"
          :value="tag.name">
        </el-option>
        </el-select>
    </div>
    <el-row
      type="flex"
      class="publish-area" >
      <el-button
        type="primary"
        class="publish-btn"
        :loading="loading"
        @click="publish">
        <span v-if="$route.query.id">保存</span>
        <span v-else><svg-icon icon-class="publish" /> 发布文章</span>
      </el-button>
    </el-row>
  </div>
</template>

<script>
import tinymce from '@/components/Tinymce'
import * as api from '@/api/article'
import * as tagApi from '@/api/tag'

export default {
  components: {
    tinymce
  },
  data() {
    return {
      article: {
        id: '',
        title: '',
        content: '',
        tags: []
      },
      tags: [],
      loading: false
    }
  },
  computed: {
    tagNames: {
      get: function() {
        const tags = this.article.tags
        const names = []
        if (tags && tags.length) {
          for (const tag of tags) {
            names.push(tag.name)
          }
        }
        return names
      },
      set: function(newVal) {
        const tags = []
        for (const name of newVal) {
          tags.push({ name: name })
        }
        this.article.tags = tags
      }
    }
  },
  mounted() {
    tagApi.list().then((resp) => {
      if (resp.success) {
        this.tags = resp.data.data
      }
    })

    const id = this.$route.query.id
    if (id) {
      api.get(id).then((resp) => {
        if (resp.success) {
          this.article = resp.data
        }
      })
    }
  },
  methods: {
    publish() {
      if (!this.article.content.trim()) {
        this.$message({ message: '请输入内容', type: 'error' })
        return false
      }

      this.loading = true
      api.save({
        id: this.article.id,
        title: this.article.title || '无标题文章',
        content: this.article.content,
        plainContent: this.$refs.editor.getPlainContent(),
        tags: this.tagNames
      }).then(resp => {
        if (resp.success) {
          this.$message({
            message: '发表成功！',
            type: 'success'
          })
          this.$router.back()
        }
        this.loading = false
      }).catch(() => {
        this.loading = false
      })
    },
    handleTitleFocus(e) {
      this.$refs.titleInput.select()
    }
  }
}
</script>

<style scoped>
  .title-input input{ border: none; padding-left: 10px; font-size: 20px; }
  .publish-area { padding: 20px 10px; }
  .other-container { padding: 10px 5px; }
</style>
