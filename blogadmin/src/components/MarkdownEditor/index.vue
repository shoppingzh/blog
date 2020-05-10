<template>
  <div ref="el" :id="id"></div>
</template>

<script>
import Editor from '@toast-ui/editor'
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'

import defaults from './defaults'
import * as api from '@/api/file'

export default {
  name: 'ZxMarkdownEditor',
  props: {
    value: {
      type: String,
      required: false,
      default: () => {
        return ''
      }
    },
    height: {
      type: Number,
      required: false,
      default: () => {
        return 300
      }
    }
  },
  data() {
    return {
      id: `meditor-${+new Date()}`,
      inited: false,
      editor: null
    }
  },
  watch: {
    value(newVal) {
      if (!this.inited) {
        this.editor.setHtml(newVal)
        this.inited = true
      }
    }
  },
  mounted() {
    this.editor = new Editor({
      el: this.$refs.el,
      ...Object.assign({}, defaults, {
        height: this.height + 'px'
      })
    })
    this.editor.on('change', () => {
      const value = this.editor.getHtml()
      this.$emit('input', value)
    })
    this.editor.addHook('addImageBlobHook', (file, callback, ui) => {
      api.upload(file).then((resp) => {
        if (resp.success) {
          callback('http://localhost:8888/api/file/' + resp.data, file.name)
        }
      })
    })
  },
  methods: {
    getContent() {
      return this.editor.getHtml()
    },
    getPlainContent() {
      return this.editor.getMarkdown()
    }
  }
}
</script>

<style>

</style>
