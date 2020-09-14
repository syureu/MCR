<template>
    <div id="actDetailContainer">
        <div id="innerContainer">
            <div id="actorImg">
                <img id="actimg" src="https://pds.joins.com/news/component/htmlphoto_mmdata/201906/10/htm_2019061010382817127.jpg">
            </div>
            <div id="actorDetail" style="font-size:20px; border: 1px solid white; height:500px;">
                <div id="actName" style="float:left; margin-right:10px;">이름 : {{ name }} &nbsp;&nbsp;&nbsp;&nbsp; </div>  <div>
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
                <div>직업 : {{ job }}</div><br><br>
                <div>출생 : {{ birth }}</div><br><br>
                <div>성별 : {{ sex }}</div><br><br>
            </div>
            <h1 style="color:white; clear:both;">{{ name }} 배우의 출연작</h1>
            <hr style="background-color:white;">

            <div id="actMovie" v-for="movie in movieInfo" :key="movie.name" >
                <div id="movitem">
                <img id="movimg" :src="movie.imgurl" alt="" style="width:200px; height:300px"  @click="$router.push({name: 'FeedDetail', params: {movieno: movie.movieno}})">
                <div id="movname">{{ movie.name }}</div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>


    </div>
</template>

<script>
import axios from 'axios'
import HTTP from "@/util/http-common.js"
export default {
    name: 'ActorDetail',
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
            userno:"",
            name: '송강호',
            job : '영화배우',
            birth : '1967.01.17.대한민국 경남 김해시',
            sex : '남성',
            movieInfo: [
                {name:'기생충' ,imgurl : 'https://t1.daumcdn.net/movie/cab3b02a7b274bd6838b80a5e481fedf1559021787090', movieno: '1'}, 
                {name:'마약왕' ,imgurl : 'https://t1.daumcdn.net/movie/358ea40235e240fea2f0b6ec7ef93c431543201035934', movieno: '2'},
                {name:'밀정' , imgurl: 'https://t1.daumcdn.net/movie/9ee4bab1a0f51fb4469b6162bad861f6d25056a7', movieno: '3'},
                {name:'택시운전사', imgurl: 'https://t1.daumcdn.net/movie/c98cf3e74671b88df0f2b31b516c0aaea2e1a816', movieno: '4'},
                {name:'사도', imgurl: 'https://t1.daumcdn.net/movie/f8356ef973b026e937354c6b67fc840afe967ad1', movieno: '5'},
                {name:'관상', imgurl: 'https://t1.daumcdn.net/cfile/276B3548521306AA13', movieno: '6'},
                {name:'변호인', imgurl: 'https://t1.daumcdn.net/cfile/22055D4A5285005C06', movieno: '7'},
                {name:'설국열차', imgurl: 'https://t1.daumcdn.net/cfile/036DD04B51B5236718', movieno: '8'},
                {name:'하울링', imgurl: 'https://t1.daumcdn.net/cfile/190315494F22159229', movieno: '9'},
            ],
            like_on : 1,
            movieList:[],
        }
    },
    methods: {
        like(){
          if (this.like_on == 0) {
            this.like_on = 1;
            axios.post(`${HTTP.BASE_URL}/actor/insertLike`, null,
              {
                params: { actorno:this.actorDetail.actorno, userno: this.userno }
              }
            )
            .then(res => {
              console.log(res)
            })
            .catch(res => {
              console.log(res)
            })
          }
          else {
            this.like_on = 0;
            axios.post(`${HTTP.BASE_URL}/actor/deleteLike`, null,
              {
                params: { actorno:this.actorDetail.actorno, userno: this.userno }
              }
            )
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
        if(this.$store.getters.geutUserData == null){
            this.userno = 0;
        } else{
            this.userno = `${this.$store.getters.getUserData.userno}`
        }
        axios.get(`/actor/detail/actorno=${this.$route.params.actorno}&userno=${this.userno}`)
        .then(res => {
            if (res.data === 'fail') {
                this.$router.push({
                    name: 'Error',
                    query: {
                        status: 404
                    }
                })
            }
            this.actorDetail = {
                actorname: res.data.actorname,
                job: res.data.job,
                actorno: res.data.actorno,
                birth: res.data.birth,
                sex: res.data.sex,
                imgUrl: res.data.imgurl,
            }
        })
        .catch(err => {
            this.$router.push({
                name: 'Error',
                query: {
                    status: 'unknown'
                }
            })
            console.log(err)
        })

        axios.get(`${HTTP.BASE_URL}/movie/actor/${this.$route.params.actorDetail.actorno}`)
        .then(res => {
            this.movieList = res.data
        })
        .catch(err => {
            console.log(err)
        })
        axios.get(`${HTTP.BASE_URL}/user/${this.$route.params.userno}`)
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
    }
    #actDetailContainer {
        background-color:black;
        height:100%;
        
    }
    #innerContainer{
        margin-top : 60px;
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