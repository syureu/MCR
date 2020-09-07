<template>
<div>
    <h1 v-if="show">yes</h1>
    <h1 v-else>no</h1>
    <button @click="showT">change</button>
</div>
  
</template>

<script>
export default {
    data () {
      return {
        show : true,
      }
    },
    methods:{
        showT(){
            this.show = !this.show
        }
    }
}
</script>

<style>

</style>