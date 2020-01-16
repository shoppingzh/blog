<template>
  <div>
      <el-tree
        :data="tree"
        :props="{ label: 'name', children: 'children' }"></el-tree>
    </div>
</template>

<script>
import * as api from '@/api/category'

export default {
    data() {
        return {
            tree: []
        }
    },
    mounted() {
        api.tree().then((resp) => {
            if(resp.success) {
                this.tree = resp.data
            }
        })
    }
}
</script>

<style>

</style>
