<template>
    <div class="main-container"> 
        <br>
            <div class="location" id="home">
            <MovieItemList :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
            <MovieItemList2 :name="recommandMovie2.title" :movies1="recommandMovie2.Movie1" :movies2="recommandMovie2.Movie2"/>
            <MovieItemList1 :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
            <div>
          
            </div>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/Movie/MovieItemList.vue'
import MovieItemList1 from '@/components/Movie/MovieItemList1.vue'
import MovieItemList2 from '@/components/Movie/MovieItemList2.vue'

import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name : 'Home',
    components:{        
        MovieItemList,
        MovieItemList1,
        MovieItemList2,
    },
    data(){
        return {
            recommandMovie:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
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
            genre:["액션",]
            
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
  
    created(){
         if (!this.$store.getters.isLoggedIn) {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 401
                }
            })
        }
        axios.get(`${URL.BASE_URL}/mcr/daummovie/searchrank/`)
            .then(res => {
                console.log(res)
                this.recommandMovie.title= res.data.object.title
                for(let i in res.data.object.movies){
                    if(i<5){
                        if(res.data.object.movies[i]["imgUrl"]==null){
                            res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie1.push({
                            "id":res.data.object.movies[i]["movieId"],
                            "posterPath":res.data.object.movies[i]["imgUrl"],
                            "title":res.data.object.movies[i]["movieName"],
                            
                        })
                    }
                   else{
                        if(res.data.object.movies[i]["imgUrl"]==null){
                            res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie2.push({
                            "id":res.data.object.movies[i]["movieId"],
                            "posterPath":res.data.object.movies[i]["imgUrl"],
                            "title":res.data.object.movies[i]["movieName"],
                      
                        })
                    }
                }
        
      })
      .catch(err => {
        alert(err)
      })
      axios.get(`${URL.BASE_URL}/mcr/daummovie/likerank`)
            .then(res => {
                console.log(res)
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
                console.log(res)
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
 
  margin: 0%;
  padding: 5%;
  background-color: black;
  color: var(--light);
  font-family: Arial, Helvetica, sans-serif;
  box-sizing: border-box;
  line-height: 1.4;
}



</style>