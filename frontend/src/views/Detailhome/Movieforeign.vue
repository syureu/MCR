<template>
    <div id="container">
        <br>
                <br>
                <br>
                <br>
                <br>
                <br><br>
         <div class="location" id="home">
            <MovieItemList :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
            <MovieItemList1 :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
            <MovieItemList2 :name="recommandMovie2.title" :movies1="recommandMovie2.Movie1" :movies2="recommandMovie2.Movie2"/>
            <MovieItemList3 :name="recommandMovie3.title" :movies1="recommandMovie3.Movie1" :movies2="recommandMovie3.Movie2"/>
            <MovieItemList4 :name="recommandMovie4.title" :movies1="recommandMovie4.Movie1" :movies2="recommandMovie4.Movie2"/>
            </div>
    </div>
</template>
<script>

import MovieItemList from '@/components/Movie/MovieItemList.vue'
import MovieItemList1 from '@/components/Movie/MovieItemList1.vue'
import MovieItemList2 from '@/components/Movie/MovieItemList2.vue'
import MovieItemList3 from '@/components/Movie/MovieItemList3.vue'
import MovieItemList4 from '@/components/Movie/MovieItemList4.vue'
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
            recommandMovie3:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
            recommandMovie4:{
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
        axios.get(`${URL.BASE_URL}/mcr/daummovie/foreignmovie`)
            .then(res => {
                this.recommandMovie.title= res.data.object[0]["mt0"]+" 장르"
                for(let i in res.data.object[0]["mg0"]){
                    if(i<5){
                        if(res.data.object[0]["mg0"][i]["imgUrl"]==null){
                            res.data.object[0]["mg0"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie1.push({
                            "id":res.data.object[0]["mg0"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg0"][i]["imgUrl"],
                            "title":res.data.object[0]["mg0"][i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object[0]["mg0"][i]["imgUrl"]==null){
                            res.data.object[0]["mg0"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie2.push({
                            "id":res.data.object[0]["mg0"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg0"][i]["imgUrl"],
                            "title":res.data.object[0]["mg0"][i]["movieName"]
                        })
                    }
                }
                this.recommandMovie1.title= res.data.object[0]["mt1"]+" 장르"
                for(let i in res.data.object[0]["mg1"]){
                    if(i<5){
                        if(res.data.object[0]["mg1"][i]["imgUrl"]==null){
                            res.data.object[0]["mg1"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie1.Movie1.push({
                            "id":res.data.object[0]["mg1"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg1"][i]["imgUrl"],
                            "title":res.data.object[0]["mg1"][i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object[0]["mg1"][i]["imgUrl"]==null){
                            res.data.object[0]["mg1"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie1.Movie2.push({
                            "id":res.data.object[0]["mg1"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg1"][i]["imgUrl"],
                            "title":res.data.object[0]["mg1"][i]["movieName"]
                        })
                    }
                }
                
                this.recommandMovie2.title= res.data.object[0]["mt2"]+" 장르"
                for(let i in res.data.object[0]["mg2"]){
                    if(i<5){
                        if(res.data.object[0]["mg2"][i]["imgUrl"]==null){
                            res.data.object[0]["mg2"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie2.Movie1.push({
                            "id":res.data.object[0]["mg2"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg2"][i]["imgUrl"],
                            "title":res.data.object[0]["mg2"][i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object[0]["mg2"][i]["imgUrl"]==null){
                            res.data.object[0]["mg2"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie2.Movie2.push({
                            "id":res.data.object[0]["mg2"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg2"][i]["imgUrl"],
                            "title":res.data.object[0]["mg2"][i]["movieName"]
                        })
                    }
                }
                this.recommandMovie3.title= res.data.object[0]["mt3"]+" 장르"
                for(let i in res.data.object[0]["mg3"]){
                    if(i<5){
                        if(res.data.object[0]["mg3"][i]["imgUrl"]==null){
                            res.data.object[0]["mg3"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie3.Movie1.push({
                            "id":res.data.object[0]["mg3"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg3"][i]["imgUrl"],
                            "title":res.data.object[0]["mg3"][i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object[0]["mg3"][i]["imgUrl"]==null){
                            res.data.object[0]["mg3"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie3.Movie2.push({
                            "id":res.data.object[0]["mg3"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg3"][i]["imgUrl"],
                            "title":res.data.object[0]["mg3"][i]["movieName"]
                        })
                    }
                }
                 this.recommandMovie4.title= res.data.object[0]["mt4"]+" 장르"
                for(let i in res.data.object[0]["mg4"]){
                    if(i<5){
                        if(res.data.object[0]["mg4"][i]["imgUrl"]==null){
                            res.data.object[0]["mg4"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie4.Movie1.push({
                            "id":res.data.object[0]["mg4"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg4"][i]["imgUrl"],
                            "title":res.data.object[0]["mg4"][i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object[0]["mg4"][i]["imgUrl"]==null){
                            res.data.object[0]["mg4"][i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie4.Movie2.push({
                            "id":res.data.object[0]["mg4"][i]["movieId"],
                            "posterPath":res.data.object[0]["mg4"][i]["imgUrl"],
                            "title":res.data.object[0]["mg4"][i]["movieName"]
                        })
                    }
                }
                
        
      })
      .catch(err => {
        alert(err)
      })
    },
    components:{
        MovieItemList,
        MovieItemList1,
        MovieItemList2,
        MovieItemList3,
        MovieItemList4
    },
    computed:{
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
    },
}
</script>
<style scoped>
    #container{
         margin-left:2%;
        background-color: black;
        color: var(--light);
        font-family: Arial, Helvetica, sans-serif;
        box-sizing: border-box;
        line-height: 1.4;
    }


</style>