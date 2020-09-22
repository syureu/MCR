<template>
    <div class="app">
            <h1 v-text="title"></h1>
        <div v-if="!isNull">
            <h3 class="result">해당검색결과가 없습니다.</h3>
        </div>
        <div class="row" v-else>
        <div class="col"  style="float:left;" v-for="movie in Movie" :key="movie.id">
          <a href=""><img :src="movie.imgUrl"   style="width:200px; height:350px;" alt="영화 이미지" @click="changeDeatil(movie.movieId)" /></a>
               <div class="card-cover" style="width:200px; height:350px;">
                <h3 v-text="movie.movieName"></h3>
                <h3  v-text="movie.rate"></h3>
               </div>
        </div>
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
          
          console.log(this.Movie)
          if(this.Movie.length==0){
            this.isNull=false
          }
        })
        .catch(err=>{
          console.log(err)
        })

        this.title=`${this.$route.params.keyword}의 검색결과`
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
.result{
  color:aliceblue;
}
.app{
    background:black;
    padding-top:100px;
    width:100%;
    height: 100%;
    overflow-x:"hidden"
}
 h1 {
  margin-left:100px;
  margin-bottom: 30px;
  color:aliceblue;
}
.row{
  margin-left:10px;
  display:flex;

}
.col{
  position:relative;
  width:300px;
  height: 300px;
}
h3{
    /* color:aliceblue; */
}
.box {
  /* display: grid; */
  /* grid-gap: 20px;
  grid-template-columns: repeat(6, minmax(100px, 1fr)); */
}
a {
  transition: transform .3s; 

}
/* a :hover {
  transition: transform .3s;
  -ms-transform: scale(1.3);
  -webkit-transform: scale(1.3);  
  transform: scale(1.3);
  opacity: 0.5;
  

} */

.card-cover{
    position: absolute;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: transparent;
        color: transparent;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
}
.card-cover:hover{
    background-color: rgba(0,0,0,0.5);
        color: whitesmoke;
}


</style>