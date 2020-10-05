<template>
    <div class="app">
            <h1 v-text="title" style="font-family: 'Hanna', sans-serif;"></h1>
        <div v-if="!isNull">
            <h3 class="result" style="font-family: 'Hanna', sans-serif;">해당검색결과가 없습니다.</h3>
        </div>
        <div class="card-list">
         <div class="search-result-card col-12 col-md-6 col-xl-2 font-kor" v-for="movie in Movie" :key="movie.id">
                <div @click="changeDeatil(movie.movieId)" class="card-img">
                  <img :src="movie.imgUrl"  alt="영화 이미지"  />
                  <div class="card-cover"  >
                <h2 v-text="movie.movieName"></h2>
                <h2 v-text="movie.rate"></h2>
                
               </div>
                </div>
          
         </div>
         <infinite-loading @infinite="infiniteHandler" spinner="waveDots">
          <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">목록의 끝입니다 :)</div>
        </infinite-loading>
         
         
        <!-- <div class="content11" v-else style="width:500px; height:350px" v-for="movie in Movie" :key="movie.id">
          <a href=""><img :src="movie.imgUrl"   style="width:200px; height:350px;" alt="영화 이미지"  /></a>
               <div class="card-cover" style="width:200px; height:350px; float:left;" @click="changeDeatil(movie.movieId)">
                <h3 v-text="movie.movieName"></h3>
                <h3  v-text="movie.rate"></h3>
               </div>
        </div> -->
        </div>
    </div>
</template>
<script>
import axios from 'axios'
import URL from '@/util/http-common.js'
import InfiniteLoading from 'vue-infinite-loading'
export default {
    data(){
        return{
            title:"",
            Movie:[],
            isNull:true,
            page:0
        }
    },components:{
      InfiniteLoading 
    },
     methods:{
       infiniteHandler($state){
         axios.get(`${URL.BASE_URL}/mcr/daummovie/page`,{
          params : {title:`${this.$route.params.keyword}`,
                    page : `${this.page}`,
          }
       })
       .then(res=> {
         setTimeout(()=>{
           if(res.data.object.length===30){
             this.Movie = this.Movie.concat(res.data.object);
             $state.loaded();
              this.page+=1;
              
           }else{
             $state.complete();
           }
         },500)
       })
       .catch(err=>{
         console.error(err);
       })
    },
     changeDeatil(id){
            this.$router.push(`/feedDetail/${id}`)
        }
    },
    mounted(){
        axios.get(`${URL.BASE_URL}/mcr/daummovie/page`,{
          params : {title:`${this.$route.params.keyword}`,
                    page : 0,
          }
        })
        .then(res=>{
          this.Movie=res.data.object
          
          if(this.Movie.length==0){
            this.isNull=false
          }
          
        })
        .catch(err=>{
          console.log(err)
        })
        this.page+=1;
        this.title=`${this.$route.params.keyword}의 검색결과`
    },
    
}
</script>
<style scoped>
.result{
  color:aliceblue;
}
.app{
    background:black;
    padding-top:150px;
}
 h1 {
  margin-left:100px;
  margin-bottom: 30px;
  color:aliceblue;
}
.search-result-card {
        display: inline-block;
        padding: 10px;
       
    }
    .card-cover {
      font-family: 'Hanna', sans-serif;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height:100%;
        background-color: transparent;
        color: transparent;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
       
    }
    .card-cover:hover {
        background-color: rgba(0,0,0,0.5);
        color: whitesmoke;
    }
    .card-img {
        position: relative;
        cursor: pointer;
        height: 100%;
        width: 100%;
    }
    .card-img img{
        width: 100%;
        height: 100%;
        max-width: 100%;
        max-height: 100%;
        object-fit: contain;
    }
    .card-list {
        margin-top: 30px;
    }
    
</style>