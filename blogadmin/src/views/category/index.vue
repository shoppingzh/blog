<template>
  <div  class="app-container">
    <category-tree></category-tree>
  </div>
</template>

<script>
import CategoryTree from '@/components/CategoryTree'

export default {
  components: {
    CategoryTree
  }
}
</script>

<style scoped>
    .operation .el-link{ margin-right: 5px; }
</style>
