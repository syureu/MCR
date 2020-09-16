<template>
        <div id="movies">
          <a href=""><img :src="movie.Imgsrc" @mouseup="showchooseBox" @click="changeDeatil"/></a>
        <div class="choosebox">
            <i v-if="movie.isFollwing" class="far fa-heart" @click="follow" ></i>
            <i v-else class="fas fa-heart"></i>
            <i class="far fa-thumbs-down" style="margin-left:10px"></i>
            <label for="toggle" />
            <i class="fas fa-bars" id="toggle"></i>
            
        </div>
        </div>
</template>
<script>
export default {
    data(){
      return {
        showbox:false,
       
      }

    },
    
    props:{
        movie : Object
    },
    methods:{
        follow(){
          this.movie.isFollwing=!this.movie.isFollwing
        },
        showchooseBox(){
          this.showbox = !this.showbox

      },
        changeDeatil(){
            this.$router.push(`/detailfeed/${this.movie.No}`)
        }
    }
}
</script>
<style scoped>
 .fa-bars{
  margin-left:65%;
  
}
.fa-heart{
  color:red;
}
a {
  transition: transform .3s; 
  display:flex; 
}
#movies:hover {
  transition: transform .3s;
  -ms-transform: scale(1.3);
  -webkit-transform: scale(1.3);  
  transform: scale(1.3);

}
img {
  
  max-width: 100%;
  border-radius: 2px;
}
</style>