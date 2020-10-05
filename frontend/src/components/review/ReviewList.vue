<template>
    <div id ="revListcontainer">
        
        <vue-confirm-dialog></vue-confirm-dialog>
        <div id="app" style="margin-top:7vh;">
            <div style="width:100%;">
                <div style="width:48%; float:left;">
                    <h1 style="float:left; padding:2vw; font-size:3vw;">긍정적 리뷰</h1>
                </div>
                <div style="width:48%; float:right;">
                    <h1 style="float:left; padding:2vw; font-size:3vw;">부정적 리뷰</h1>

                </div>
            </div>
            <div style="clear:both;"></div>
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
                <div class="firstLine">
                <div class ="revName">
                    {{ review.writer }} 
                </div>    
                    <i id="staricon" class="fas fa-star" style="color:yellow; float:left; "></i>&nbsp;<p style="font-size:1.5vw; float:left;"> {{ review.rate }} / 10 </p>
                
                </div>
                <br>
                <div class="revContent">
                    {{ review.content }}
                </div>
                <br>
                <div class="revDate">
                    {{ review.regtime }} <button v-if="userno == review.userNo" @click="delrev" style="color: black; background-color: #fff9f7; -webkit-transition-duration: 0.4s; margin-left: 1vw; transition-duration: 0.4s; border: 0; outline: 0;">삭제하기</button>
                </div>
            </div>
            <div class="btn-cover">
      <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum + 1 }} / {{ pageCount }} 페이지</span>
      <button  :disabled="pageNum >= pageCount - 1" @click="nextPage" class="page-btn">
        다음
      </button>
    </div>


        </div>
        
        <div id ="negativeRev">
            <div class = "revitem" v-for="review in paginatedData1" :key="review.writer">
                <div class="firstLine">
                <div class ="revName">
                    {{ review.writer }}
                </div> <i id="staricon1" class="fas fa-star" style="color:yellow; float:left;"></i>&nbsp; <p style="font-size:1.5vw; float:left;">{{ review.rate }} / 10</p>
                </div>
                <br>
                <div class ="revContent">
                    {{ review.content }}
                </div>
                <br>
                <div class="revDate">
                    {{ review.regtime }}  <button v-if="userno==review.userNo" @click="delrev" style="color: black; background-color: #fff9f7; -webkit-transition-duration: 0.4s; margin-left: 1vw; transition-duration: 0.4s; border: 0; outline: 0;">삭제하기</button>
                </div>
            </div>
            <div class="btn-cover">
      <button  :disabled="pageNum1 === 0" @click="prevPage1" class="page-btn">
        이전
      </button>
      <span class="page-count">{{ pageNum1 + 1 }} / {{ pageCount1 }} 페이지</span>
      <button  :disabled="pageNum1 >= pageCount1 - 1" @click="nextPage1" class="page-btn">
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
import HTTP from "@/util/http-common.js"
import wordcloud from 'vue-wordcloud'

export default {
    name: 'app',
    components: {
        wordcloud,
    },
    props: ['movieNo']
    ,
    data() {
        return {
            booleanValue: false,
            paddingNum: 5,
            F: false,
            userno: 0,
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
            this.userno = this.$store.getters.getUserData.userinfo.userNo
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
    delrev() {
        this.handleClick()
    },
    handleClick(){
        this.$confirm({
            message: `리뷰를 삭제하시겠습니까?`,
            button: {
                no: '아니요',
                yes: '삭제'
            },
            callback: confirm => {
             
                if(confirm){
                    axios.delete(`${HTTP.BASE_URL}/mcr/daumreview/`,
                    {
                        params: {movieId: this.movieId , userNo: this.userno}
                    })
                
                    alert("삭제 되었습니다.")
                    setTimeout(() => {
                        this.$router.go()
                        this.$router.push({name: "FeedDetail", params: {movieId: this.movieId }})
                    }, 1000);
                }
            }
        })
    }

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

    

    #checkdiv{
        clear: both;
        border-bottom: 1px solid white;
        padding: 10px;
    }

    .btn-cover {
  margin-top: 1.5rem;
  text-align: center;
  font-size: 2vw;
  
}
.btn-cover .page-btn {
  width: 8vw;
  height: 5vh;
  font-size: 1.5vw;
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
    line-height: 1.2;
    height: 3.6em;
    width:95%;
    display: inline-block;
    overflow-y:auto;
    padding-right:1vw;
    font-size: 1.2vw;
    word-break:break-all;
}
.wordDiv{
    width:45%;
    height: 50vh;
}

.revContent::-webkit-scrollbar {
    width: 10px;
  }
  .revContent::-webkit-scrollbar-thumb {
    background-color: #2f3542;
    border-radius: 10px;
    background-clip: padding-box;
    border: 2px solid transparent;
  }
  .revContent::-webkit-scrollbar-track {
    background-color: grey;
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
  }

#staricon {
    margin-right:10px; margin-left:10px; margin-top:5px;
}

#staricon1{
    margin-right:10px; margin-left:10px; margin-top:5px;
}

@media screen and (max-width: 405px) {
    .btn-cover .page-btn{
        width: 9vw;
        height: 3vh;
        font-size: 1vw;
    }



}

@media screen and (max-width: 1100px) {
    #staricon {
        margin-top:3px !important;
    }

    #staricon1 {
        margin-top:3px !important;
    }
}

@media screen and (max-width: 950px) {
    #staricon {
        margin-top:2px !important;
    }

    #staricon1 {
        margin-top:2px !important;
    }
}

@media screen and (max-width: 880px) {
    #staricon {
        margin-top:1px !important;
    }

    #staricon1 {
        margin-top:1px !important;
    }
}

@media screen and (max-width: 750px) {
    #staricon {
        margin-top:0px !important;
    }

    #staricon1 {
        margin-top:0px !important;
    }
}

</style>