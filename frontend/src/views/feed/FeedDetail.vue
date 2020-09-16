<template>
  <div id="total" style="background-color : black">

    <div class="">
        <hr>
        <hr>
        <hr>
        <hr>
        <hr>
        <div class="trail pl-5 ml-5">
            <div style="background-color: grey; width :50%" class="inline-div countsort pt-3 mt-4">
                <iframe wmode="Opaque" class="p-3 video " width="1560" height="315" :src="trailerURL1" frameborder="0" allow="autoplay;  encrypted-media" allowfullscreen></iframe>
            </div>
            <div class="inline-div pl-3 ml-3" style="height : 300px; width: auto; position : absolute;">
                <div class="example">
                    <div class="tabs" >
                    <TabItem
                        v-for="item in list"
                        v-bind="item" :key="item.id"
                        v-model="currentId"/>
                    </div>
                    <div class="contents">
                    <transition>
                        <section class="item" style="color : white; height:300px; width: auto" :key="currentId">
                            <div style="padding:12px;" v-if="current.content==='콘텐츠1'">
								<div style="border: 5px solid black; border-radius: 8px; padding:13px;">
								<img :src="this.movieDetail.imgurl" alt="" style="float:left; margin-right: 10px; border:10px solid black; border-radius:8px;">
								<div style="float:left;">
									<br>
									{{ this.movieDetail.movieName }} | <i class="fas fa-star" style="color:yellow;"></i>&nbsp;{{ this.movieDetail.rate}} <br><br>
									{{ this.movieDetail.genre }} | {{ this.movieDetail.movieOpeningDate}} <br><br>									{{ this.movieDetail.nation }}
								</div>
								<div style="clear:both;"></div>
								</div>
							</div>
							<div style="padding:5px;" v-if="current.content==='콘텐츠2'">
								<div style="margin:5px;padding:5px;height:280px;width:auto;border:1px solid #ccc;font:16px/26px Georgia, Garamond, Serif;overflow:auto;">
								{{ this.movieDetail.overview }}
								</div>
							</div>
							<div v-if="current.content==='콘텐츠3'">
                                <div  id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                                    <ol class="carousel-indicators">
                                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                                    </ol>
                                    <div class="carousel-inner">
                                        <div class="carousel-item active">
                                        <img src="https://image.chosun.com/sitedata/image/202005/11/2020051102594_0.jpg" style="max-width:100%; height:auto;" alt="...">
                                        </div>
                                        <div class="carousel-item">
                                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/SV100408.JPG/1200px-SV100408.JPG" class="d-block w-100" alt="...">
                                        </div>
                                        <div class="carousel-item">
                                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/SV100408.JPG/1200px-SV100408.JPG" class="d-block w-100" alt="...">
                                        </div>
                                    </div>
                                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                    </div>
                            </div>
                        </section>
                    </transition>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
<div class="container">
    <div class="title" style="margin-top: 100px;">
        <h2 style="color: white;">출연</h2>
        <hr style="background-color:white;">    
    </div>
    <div class="boxA" v-for="actor in actorList" :key="actor.personId">
        <div class="box" >
            <img :src="actor.imgurl" width="180" height="180" alt="" @click="$router.push({name: 'ActorDetail', params: {personId: actor.personId}})" />
            <h3 style="color: white;"> {{ actor.actorName }}  </h3>
            <p style="color: grey;"> {{ actor.role }} 역</p>
        </div>
    </div>
    
    
</div>
<div id="revcontainer" >
<reviewWrite />
<reviewList />
</div>
<div id="copyright" class="container">
    <p>&copy; Untitled. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>

<vue-word-cloud
  style="
    height: 480px;
    width: 640px;
  "
  :words="[['romance', 19], ['horror', 3], ['fantasy', 7], ['adventure', 3]]"
  :color="([, weight]) => weight > 10 ? 'DeepPink' : weight > 5 ? 'RoyalBlue' : 'Indigo'"
  font-family="Roboto"
