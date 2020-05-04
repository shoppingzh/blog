<template>
  <a-layout class="app-layout">
    <a-affix :offset-top="0">
      <a-layout-header class="app-layout-header">
        <a-row type="flex" align="middle">
          <a-col :span="8">
            <div class="logo">
              <router-link to="/">
                <img src="/logo.png">
              </router-link>
            </div>
          </a-col>
          <a-col :span="16">
            <!-- <a-menu v-model="current" mode="horizontal">
              <a-menu-item key="mail"> <a-icon type="mail" />Navigation One </a-menu-item>
              <a-menu-item key="app"> <a-icon type="appstore" />Navigation Two </a-menu-item>
            </a-menu> -->
          </a-col>
        </a-row>
      </a-layout-header>
    </a-affix>
    <a-layout-content class="app-layout-content">
      <router-view />
    </a-layout-content>
    <!-- <a-layout-footer></a-layout-footer> -->
  </a-layout>
</template>

<script>
export default {

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
