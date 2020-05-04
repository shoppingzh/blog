<template>
  <div  class="app-container">
    <zx-category-folder></zx-category-folder>
  </div>
</template>

<script>
import ZxCategoryFolder from '@/components/CategoryFolder'

export default {
  components: {
    ZxCategoryFolder
  }
}
</script>

<style scoped>
    .operation .el-link{ margin-right: 5px; }
</style>
