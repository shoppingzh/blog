<template>
  <a-layout class="app-layout">
    <a-affix :offset-top="0">
      <a-layout-header class="app-layout-header">
        <a-row type="flex" align="middle" justify="space-between">
          <a-col :span="8">
            <div class="logo">
              <router-link to="/">
                <img src="/logo.png">
              </router-link>
            </div>
          </a-col>
          <a-col>
            <a-menu v-model="current" mode="horizontal" class="app-navbar">
              <a-menu-item key="mail3"><a-icon type="compass" />关于作者</a-menu-item>
              <a-menu-item key="message"><a-icon type="message" />留言</a-menu-item>
            </a-menu>
          </a-col>
        </a-row>
      </a-layout-header>
    </a-affix>
    <a-layout-content class="app-layout-content">
      <a-row>
        <a-col :xs="24" :md="24" :xl="{span: 18, offset: 3}">
          <router-view style="margin: auto;" />
        </a-col>
      </a-row>
    </a-layout-content>
    <!-- <a-layout-footer></a-layout-footer> -->
  </a-layout>
</template>

<script>
export default {
  data() {
    return {
      current: ['mail2']
    }
  }
}
</script>

<style scoped>
  .logo{
    height: 100%;
    font-size: 24px;
  }
  .logo img{
    height: 50px;
    object-fit: contain;
  }
</style>
