<template>
  <el-tag
    hit
    :color="tag.theme || '#409EFF'"
    class="tag">
    {{ tag.name }}
  </el-tag>
</template>

<script>
export default {
  props: {
    tag: {
      type: Object,
      required: true
    }
  }
}
</script>

<style scoped>
  .tag {
    margin-right: 5px;
    margin-bottom: 5px;
    border: none;
    color: #fff;
  }
</style>
