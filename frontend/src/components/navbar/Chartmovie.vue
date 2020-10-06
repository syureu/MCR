<template>
  <!-- <div id="app">
    <ul class="mt-5 pt-5">
      <li>
        <div id="content">
          <dl id="rank_list">
            <dt>실시간 급상승 검색어</dt>
                <dd>
                    <ol style="color: white;" v-for="item in items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice" v-bind:key="item.movieNm">
                        <li><a href="#">{{ item.movieNm._text }}</a></li>
    
                       
                    </ol>
                </dd>
            </dl>
          </div>
      </li>
    </ul>
  </div> -->
  <div style="display:inline-block">
    <span class="cursor_test" @click="changeModal()" >▼ Box Office</span>
     <div class="rolling_box">
       <MovieChartModal v-if="Modal" @close="changeLogin" @change="changeModal" :rolling="this.rollingData"/>
      <ul id ="rolling_box" style="padding-left: 0">
        
        <li class="card_sliding" id ="first"><p></p></li>
        <li class="" id ="second"><p></p></li>
        <li class="" id ="third"><p></p></li>
      </ul>
     
    </div>




    </div>
      

</template>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>

<script>
import MovieChartModal from '@/components/modal/MovieChartModal.vue'
var convert = require('xml-js')
export default {
  name: 'App',
  data () {
    return {
      items: [],
      rollingData: ['준비중입니다.',],
      Modal:false
    }
  },components:{
    MovieChartModal
       },
  created () {
    let today = new Date(); 

    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1;  // 월
    let apidate = year+''+month+'01'
    this.$http.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=424e2b88162e9154ee09a8a4d8e5bf3a&targetDt='+apidate)
      .then((response) => {
        var xml = response.data
      
        var json = convert.xml2json(xml, { compact: true })
        this.items = JSON.parse(json)
      
      })
      setTimeout(() => {
       
        this.rollingData = [
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[0].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[1].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[2].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[3].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[4].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[5].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[6].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[7].movieNm._text ,                     
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[8].movieNm._text,
                            this.items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice[9].movieNm._text
                            
                          ]    
                        
      }, 3500);
  
      
  },
  methods: {
    gotomovie(){
    },
     changeLogin () {
          this.Modal = !this.Modal
      },
      changeModal(){
        this.Modal = !this.Modal
      }
  },
  mounted () {

        
      
    
        let timer = 2500 // 롤링되는 주기 입니다 (1000 => 1초)

        let first = document.getElementById('first'),
            second = document.getElementById('second'),
            third = document.getElementById('third')
        let move = 2
        let dataCnt = 1
        let listCnt = 1

        //위 선언은 따로 완전히 수정하지 않는 한 조정할 필요는 없습니다.

        first.children[0].innerHTML = this.rollingData[0]

        setInterval(() => {
            if(move == 2){
                first.classList.remove('card_sliding')
                first.classList.add('card_sliding_after')

                second.classList.remove('card_sliding_after')
                second.classList.add('card_sliding')

                third.classList.remove('card_sliding_after')
                third.classList.remove('card_sliding')

                move = 0
            } else if (move == 1){
                first.classList.remove('card_sliding_after')
                first.classList.add('card_sliding')

                second.classList.remove('card_sliding_after')
                second.classList.remove('card_sliding')

                third.classList.remove('card_sliding')
                third.classList.add('card_sliding_after')

                move = 2
            } else if (move == 0) {
                first.classList.remove('card_sliding_after')
                first.classList.remove('card_sliding')

                second.classList.remove('card_sliding')
                second.classList.add('card_sliding_after')

                third.classList.remove('card_sliding_after')
                third.classList.add('card_sliding')

                move = 1
            }
            
            if(dataCnt < (this.rollingData.length - 1)) {
                document.getElementById('rolling_box').children[listCnt].children[0].innerHTML = this.rollingData[dataCnt]
                    dataCnt++
            } else if(dataCnt == this.rollingData.length - 1) {
                document.getElementById('rolling_box').children[listCnt].children[0].innerHTML = this.rollingData[dataCnt]
                dataCnt = 0
            }

            if(listCnt < 2) {
                listCnt++
            } else if (listCnt == 2) {
                listCnt = 0
            }


        }, timer);
  }
}


</script>
<style scoped>
.cursor_test {cursor: pointer;}
span{
  color: white;
}
span:hover {color: red; text-decoration: underline;}
ul{
   list-style:none;

   }

.rolling_box{
            width: 200px;
            height: 5vh;
            text-align: center;
          
        }

        .rolling_box ul {
            
            height: 100%;
            overflow: hidden;
            position: relative;
        }

        .rolling_box ul li {
           
            height: 100%;
            transition: .5s;
            position:absolute;
            transition: top .75s;
            overflow: hidden;
            top: 100%;
            z-index: 1;
            background-color: black;
            overflow:hidden;
        }

        .card_sliding{
            top: 0 !important;
            z-index: 100 !important;
        } 

        .card_sliding_after{
            top: -100% !important;
            z-index: 10 !important;
        }

        .rolling_box ul li p {
          color: aliceblue;
            font-size: 100%;
            line-height: 5vh;
            font-weight: bold;
        }

        .before_slide {
            transform: translateY(100%);
        }

        .after_slide {
            transform: translateY(0);
        }

</style>