<template>
    <div id="actDetailContainer">
        <div id="innerContainer">
            <div id="actorImg">
                <img id="actimg" :src="getImgUrl">
            </div>
            <div id="actorDetail" style="font-size:20px; border: 10px solid grey; height:500px;">
                <div id="actName" style="float:left; margin-right:10px;">이름 : {{ this.actorDetail.actorname }} &nbsp;&nbsp;&nbsp;&nbsp; </div>  <div v-if="this.$store.getters.getUserData != null">
                                        <svg      
                                        class="svg-inline--fa fa-heart fa-w-16 icon full"
                                        aria-hidden="true"
                                        data-prefix="fas"
                                        data-icon="heart"
                                        role="img"
                                        xmlns="http://www.w3.org/2000/svg"
                                        viewBox="0 0 512 512"
                                        data-fa-i2svg
                                        cursor="pointer"
                                        width="30px"
                                        @click="like()"
                                        >
                                        <path
                                        v-if="like_on == 1"
                                            fill="crimson"
                                            d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                                            />
                                        <path
                                            v-else-if="like_on == 0"
                                            fill="white"
                                            d="M462.3 62.6C407.5 15.9 326 24.3 275.7 76.2L256 96.5l-19.7-20.3C186.1 24.3 104.5 15.9 49.7 62.6c-62.8 53.6-66.1 149.8-9.9 207.9l193.5 199.8c12.5 12.9 32.8 12.9 45.3 0l193.5-199.8c56.3-58.1 53-154.3-9.8-207.9z"
                                            />
                                        </svg> 
                                    </div><br><br>
                <div>직업 : {{ this.actorDetail.job }}</div><br><br>
                <div>출생 : {{ this.actorDetail.birth }}</div><br><br>
                <div>성별 : {{ this.actorDetail.sex }}</div><br><br>
            </div>
            <h1 v-if="this.actorDetail.job=='영화감독' || this.actorDetail.job=='감독' || this.actorDetail.job=='촬영감독' || this.actorDetail.job=='연출'" style="color:white; clear:both;">{{ this.actorDetail.actorname }} {{this.actorDetail.job}}의 연출작</h1>
            <h1 v-else style="color:white; clear:both;">{{ this.actorDetail.actorname }} 배우의 출연작</h1>
            <hr style="background-color:white;">
            <div style="text-align:center;">
            <carousel  style="width:1000px; display:inline-block; margin-top:30px;" :items="itemNumber" :nav="true" :autoplay="true" v-if="movieInfo && movieInfo.length" >
                <div style="display: inline-block; "  v-for="movie in movieInfo" :key="movie.movieId">
                    <img style="width:200px; height:250px;" v-if="movie.imgUrl==''" src="https://png.pngtree.com/png-vector/20191001/ourlarge/pngtree-man-icon-isolated-on-abstract-background-png-image_1769021.jpg" @click="$router.push({name: 'ActorDetail', params: {personId: actor.personId}})" alt="">
                    <img style="width:200px; height:250px;" v-else :src="movie.imgUrl" alt="" @click="$router.push({name: 'FeedDetail', params: {movieId: movie.movieId}})" />
                    <br>
                    <h3 style="color: white; text-align:center; font-size:15px;"> {{ movie.movieName }}  </h3>
                </div>
            </carousel>
            </div>
            <div style="clear:both; padding-bottom: 100px;"></div>
            <!-- <div id="actMovie" v-for="movie in movieInfo" :key="movie.movieName" >
                <div id="movitem">
                <img id="movimg" :src="movie.imgurl" alt="" style="width:200px; height:300px"  @click="$router.push({name: 'FeedDetail', params: {movieId: movie.movieId}})">
                <div id="movname">{{ movie.movieName }}</div>
                </div>
            </div>
            <div style="clear:both;"></div> -->
        </div>


    </div>
</template>

<script>

