<template>
    <div class="main-container"> 
            <div class="location" id="home">
                <br>
                <br>
                <br>
                <br>
                <br>
                <br><br>
            <MovieItemList2 :name="recommandMovie2.title" :movies1="recommandMovie2.Movie1" :movies2="recommandMovie2.Movie2"/>
            <MovieItemList1 :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
            <MovieItemList v-if="isNull" :name="recommandMovie3.title" :movies1="recommandMovie3.Movie1" :movies2="recommandMovie3.Movie2"/>
             <div class="card-list">
                <h1 style="font-family: 'Hanna', sans-serif;">MCR 인기영화</h1>
         <div class="search-result-card col-12 col-md-6 col-xl-2 font-kor" v-for="movie in recommandMovie" :key="movie.id">
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
         
         
        
        </div>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/Movie/MovieItemList.vue'
import MovieItemList1 from '@/components/Movie/MovieItemList1.vue'
import MovieItemList2 from '@/components/Movie/MovieItemList2.vue'
import InfiniteLoading from 'vue-infinite-loading'

import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name : 'Home',
    components:{        
        MovieItemList,
        MovieItemList1,
        MovieItemList2,
        InfiniteLoading
    },
    data(){
        return {
            recommandMovie:[],
            recommandMovie1:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
             
            recommandMovie2:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
             recommandMovie3:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
            page:0,
            isNull:true
            
        }
    },
    computed:{
        isLoggedIn() {
            if(this.$store.getters.isLoggedIn===true){
                this.isLogin()
               
            }
            return this.$store.getters.isLoggedIn
        }
       
    },
    methods:{
         infiniteHandler($state){
           axios.get(`${URL.BASE_URL}/mcr/daummovie/searchrank`,{
            params : {page:`${this.page}`}
    })
       .then(res=> {
         setTimeout(()=>{
           if(res.data.object.movies.length===30){
             this.recommandMovie = this.recommandMovie.concat(res.data.object.movies);
             $state.loaded();
              this.page+=1;
              
           }else{
             $state.complete();
           }
         },500)
       })
       .catch(err=>{
         console.log(err);
        
       })
    },
     changeDeatil(id){
            this.$router.push(`/feedDetail/${id}`)
        }
    
    },
    mounted(){
        axios.get(`${URL.BASE_URL}/mcr/daummovie/searchrank`,{
            params : {page : 0}
    })
        .then(res=>{
          this.recommandMovie=res.data.object.movies
          if(this.recommandMovie.length==0){
            this.isNull=false
          }
          
        })
        .catch(err=>{
          console.log(err)
        })
        this.page+=1;
    },
    created(){
         if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
      axios.get(`${URL.BASE_URL}/mcr/recommend/similarity/random/${this.$store.getters.getUserData.userinfo.userNo}`)
      .then(res => {
          console.log(res)
          if(res.status===200){
              this.recommandMovie3.title=res.data.recommendMent
                 for(let i in res.data.list){
                    if(i<5){
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/9wQzg7yuLhQhSzSpg7Th3FJP6TuMm1QA-B1wCaoCsedHu-Qu6BPlfnM7PFJhpiguuV1AlHeKSpFx3nKyej0--MYp-P3MXpq1Tz0gy0uJI1nK85hOVQlHRv6__qE";
                        }
                        this.recommandMovie3.Movie1.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
                        })
                    }
                   else{
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/9wQzg7yuLhQhSzSpg7Th3FJP6TuMm1QA-B1wCaoCsedHu-Qu6BPlfnM7PFJhpiguuV1AlHeKSpFx3nKyej0--MYp-P3MXpq1Tz0gy0uJI1nK85hOVQlHRv6__qE";
                        }
                        this.recommandMovie3.Movie2.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
                        })
                    }
                }
          }
          else{
              this.isNull=false
          }

      }) 
      .catch(err=>{
          this.isNull=false
         console.log(err)
      }) 
      axios.get(`${URL.BASE_URL}/mcr/daummovie/likerank`)
            .then(res => {
                this.recommandMovie1.title= res.data.object.title
                for(let i in res.data.object.movies){
                    if(i<5){
                        if(res.data.object.movies[i]["imgUrl"]==null){
                            res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie1.Movie1.push({
                            "id":res.data.object.movies[i]["movieId"],
                            "posterPath":res.data.object.movies[i]["imgUrl"],
                            "title":res.data.object.movies[i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object.movies[i]["imgUrl"]==null){
                            res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie1.Movie2.push({
                            "id":res.data.object.movies[i]["movieId"],
                            "posterPath":res.data.object.movies[i]["imgUrl"],
                            "title":res.data.object.movies[i]["movieName"]
                        })
                    }
                }
            
      })
      .catch(err => {
        alert(err)
      })
       axios.get(`${URL.BASE_URL}/mcr/recommend/simple/random/${this.$store.getters.getUserData.userinfo.userNo}`)
            .then(res => {
                this.recommandMovie2.title= res.data.recommendMent
                for(let i in res.data.list){
                    if(i<5){
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie2.Movie1.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
                        })
                    }
                   else{
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie2.Movie2.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
                        })
                    }
                }
      })
      .catch(err => {
        alert(err)
      })
    },

    
}
</script>
<style scoped>
.main-container  {
     margin-left:2%;
  background-color: black;
  color: var(--light);
  font-family: Arial, Helvetica, sans-serif;
  box-sizing: border-box;
  line-height: 1.4;
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