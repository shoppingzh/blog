<template>
  <div>
    <text @click="to" class="message">Now, let's use Vue.js to build your Weex app.</text>
  </div>
</template>

<script>
export default {
  methods: {
    to () {
      this.$router.push({ path: '/nice' })
    }
  }
}
</script>
