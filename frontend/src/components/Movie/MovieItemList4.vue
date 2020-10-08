<template>
    <div id="content">
        <h1 id="movies" style="font-family: 'Hanna', sans-serif;" v-text="name"></h1>
        <div id="dynamicId4" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
               <div  class="carousel-item active" >
                    <div class="row">
                    <div class="col" v-for="movie in movies1" :key="movie.id">
                        <a href=""><img :src="movie.posterPath" class="d-block" style="width:240px; height:350px"  alt="영화 이미지" @click="changeDeatil(movie.id)"/></a>
                        <div class="card-cover" @click="changeDeatil(movie.id)">
                            <h3 v-text="movie.title"></h3>
                        </div>
                    </div>
                    </div>
               </div>
               <div class="carousel-item">
                    <div class="row">
                    <div class="col" v-for="movie in movies2" :key="movie.id">
                        <a href=""><img :src="movie.posterPath" class="d-block" style="width:240px; height:350px" alt="영화 이미지" @click="changeDeatil(movie.id)"/></a>
                        <div class="card-cover" @click="changeDeatil(movie.id)">
                            <h3 v-text="movie.title"></h3>
                        </div>
                    </div>
                    </div>
               </div>
    
        </div>
     <a class="carousel-control-prev" href="#dynamicId4" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
    </a>
  <a class="carousel-control-next" href="#dynamicId4" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
        </div>
    </div>
</template>
<script>

// import MovieItem from './MovieItem'
export default {
    components:{
        // MovieItem
    },
    props:{
        name : String,
        movies1 : Array,
        movies2 : Array,
    },
    data() {
        return {
            show:true,
            test: [],
            test1: [],            
        }
    },
    methods: {
        changeDeatil(id){
         
            this.$router.push(`/feedDetail/${id}`)
        },
        changeimg(){
            var menuBtn1 = document.getElementById("ele").firstElementChild;
            var menuBtn2 = document.getElementById("ele").lastElementChild;
            if(menuBtn1.className=="carousel-item active"){
                menuBtn1.className="carousel-item"
                menuBtn2.className="carousel-item active"
            }
            else{
                menuBtn1.className="carousel-item active"
                menuBtn2.className="carousel-item"
            }
           
            
        }
    },computed:{

    }
}
</script>
<style scoped>
.carousel-control-prev{
    width:2%;
}
.carousel-control-next{
    
    width:2%;
}
.content h1 {
  margin-top: 160px;  

}
a {
  transition: transform .3s; 

}
a :hover {
  transition: transform .3s;
  -ms-transform: scale(1.3);
  -webkit-transform: scale(1.3);  
  transform: scale(1.3);
  opacity: 0.5;
  

}
.card-cover{
    position: absolute;
        top: 0;
        left: 15px;
        width: 100%;
        height: 100%;
        background-color: transparent;
        color: transparent;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-family: 'Hanna', sans-serif;
}
.card-cover:hover{
    background-color: rgba(0,0,0,0.5);
        color: whitesmoke;
}
h1{
    color:aliceblue;
}


</style>