/>
  </div>
</template>
<script src="https://unpkg.com/vue"></script>
<script src="https://unpkg.com/vuewordcloud"></script>
<script type="text/javascript" src="https://unpkg.com/movie-trailer"></script>
<script>
import TabItem from './TabItem.vue'
import VueWordCloud from 'vuewordcloud';
import reviewWrite from '@/components/review/ReviewWrite.vue';
import reviewList from '@/components/review/ReviewList.vue';
import axios from 'axios'
import HTTP from "@/util/http-common.js"

export default {
    
    
    components: {
    [VueWordCloud.name]: VueWordCloud,
    reviewWrite,
    reviewList,
    TabItem,
  },
  data() {
      return {
        title: '기생충',
        trailerURL1 : "",
        trailerURL2 : "",
		currentId: 1,
		userno: "",
        list: [
            { id: 1, label: '영화정보', content: '콘텐츠1' },
            { id: 2, label: '줄거리', content: '콘텐츠2' },
            { id: 3, label: '포토', content: '콘텐츠3' }
        ],
        actorList: [
            { actorName: '송강호', role: '기태', personId: '561', imgurl: '//img1.daumcdn.net/thumb/C74x107/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fmovie%2F1ca37a29af263c233bcba1f467327895185f0b31'},
            { actorName: '이선균', role: '동익', personId: '14959', imgurl: '//img1.daumcdn.net/thumb/C74x107/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2Fb066cbec9fe6d2268b3b89d129a731695a72c65e'},
            { actorName: '조여정', role: '연교', personId: '96410', imgurl: '//img1.daumcdn.net/thumb/C74x107/?fname=http%3A%2F%2Fcfile79.uf.daum.net%2Fimage%2F26599548532B92372E2A19'},
            { actorName: '최우식', role: '기우', personId: '259139', imgurl: '//img1.daumcdn.net/thumb/C74x107/?fname=http%3A%2F%2Ft1.daumcdn.net%2Fmovie%2F64c561c864938fda5f2b605b25f562e5d9996a3a'},
		],
		movieDetail: {
			movieName: "",
			movieId: "",
			rate : "",
			genre: "",
			movieOpeningDate: "",
			runningTime : "",
			overview: "",
			imgurl: "",
			nation: "",
		},
        }
    },
    computed: {
    current() {
      return this.list.find(el => el.id === this.currentId) || {}
    }
  },
    created() {
		if(this.$store.getters.getUserData == null){
            this.userno = 0;
        } else{
            this.userno = this.$store.getters.getUserData.userinfo.userNo
            console.log(this.userno)
        }
		axios.get(`${HTTP.BASE_URL}/mcr/daummovie` ,
			{
				params: { movieId: '42' }
			}
		)
		.then(res => {
			if (res.data === 'fail') {
				this.$router.push({
					name: 'Error',
					query: {
						status: 404
					}
				})
			}
			this.movieDetail = {
			movieName: res.data.object.movieName,
			rate : res.data.object.rate,
			genre: res.data.object.genre,
			movieOpeningDate: res.data.object.movieOpeningDate,
			runningTime : res.data.object.runningTime,
			overview: res.data.object.overview,
			imgurl: res.data.object.imgUrl,
			nation: res.data.object.nation,
			}
			const movieTrailer = require( 'movie-trailer' )
			this.trailerURL1 = 'https://www.youtube.com/embed/'
			movieTrailer( res.data.object.movieName , ( error, response) => {
				this.trailerURL1 = this.trailerURL1 + response.substr(32)
			})
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

        console.log(this.movieDetail.movieName)
        
    
	},
}
</script>

<style scoped>
div {
    z-index: 1
}
.contents {
  position: relative;
  overflow: hidden;

  width: 530px;
  border: 2px solid #000;
}
.item {
  box-sizing: border-box;
  margin-bottom: 0px;
  overflow:hidden;
    height:auto;
  transition: all 0.8s ease;
  background-color: grey;
}
/* 트랜지션 전용 스타일 */
.v-leave-active {
  position: absolute;
}
.v-enter {
  transform: translateX(-100%);
}
.v-leave-to {
  transform: translateX(100%);
}



.countsort{

    position : relative;

    width : 100%;
    height : 0;
    padding-bottom: 50px;
    margin-bottom: 50px;
    padding-bottom : 26.25%;

}


.video{

    position : absolute;

    top : 0;

    left : 0;

    width : 100%;

    height : 100%;

}




.inline-div{
    display: inline-block;
  
  
}
    html, body
{
    height: 100%;
}




h1, h2, h3
{
    margin: 0;
    padding: 0;
}

p, ol, ul
{
    margin-top: 0;
}

ol, ul
{
    padding: 0;
    list-style: none;
}

p
{
    line-height: 180%;
}

strong
{
}

a
{
    color: #96063b;
}

a:hover
{
    text-decoration: none;
}

.container
{
    overflow: hidden;
    margin: 0em auto;
    width: 1200px;
}

/*********************************************************************************/
/* Image Style                                                                   */
/*********************************************************************************/

.image
{
    display: inline-block;
}

.image img
{
    display: block;
    width: 100%;
}

.image-full
{
    display: block;
    width: 100%;
    margin: 0 0 2em 0;
}

.image-left
{
    float: left;
    margin: 0 2em 2em 0;
}

.image-centered
{
    display: block;
    margin: 0 0 2em 0;
}

.image-centered img
{
    margin: 0 auto;
    width: auto;
}

/*********************************************************************************/
/* List Styles                                                                   */
/*********************************************************************************/

ul.style1
{
}


/*********************************************************************************/
/* Social Icon Styles                                                            */
/*********************************************************************************/

ul.contact
{
    margin: 0;
    padding: 2em 0em 0em 0em;
    list-style: none;
}

ul.contact li
{
    display: inline-block;
    padding: 0em 0.30em;
    font-size: 1em;
}

ul.contact li span
{
    display: none;
    margin: 0;
    padding: 0;
}

ul.contact li a
{
    color: #FFF;
}

ul.contact li a:before
{
    display: inline-block;
    background: #3f3f3f;
    width: 40px;
    height: 40px;
    line-height: 40px;
    border-radius: 20px;
    text-align: center;
    color: #FFFFFF;
}

ul.contact li a.icon-twitter:before { background: #2DAAE4; }
ul.contact li a.icon-facebook:before { background: #39599F; }
ul.contact li a.icon-dribbble:before { background: #C4376B;    }
ul.contact li a.icon-tumblr:before { background: #31516A; }
ul.contact li a.icon-rss:before { background: #F2600B; }

/*********************************************************************************/
/* Button Style                                                                  */
/*********************************************************************************/

.button
{
    display: inline-block;
    padding: 1em 2em 1em 2em;
    background: #96063b;
    letter-spacing: 0.20em;
    text-decoration: none;
    text-transform: uppercase;
    font-weight: 400;
    font-size: 0.90em;
    color: #FFF;
}

.button:before
{
    display: inline-block;
    background: #FFC31F;
    margin-right: 1em;
    width: 40px;
    height: 40px;
    line-height: 40px;
    border-radius: 20px;
    text-align: center;
    color: #272925;
}
    
/*********************************************************************************/
/* 4-column                                                                      */
/*********************************************************************************/

.box1,
.box2,
.box3,
.box4
{
    width: 200px;
}

.box1,
.box2,
.box3
{
    float: left;
    margin-right: 20px;
}

.box4
{
    float: right;
}

.box
{
	margin-left: 100px;
}

/*********************************************************************************/
/* 3-column                                                                      */
/*********************************************************************************/

.boxA,
.boxB,
.boxC
{
    width: 320px;
}

.boxA,
.boxB
{
    float: left;
    margin-right: 20px;
}

.boxC
{
    float: right;
}


/*********************************************************************************/
/* 2-column                                                                      */
/*********************************************************************************/

.tbox1,
.tbox2
{
    width: 575px;
}

.tbox1
{
    float: left;
}

.tbox2
{
    float: right;
}

/*********************************************************************************/
/* Header                                                                        */
/*********************************************************************************/

#header-wrapper
{
    overflow: hidden;
    height: 40em;
}

#header
{
    position: relative;
    margin-top: 3em;
    padding: 3em 0em;
    background: #96063b;
}

/*********************************************************************************/
/* Logo                                                                          */
/*********************************************************************************/

#logo
{
    position: absolute;
    top: 1.5em;
    left: 2em;
}

#logo h1
{
}

#logo a
{
    text-decoration: none;
    color: #FFF;
}

/*********************************************************************************/
/* Menu                                                                          */
/*********************************************************************************/

#menu
{
    position: absolute;
    top: 2.4em;
    right: 2em;
}

#menu ul
{
    display: inline-block;
}

#menu li
{
    display: block;
    float: left;
    text-align: center;
}

#menu li a, #menu li span
{
    text-decoration: none;
    padding: 1em 1.5em;
    color: #FFF;
}

#menu li:hover a, #menu li.active a, #menu li.active span
{
    text-decoration: underline;
}

#menu .current_page_item a
{
}

#menu .icon
{
}

/*********************************************************************************/
/* Welcome                                                                       */
/*********************************************************************************/

#welcome
{
    overflow: hidden;
    width: 1000px;
    margin-top: 3em;
    padding: 4em 100px 3em 100px;
    background: #FFF;
}


#welcome h2
{
    padding-bottom: 1em;
    font-size: 1.8em;
    font-weight: 400;
}

/*********************************************************************************/
/* Page                                                                          */
/*********************************************************************************/

#page
{
    overflow: hidden;
    width: 1000px;
    padding: 7em 100px 3em 100px;
    background: #FFF;
}

#page .boxA,
#page .boxB,
#page .boxC
{
}

#page .boxA
{
}

#page .box
{
    margin-bottom: 4em;
}

#page img
{
    margin-bottom: 2em;
}

#page h2
{
    padding-bottom: 1.5em;
    font-size: 2.2em;
    font-weight: 600;
}

#page h3
{
    padding-bottom: 1em;
    font-size: 1.8em;
    font-weight: 400;
}

#page .link
{
    display: block;
    padding-top: 1em;
    text-transform: uppercase;
    text-decoration: none;
    font-weight: 700;
    color: rgba(0,0,0,0.8);
}

/*********************************************************************************/
/* Content                                                                       */
/*********************************************************************************/

#content
{
}

/*********************************************************************************/
/* Sidebar                                                                       */
/*********************************************************************************/

#sidebar
{
}

/*********************************************************************************/
/* Footer                                                                        */
/*********************************************************************************/

#footer
{
    overflow: hidden;
    padding: 5em 0em 3em 0em;
    border-top: 1px solid rgba(0,0,0,0.08);
}


/*********************************************************************************/
/* Copyright                                                                     */
/*********************************************************************************/

#copyright
{
    overflow: hidden;
    padding: 5em 0em;
    border-top: 1px solid rgba(0,0,0,0.08);
}

#copyright p
{
    letter-spacing: 0.20em;
    text-align: center;
    text-transform: uppercase;
    font-size: 0.80em;
    color: rgba(255,255,255,0.3);
}

#copyright a
{
    text-decoration: none;
    color: rgba(255,255,255,0.6);
}




.trail{
    width: 90%;
    height: 400px;
    background-color: black;

}
#revcontainer{
    background-color: black;
    margin: 15px;
}
</style>