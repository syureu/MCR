<template>
    <div id ="revListcontainer">
        <div id="app">
        <div class ="wordDiv" style="float:left;">
        <wordcloud
      :data="defaultWords"
      nameKey="content"
      valueKey="rate"
      spiral="rectangular"
      :rotate="{from: 0, to:0, numOfOrientation: 0}"
      fontScale="log"
      :showTooltip="false"
      :fontSize="[15, 15]"
      :wordPadding="paddingNum"
      >
      </wordcloud>
      </div>
        <div class ="wordDiv" style="float: right;">
        <wordcloud
      :data="defaultWords1"
      nameKey="content"
      valueKey="rate"
      :showTooltip="false"
      :fontSize="[15, 15]"
      spiral="rectangular"
      fontScale="log"
      :rotate="{from: 0, to:0, numOfOrientation: 0}"
      :wordPadding="paddingNum"
      >
      </wordcloud>
      </div>
      </div>
      <div style="clear:both;"></div>
        <div id ="positiveRev"> 
            <div class = "revitem" v-for="review in paginatedData" :key="review.writer" >
                <div>
                <div class ="revName">
                    {{ review.writer }} 
                </div>    
                    <StarRating class="starR" v-model="review.rate" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.rate }} / 10
                
                </div>
                <br>
                <div class="revContent">
                    {{ review.content }}
                </div>
                <br>
                <div class="revDate">
                    {{ review.regtime }}
                </div>
            </div>
            <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>


        </div>
        
        <div id ="negativeRev">
            <div class = "revitem" v-for="review in paginatedData1" :key="review.writer">
                <div>
                <div class ="revName">
                    {{ review.writer }}
                </div> <StarRating class="starR" v-model="review.rate" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.rate }} / 10
                </div>
                <br>
                <div class ="revContent">
                    {{ review.content }}
                </div>
                <br>
                <div class="revDate">
                    {{ review.regtime }}
                </div>
            </div>
            <div class="btn-cover">
      <button :disabled="pageNum1 === 0" @click="prevPage1" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum1 + 1 }} / {{ pageCount1 }} 페이지</span>
      <button :disabled="pageNum1 >= pageCount1 - 1" @click="nextPage1" class="page-btn">
        다음
      </button>
    </div>
        </div>
        <div id="checkdiv">

        </div>
    </div>
</template>


<script>

import axios from 'axios'
import StarRating from 'vue-star-rating';
import HTTP from "@/util/http-common.js"
import wordcloud from 'vue-wordcloud'

export default {
    name: 'app',
    components: {
        StarRating,
        wordcloud,
    },
    props: ['movieNo']
    ,
    data() {
        return {
            paddingNum: 5,
            F: false,
            starsize: 20,
            userno: "",
            movieId: this.movieNo,
            pageNum: 0,
            pageSize: 5,
            pageNum1: 0,
            pageSize1: 5,
            positiveList: [],
            defaultWords: [],
            defaultWords1: [],
            negativeList: [],
            myColors: ['#1f77b4', '#CF11EE', '#EEEE11', '#c9e0ef', '#EE3811'],
        }
    },
    computed: {
        

    pageCount () {
      let listLeng = this.positiveList.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    pageCount1 () {
      let listLeng = this.negativeList.length,
          listSize = this.pageSize1,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      
      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page;
    },
    paginatedData () {
      const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
      return this.positiveList.slice(start, end);
    },
    paginatedData1 () {
      const start = this.pageNum1 * this.pageSize1,
            end = start + this.pageSize1;
      return this.negativeList.slice(start, end);
    },
    },
    created() {

        if(this.$store.getters.getUserData == null) {
            this.userno = 0;
        } else {
            this.userno = this.$store.getters.getUserData.userinfo.userno
        }
        axios.get(`${HTTP.BASE_URL}/mcr/daumreview/pos`,
            {
                params: {movieId: this.movieId }
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
            this.positiveList = res.data.object

            var num = 1
            if(this.positiveList.length > 1000){
                num = 100;
            } else if( this.positiveList.length > 500) {
                num = 100;
            } else if( this.positiveList.length > 100) {
                num = 50;
            } else {
                num = this.positiveList.length;
            }

            for (var i = 0; i < num; i++){
                this.defaultWords.push(
                    {"rate": this.positiveList[i]["rate"],
                    "content": this.positiveList[i]["content"],
                })
                
            }
        })

        axios.get(`${HTTP.BASE_URL}/mcr/daumreview/neg`,
            {
                params: {movieId: this.movieId }
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
            this.negativeList = res.data.object
            
            var num = 1
            if(this.negativeList.length > 1000){
                num = 100;
            } else if( this.negativeList.length > 500) {
                num = 100;
            } else if( this.negativeList.length > 100) {
                num = 50;
            } else {
                num = this.negativeList.length;
            }

            for (var i = 0;  i < num; i++){
                this.defaultWords1.push({
                    "rate": this.negativeList[i]["rate"],
                    "content": this.negativeList[i]["content"],
                })
                
            }
            for(var j = 0; j < this.defaultWords1.length; j++){
                this.defaultWords1[j].rate = 10 - this.defaultWords1[j].rate
            }
        })
        

    },

    methods: {    
    nextPage () {
      this.pageNum += 1;
    },
    prevPage () {
      this.pageNum -= 1;
    },
    nextPage1 () {
      this.pageNum1 += 1;
    },
    prevPage1 () {
      this.pageNum1 -= 1;
    },
    }
}
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
    #revListcontainer{
        background-color:#fff9f7;
        font-family: 'Hanna', sans-serif;
    }

    #positiveRev{
        float: left;
        width : 48%;
    }

    #negativeRev{
        float: right;
        width: 48%;
    }

    .revName{
        font-size: 1.5vw;
        float: left;
    }

    .revitem{
        border-bottom: 1px solid skyblue;
        margin-top: 2vh;
        padding: 2vw;
    }

    .revDate{
        font-size : 1.5vw;
    }   

    .starR{
        float:left;
        margin-left: 1.5vw;
        margin-right: 1.5vw;
    }

    

    #checkdiv{
        clear: both;
        border-bottom: 1px solid white;
        padding: 10px;
    }

    .btn-cover {
  margin-top: 1.5rem;
  text-align: center;
  
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
  border-radius: 8px;
  color: skyblue;
  background-color: rgba(0,0,0,0);
  border: 1px solid skyblue;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.revContent{
    white-space: normal;
    line-height: 1.2;
    height: 3.6em;
    width:95%;
    display: inline-block;
    overflow:hidden;
    text-overflow:ellipsis;
    font-size: 1.2vw;
}
.wordDiv{
    width:45%;
    height: 50vh;
}
</style>