<template>
  <div id="app">
    <nav>
      <router-link to="/">Home</router-link> |
      <router-link to="/login">Login</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script lang="ts">
export default {
  name: 'App'
}
</script>

<style>
/* 在此处添加你的全局样式 */
</style>
