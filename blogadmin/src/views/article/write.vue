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
      <el-col :span="6" class="edit-right">
        <el-form label-position="top" label-suffix="：">
          <el-form-item label="标题">
            <el-input
              ref="titleInput"
              v-model="article.title"
              placeholder="无标题文章"
              clearable
              @focus="handleTitleFocus" />
          </el-form-item>
          <el-form-item label="缩略图">
            <el-upload
              v-if="!article.thumbnail"
              action="http://localhost:8888/api/file"
              list-type="picture-card"
              :multiple="false"
              :limit="1"
              :on-success="handleThumbnailUploaded">
              <i slot="default" class="el-icon-plus"></i>
            </el-upload>
            <img
              v-else
              style="width: 200px; height: 150px; object-fit: contain; border: 1px solid #eee; border-radius: 4px;"
             :src="'http://localhost:8888/api/file/' + article.thumbnail.id"
             />
          </el-form-item>
          <el-form-item label="标签">
            <el-select
              v-model="selectTagIds"
              multiple
              filterable
              placeholder="请选择文章标签" >
              <el-option
                v-for="tag in tags"
                :key="tag.id"
                :label="tag.name"
                :value="tag.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="选择文章分类">
            <a href="javascript:;" class="c-primary" type="primary" @click="handleChooseCategory">
              <span v-if="article.category">{{ article.category.name }} </span>
              <span v-else>清选择</span>
            </a>
          </el-form-item>
          <el-form-item>
            <el-button
              type="danger"
              class="publish-btn"
              :loading="loading"
              @click="handlePublish(false)">
              <svg-icon icon-class="publish" /> 发布文章
            </el-button>
            <el-button
              :loading="loading"
              @click="handlePublish(true)">
              保存草稿
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <el-dialog
      :visible.sync="choosingCat">
      <category-tree
        ref="catTree"
        :choose="true"></category-tree>
      <div class="app-container" style="text-align: right;">
        <el-button
          type="primary"
          size="mini"
          @click="handleChooseCategoryEnd">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import tinymce from '@/components/Tinymce'
import CategoryTree from '@/components/CategoryTree'
import * as api from '@/api/article'
import * as tagApi from '@/api/tag'

export default {
  components: {
    tinymce,
    CategoryTree
  },
  data() {
    return {
      article: {
        id: '',
        title: '',
        content: '',
        tags: [],
        category: null,
        thumbnail: null
      },
      tags: [],
      loading: false,
      choosingCat: false,
      selectTagIds: []
    }
  },
  computed: {
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
          const article = resp.data
          this.article = article
          if (article.tags) {
            article.tags.forEach((tag) => {
              console.log(tag.id)
              this.selectTagIds.push(tag.id)
            })
          }
        }
      })
    }
  },
  methods: {
    // 缩略图上传成功
    handleThumbnailUploaded(resp) {
      if (resp.success) {
        this.article.thumbnail = {
          id: resp.data
        }
      } else {
        this.$message(`文件上传失败`)
      }
    },
    // 发布/保存草稿
    handlePublish(draft) {
      if (!this.article.content.trim()) {
        this.$message({ message: '请输入内容', type: 'error' })
        return false
      }

      const selectTagIds = this.selectTagIds
      const selectTags = []
      selectTagIds.forEach((selectTagId) => {
        selectTags.push({
          id: selectTagId
        })
      })

      this.loading = true
      api.save({
        id: this.article.id,
        title: this.article.title || '无标题文章',
        content: this.article.content,
        plainContent: this.$refs.editor.getPlainContent(),
        tags: selectTags,
        category: this.article.category,
        draft: draft || false,
        thumbnail: this.article.thumbnail
      }).then(resp => {
        if (resp.success) {
          this.$message({
            message: `${draft ? '保存' : '发表'}成功！`,
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
    },
    handleChooseCategory() {
      this.choosingCat = true
    },
    handleChooseCategoryEnd() {
      const checkNodes = this.$refs.catTree.$refs.tree.getCheckedNodes()
      if (checkNodes.length > 1) {
        return this.$alert('只能选择一个分类')
      }
      if (checkNodes.length > 0) {
        this.article.category = checkNodes[0]
      }
      this.choosingCat = false
    }
  }
}
</script>

<style scoped>
  .publish-area { padding: 20px 10px; }
  .other-container { padding: 10px 5px; }
  .edit-right{
    margin: 0 10px;
  }
</style>
