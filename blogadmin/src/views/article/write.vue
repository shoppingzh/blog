<template>
  <div>
    <el-row
      type="flex"
      :gutter="20">
      <el-col :span="18">
        <tinymce
          ref="editor"
          v-model="article.content"
          :height="450" />
      </el-col>
      <el-col :span="6">
        <el-form label-position="top" label-suffix="：">
          <el-form-item label="标题">
            <el-input
              ref="titleInput"
              v-model="article.title"
              placeholder="无标题文章"
              clearable
              @focus="handleTitleFocus" />
          </el-form-item>
          <el-form-item label="标签">
            <el-select
              v-model="tagNames"
              multiple
              filterable
              allow-create
              placeholder="请选择文章标签" >
              <el-option
                v-for="tag in tags"
                :key="tag.name"
                :label="tag.name"
                :value="tag.name">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button
              type="danger"
              class="publish-btn"
              :loading="loading"
              @click="publish">
              <span v-if="$route.query.id">保存</span>
              <span v-else><svg-icon icon-class="publish" /> 发布文章</span>
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
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
  .publish-area { padding: 20px 10px; }
  .other-container { padding: 10px 5px; }
</style>
