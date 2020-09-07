<template>
  <div id="app">
    <navbar/>
    <Home/>
  </div>
</template>

<script>
import Home from '@/views/main.vue'
import navbar from '@/components/navbar/navbar.vue'
export default {
  name: 'App',
  components: {
    Home,
    navbar

  }
}
</script>

<style>
</style>
