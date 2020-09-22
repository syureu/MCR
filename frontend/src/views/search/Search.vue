<template>
    <div class="app">
            <h1 v-text="title"></h1>
        <div v-if="!isNull">
            <h3>해당검색결과가 없습니다.</h3>
        </div>
        <div class="box" v-else  v-for="movie in Movie" :key="movie.id">
          <a href=""><img :src="movie.imgUrl"  style="width:300px; hegith:250px; float:left;" alt="영화 이미지" @click="changeDeatil(movie.movieId)" /></a>
                <h3 v-text="movie.movieName"></h3>
        </div>
        <div style="clear:both;"></div>
    </div>
</template>
<script>
import axios from 'axios'
import URL from '@/util/http-common.js'

export default {
    data(){
        return{
            title:"",
            Movie:[],
            isNull:true
        }
    }, 
    created(){
        axios.get(`${URL.BASE_URL}/mcr/daummovie/bytitle`,{
          params : {title:`${this.$route.params.keyword}`}
        })
        .then(res=>{
          console.log(res)
          this.Movie=res.data.object
          this.Movie.forEach(index => {
              this.Movie[index]["hover"]=false
          });
          console.log(this.Movie)
          if(this.Movie==null){
            this.isNull=false
          }
        })
        .catch(err=>{
          console.log(err)
        })

        
    },
    methods:{
       changeDeatil(id){
            alert(id)
            this.$router.push(`/feedDetail/${id}`)
        },
    }
}
</script>
<style scoped>
.app{
    background:black;
    margin-top:50px;
    width:100%;
    height: 650px;
}
 h1 {
  padding-top: 100px;  
  margin-left:10px;
    margin-bottom: 30px;
    color:aliceblue;
}
h3{
    color:aliceblue;
}
.box {
  /* display: grid; */
  /* grid-gap: 20px;
  grid-template-columns: repeat(6, minmax(100px, 1fr)); */
}

a {
  transition: transform .3s;
}

 a :hover {
  transition: transform .3s;
  -ms-transform: scale(1.4);
  -webkit-transform: scale(1.4);  
  transform: scale(1.4);
}
.card-cover{
    position: absolute;
        top: 0;
        left: 15px;
        width: 80%;
        height: 100%;
        background-color: transparent;
        color: transparent;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
}
.card-cover :hover{
    background-color: rgba(0,0,0,0.5);
        color: whitesmoke;
}


</style>