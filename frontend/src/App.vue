<template>
  <div id="app">
    <navbar/>
    <router-view @navbar="navbar"></router-view>
    
  </div>
</template>

<script>

import navbar from '@/components/navbar/Navbar.vue'
export default {
  name: 'App',
  components: {
    navbar
  },
  methods: {
    navbar() {
      this.$store.dispatch('setNavbar')
      console.log(this.$store)
    }
  }
}
</script>

<style>
</style>
