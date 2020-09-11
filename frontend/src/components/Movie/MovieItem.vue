<template>
        <a href=""><img :src="movie.Imgsrc" @click.native="changeDeatil"/></a>
</template>
<script>
export default {
    
    props:{
        movie : Object
    },
    methods:{
        changeDeatil(){
            this.$router.push(`/detailfeed/${this.movie.No}`)
        }
    }
}
</script>
<style scoped>
a {
  transition: transform .3s; 
  display:flex; 
}
a:hover {
  transition: transform .3s;
  -ms-transform: scale(1.4);
  -webkit-transform: scale(1.4);  
  transform: scale(1.4);
}
img {
  max-width: 100%;
  border-radius: 2px;
}
</style>