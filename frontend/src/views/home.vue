<template>
    <div class="main-container">
        <br>
        <br>
        <div>
            <Content v-if="!isLoggedIn"/>
        </div> 
            <div class="location" id="home" v-if="isLoggedIn">
                <div>
            <MovieItemList :dynamicId="ca1" :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
                </div>
                <div>
            <MovieItemList :dynamicId="ca2" :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
                </div>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/Movie/MovieItemList.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'
import Content from '@/components/home/Content.vue'
export default {
    name : 'Home',
    components:{
        Content,
        MovieItemList
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
            LikeMovie:[
                {
                    "Imgsrc":"https://github.com/carlosavilae/Netflix-Clone/blob/master/img/p2.PNG?raw=true",
                    "isFollwing" : false,
                },
                {
                    "Imgsrc":"https://github.com/carlosavilae/Netflix-Clone/blob/master/img/p3.PNG?raw=true",
                    "isFollwing" : true
                },
                {
                    "Imgsrc":"https://github.com/carlosavilae/Netflix-Clone/blob/master/img/p4.PNG?raw=true",
                    "isFollwing" : false
                },
                {
                    "Imgsrc":"https://github.com/carlosavilae/Netflix-Clone/blob/master/img/p5.PNG?raw=true",
                    "isFollwing" : false
                },
            ],  
            title:"20대가좋아하는 영화"
        }
    },
    methods:{

    },
    computed:{
        isLoggedIn() {
            return this.$store.getters.isLoggedIn
        }
        
    },
  
    created(){
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
                            "title":res.data.object.movies[i]["movieName"]
                        })
                    }
                   else{
                        if(res.data.object.movies[i]["imgUrl"]==null){
                            res.data.object.movies[i]["imgUrl"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie2.push({
                            "id":res.data.object.movies[i]["movieId"],
                            "posterPath":res.data.object.movies[i]["imgUrl"],
                            "title":res.data.object.movies[i]["movieName"]
                        })
                    }
                }
            //     this.recommandMovie.Movie.forEach( Movie=> {
            //         Movie.follow = false
            //     if(Movie.posterPath===null)
            //         Movie.posterPath="@/assets/logo.png"
            //   })
                console.log(res)
        
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
            //     this.recommandMovie.Movie.forEach( Movie=> {
            //         Movie.follow = false
            //     if(Movie.posterPath===null)
            //         Movie.posterPath="@/assets/logo.png"
            //   })
                console.log(res)
        
      })
      .catch(err => {
        alert(err)
      })
    },

    
}
</script>
<style scoped>
    .main-container  {
  width: 100vw;
  min-height: 100vh;
  margin: 0%;
  padding: 5%;
  background-color: black;
  color: var(--light);
  font-family: Arial, Helvetica, sans-serif;
  box-sizing: border-box;
  line-height: 1.4;
}

.main-container img {
  max-width: 100%;
}

</style>