import carousel from 'vue-owl-carousel'
import axios from 'axios'
import HTTP from "@/util/http-common.js"
export default {
    name: 'ActorDetail',

    components: {
        carousel,
    },

    data(){
        return{
            actorDetail: {
                actorname:"",
                job:"",
                actorno:"",
                birth:"",
                sex:"",
                imgUrl:"",
            },
            itemNumber: 4,
            userno:"",
            name: '송강호',
            job : '영화배우',
            birth : '1967.01.17.대한민국 경남 김해시',
            sex : '남성',
            movieInfo: [],
            like_on : 0,
            movieList:[],
        }
    },
    computed: {
        getImgUrl() {
          return   `${this.actorDetail.imgUrl}`
        },
    },

    methods: {
        like(){
          if (this.like_on == 0) {
            this.like_on = 1;
            let like= {
                personId: this.actorDetail.actorno,
                userNo: this.userno,
            };
            axios.post(`${HTTP.BASE_URL}/mcr/daumuseractor`, like)
            .then(res => {
              console.log(res)
            })
            .catch(res => {
              console.log(res)
            })
          }
          else {
            this.like_on = 0;
            let like= {
                personId: this.actorDetail.actorno,
                userNo: this.userno,
            };
            axios.post(`${HTTP.BASE_URL}/mcr/daumuseractor`, like)
            .then(res => {
                console.log(res)
            })
            .catch(res => {
                console.log(res)
            })
          }
    },
    toMovieDetail() {
        this.$router.push(`//${this.movieInfo.name}`)
    }


    },
    created() {
        if(this.$store.getters.getUserData == null){
            this.userno = 0;
        } else{
            this.userno = this.$store.getters.getUserData.userinfo.userNo
            console.log(this.userno)
        }
        axios.get(`${HTTP.BASE_URL}/mcr/daumactor` ,
        {
                params: { personId: `${this.$route.params.personId}` }
        }
        )
        .then(res => {
             console.log(res.data.object.actorName)
            if (res.data === 'fail') {
                this.$router.push({
                    name: 'Error',
                    query: {
                        status: 404
                    }
                })
            }
            this.actorDetail = {
                actorname: res.data.object.actorName,
                job: res.data.object.job,
                actorno: res.data.object.personId,
                birth: res.data.object.birth,
                sex: res.data.object.gender,
                imgUrl: res.data.object.imgUrl,
            }
            console.log(this.actorDetail)
        })
        .catch(err => {
        //     this.$router.push({
        //         name: 'Error',
        //         query: {
        //             status: 'unknown'
        //         }
        //     })
            console.log(err)
        })
       
        axios.get(`${HTTP.BASE_URL}/mcr/daummovieactor/filomography`, 
        {
            params : { personId : `${this.$route.params.personId}` }
        })
        .then(res => {
            this.movieInfo = res.data.object
            console.log(res)
        })


        axios.get(`${HTTP.BASE_URL}/mcr/daumuseractor/check`, 
        {
            params : { personId : `${this.$route.params.personId}` , userNo : this.userno}
        })
        .then(res => {
            this.like_on = res.data.object
        })
    }
}
</script>

<style scoped>
    #actorImg{
        float: left;
        margin: 50px;
    }
    #actimg{
        width: 400px;
        height: 500px;
    }
    #actorDetail{
        margin: 50px;
        color: white;
        float:left;
        padding: 45px;
        width: 40%;
    }
    #actDetailContainer {
        background-color:black;
        height:100%;
        
    }
    #innerContainer{
        padding-top : 60px;
        margin-left : 200px;
        margin-right : 100px;
    }

    #actMovie{
    }

    #movname{
        color:white;
    }

    #movitem{
        float:left;
        margin-left: 20px;
        margin-right: 20px;
        margin-bottom: 20px;
    }

    .like-container{filter: url('#filter');}
    .like-cnt{  
        position: absolute; 
        cursor: pointer;
        left: 50%; 
        top: 50%; 
        transform: translate(-50%, -50%);     background: rgba(255,255,255,0.3);     width: 60px; 
        height: 60px;  
        border-radius: 50%;
        text-align: center;
        line-height: 75px;
        z-index: 10;
    }
    .like-btn{
        color: #fff;    
    }
</style>