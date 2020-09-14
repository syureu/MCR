<template>
    <div id ="revListcontainer">
    
        <div id ="positiveRev"> 
            <div class = "revitem" v-for="review in rowsInPage" :key="review.name" >
                <div>
                <div class ="revName">
                    {{ review.name }} 
                </div>    
                    <StarRating class="starR" v-model="review.score" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.score}} / 10
                
                </div>
                <br>
                <div class="revContent">
                    {{ review.content }}
                </div>
            </div>
            <div class="w3-bar" id="paginator">
            <a class="w3-button" @click="previous">&laquo;</a>
            <a v-for="pNum in pageRange" :key="pNum" v-text="pNum" class="w3-button" @click="getPage"/>
            <a class="w3-button" @click="next">&raquo;</a>
            </div>
        </div>
        
        <div id ="negativeRev">
            <div class = "revitem" v-for="review in rowsInPage1" :key="review.name">
                <div>
                <div class ="revName">
                    {{ review.name }}
                </div> <StarRating class="starR" v-model="review.score" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.score }} / 10
                </div>
                <br>
                <div>
                    {{ review.content }}
                </div>
            </div>
            <div class="w3-bar" id="paginator1">
            <a class="w3-button" @click="previous1">&laquo;</a>
            <a v-for="pNum1 in pageRange1" :key="pNum1" v-text="pNum1" class="w3-button" @click="getPage1"/>
            <a class="w3-button" @click="next1">&raquo;</a>
            </div>
        </div>
        <div id="checkdiv">

        </div>
    </div>
</template>

<script>


import StarRating from 'vue-star-rating';
export default {
    components: {
        StarRating
    },
    data() {
        return {
            F: false,
            starsize: 20,
            rowPerPage: 5,
            rowPerPage1: 5,
            pageNow1: 1,
            range1: 5,
            dummy1: null,
            beforeIndex1: 1,
            pageNow : 1,
            range: 5,
            dummy: null,
            beforeIndex: 1,
            positiveList: [
                {name: '박지훈' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '아이유' , content: '정말 재미있는 영화 입니다.', score: 9},
                {name: '수지' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '박보검' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '공유' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '김태희' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '송혜교' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '아무나' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '김철수' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '조재민' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '박현수' , content: '감동적인 영화 입니다. 시간 가는 줄 모르고 시청했습니다.', score: 10},
                {name: '허진기' , content: '굿굿굿 베리굿 입니다.', score: 8}
            ],

            negativeList: [
                {name: '박지훈' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈1' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈2' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈3' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈4' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈5' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈6' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈7' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈8' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈9' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈10' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈11' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈12' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈13' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈14' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈15' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박지훈16' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박현수' , content: '감동도 없고 시간을 버리는 영화입니다.', score: 4},
                {name: '허진기' , content: '밷밷밷 베리배드 입니다.' , score: 2}
            ],
        }
    },
    computed: {
        pageNumber() {
            if (this.positiveList.length % this.rowPerPage > 0) {
                return Math.floor(this.positiveList.length / this.rowPerPage) + 1
            } else {
                return Math.floor(this.positiveList.length / this.rowPerPage)
            }
        },
        pageRange() {
            return [...Array(this.pageNumber).keys()].map(key => key+1)
        },
        rowsInPage() {
            return this.positiveList.slice((this.pageNow - 1) * this.rowPerPage, this.pageNow * this.rowPerPage)
        },
        rangeStart() {
            return Math.floor(this.pageNow / this.range)
        },
        pageNumber1() {
            if (this.negativeList.length % this.rowPerPage1 > 0) {
                return Math.floor(this.negativeList.length / this.rowPerPage1) + 1
            } else {
                return Math.floor(this.negativeList.length / this.rowPerPage1)
            }
        },
        pageRange1() {
            return [...Array(this.pageNumber1).keys()].map(key => key+1)
        },
        rowsInPage1() {
            return this.negativeList.slice((this.pageNow1 - 1) * this.rowPerPage1, this.pageNow1 * this.rowPerPage1)
        },
        rangeStart1() {
            return Math.floor(this.pageNow1 / this.range1)
        },
    },
    methods: {
        previous() {
            if (this.pageNow - this.range >= 1) {
                this.pageNow -= this.range
            } else {
                this.pageNow = 1
            }
            this.checkIndex(this.pageNow)
        },
        next() {
            if (this.pageNow + this.range > this.pageNumber) {
                this.pageNow = this.pageNumber
            } else {
                this.pageNow += this.range
            }
            this.checkIndex(this.pageNow)
        },
        getPage(e) {
            this.checkIndex(e.target.innerText)
            this.pageNow = e.target.innerText
        },
        checkIndex(number) {
            const indexList = document.querySelectorAll('#paginator a')
            indexList[this.beforeIndex].setAttribute('style', '')
            indexList[number].setAttribute('style', 'background-color: rgb(128,128,116); color: white;')
            this.beforeIndex = number

            const pageStart = Math.floor(this.pageNow / this.range) + 1
            if (pageStart + this.range - 1 > this.pageNumber) {
                this.dummy = pageStart
            } else {
                this.pageRange
            }
        },

        previous1() {
            if (this.pageNow1 - this.range1 >= 1) {
                this.pageNow1 -= this.range1
            } else {
                this.pageNow1= 1
            }
            this.checkIndex1(this.pageNow1)
        },
        next1() {
            if (this.pageNow1 + this.range1 > this.pageNumber1) {
                this.pageNow1 = this.pageNumber1
            } else {
                this.pageNow1 += this.range1
            }
            this.checkIndex1(this.pageNow1)
        },
        getPage1(e) {
            this.checkIndex1(e.target.innerText)
            this.pageNow1 = e.target.innerText
        },
        checkIndex1(number1) {
            const indexList1 = document.querySelectorAll('#paginator1 a')
            indexList1[this.beforeIndex1].setAttribute('style', '')
            indexList1[number1].setAttribute('style', 'background-color: rgb(128,128,116); color: white;')
            this.beforeIndex1 = number1

            const pageStart1 = Math.floor(this.pageNow1 / this.range1) + 1
            if (pageStart1 + this.range1 - 1 > this.pageNumber1) {
                this.dummy1 = pageStart1
            } else {
                this.pageRange1
            }
        },
    }
}
</script>

<style scoped>
    #revListcontainer{
        background-color: black;
        color: white;
    }

    #positiveRev{
        float: left;
        width : 48%;
        background-color: black;
    }

    #negativeRev{
        float: right;
        width: 48%;

        background-color: black;
    }

    .revName{
        font-size: 20px;
        float: left;
    }

    .revitem{
        border-bottom: 1px solid white;
        margin-top: 10px;
        padding: 10px;
    }
    
    .starR{
        float:left;
        margin-left: 10px;
        margin-right: 10px;
    }

    

    #checkdiv{
        clear: both;
        border-bottom: 1px solid white;
        padding: 10px;
    }
    .page-item{
        margin-right: 5px;
    }

    .w3-bar{
        margin-top: 50px;
        text-align: center;
    }

    .w3-bar p {
        display:inline-block;
    }
    .w3-bar a:hover{
        background-color: white;
        border-bottom: 1px solid white;
    }
    .w3-button{
        margin-right: 10px;
    }
</style>