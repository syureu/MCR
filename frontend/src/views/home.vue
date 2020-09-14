<template>
    <div class="main-container">
        <br>
        <br>

        <div class="location" id="home">
            <MovieItemList :name="title" :movies="LikeMovie"/>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/Movie/MovieItemList.vue'

export default {
    name : 'Home',
    components:{
        MovieItemList
    },
    data(){
        return {
            LikeMovie:[
                {
                    "Imgsrc":"https://github.com/carlosavilae/Netflix-Clone/blob/master/img/p2.PNG?raw=true",
                    "isFollwing" : false
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

    }
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