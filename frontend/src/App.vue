<template>
  <div id="app">
    <router-view></router-view>
    <navbar/>
    
  </div>
</template>

<script>

import navbar from '@/components/navbar/navbar.vue'
export default {
  name: 'App',
  components: {

    navbar

  }
}
</script>

<style>
</style>
