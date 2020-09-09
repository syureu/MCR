<template>
        <a  href=""><img :src="movie.Imgsrc" @click.native="changeDeatil"/></a>
</template>
<script>
export default {
    name='MovieListItem',
    props:{
        movie : Object
    },
    methods:{
        changeDeatil(){
            this.$router.push(`/detailmovie/${this.movie.No}`)
        }
    }
}
</script>
<style scoped>
a {
  transition: transform .3s;  
}
a:hover {
  transition: transform .3s;
  -ms-transform: scale(1.4);
  -webkit-transform: scale(1.4);  
  transform: scale(1.4);
}
img {
  border-radius: 2px;
}
</style>