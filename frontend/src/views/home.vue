<template>
    <div class="main-container">
        <br>
        <br>
        <div class="location" id="home">
            <MovieItemList :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/Movie/MovieItemList.vue'
import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    name : 'Home',
    components:{
        MovieItemList
    },
    data(){
        return {
            
            recommandMovie:{
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
        axios.get(`${URL.BASE_URL}/mcr/recommend/`)
            .then(res => {
                this.recommandMovie.title= res.data.recommendMent
                for(let i in res.data.list){
                    if(i<5){
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie1.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
                        })
                    }
                   else{
                        if(res.data.list[i]["posterPath"]==null){
                            res.data.list[i]["posterPath"]="https://lh3.googleusercontent.com/proxy/rLr6HPTpU3xktj1vwyVQZbTIb6W4uZbltlg0nIv-R9-tSm651mY8zxxRGowtL5ahjWa9q5xT91-lQ_NjnE4TySKxTh1Wvvppbv8-8Q";
                        }
                        this.recommandMovie.Movie2.push({
                            "id":res.data.list[i]["id"],
                            "posterPath":res.data.list[i]["posterPath"],
                            "title":res.data.list[i]["title"]
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