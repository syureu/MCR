<template>
    <div id="container">
         <div class="location" id="home">
            <MovieItemList :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
            <MovieItemList2 :name="recommandMovie2.title" :movies1="recommandMovie2.Movie1" :movies2="recommandMovie2.Movie2"/>
            <MovieItemList1 :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
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
        axios.get(`${URL.BASE_URL}/mcr/daummovie/koreamovie`)
            .then(res => {
                console.log(res)
                // console.log(1)
                // console.log(res.data.object[0]["장르"])

                // this.recommandMovie.title= res.data.object.title
                // for(let i in res.data.object.movies){
                //     if(i<5){
                //         if(res.data.object.movies[i]["imgUrl"]==null){
                //             res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                //         }
                //         this.recommandMovie.Movie1.push({
                //             "id":res.data.object.movies[i]["movieId"],
                //             "posterPath":res.data.object.movies[i]["imgUrl"],
                //             "title":res.data.object.movies[i]["movieName"]
                //         })
                //     }
                //    else{
                //         if(res.data.object.movies[i]["imgUrl"]==null){
                //             res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                //         }
                //         this.recommandMovie.Movie2.push({
                //             "id":res.data.object.movies[i]["movieId"],
                //             "posterPath":res.data.object.movies[i]["imgUrl"],
                //             "title":res.data.object.movies[i]["movieName"]
                //         })
                //     }
                // }
        
      })
      .catch(err => {
        alert(err)
      })
    },
    components:{
        MovieItemList,
        MovieItemList1,
        MovieItemList2,
    },
    computed:{
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
    },
}
</script>