<template>
    <div id="revcontainer">
        <div id="revtitle" >리뷰</div>
        <hr id="hr1">
        <div id="content" >
        <div id="line1"  v-if="onlogin">
        <label id="lab" for="" >내평점</label> <button id="togglebtn" @click="changetoggle">{{ name }}</button>
        </div>
        <div id="incontainer" v-if="toggle==true" >
        <StarRating v-model="rating" v-bind:max-rating="10" :show-rating="temp"/>
        <textarea id="revcontent"></textarea>
        <button id="revbutton" >작성</button>
        </div>
        </div>
    </div>
</template>

<script>
// import HTTP from "@/util/http-common.js"
// import axios from 'axios'
import StarRating from 'vue-star-rating';
export default {
    name: 'ReviewWrite',
    components: {
        StarRating,
    },
    data(){
        return {
            revwrite: null,
            temp:false,
            rating:"",
            toggle: false,
            name: "펼치기",
            onlogin:false,
        }
    },
    methods:{
        changetoggle() {
            this.toggle = !this.toggle;
            if(this.name == "펼치기"){
                this.name = "접기";
            } else {
                this.name = "펼치기";
            }
        }
    },
    created() {
        if(this.$store.getters.getUserData == null){
            this.onlogin = false;
        } else{
            this.onlogin = true;
        }
    }
 }
</script>

<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
    #content{
        padding-bottom: 2.5vh;
    }
    #line1{
        height : 4.5vh;
    }
    #lab{
        float: left;
        margin-left:3vw;
    }
    #togglebtn{
        color: black;
        background-color: #fff9f7;
        float: right;
        margin-right : 2vw;
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        border: 0;
        outline: 0;
        
    }
    #togglebtn:hover{
        color: red;
    }
    #incontainer{
        border: 2px solid skyblue;
        margin : 2vh 2vw 2vh 2vw; 
        padding : 2vh 2vw 2vh 2vw;
    }
    #revcontainer{
        background-color : #fff9f7;
        font-family: 'Hanna', sans-serif;
    }
    #revcontent {
            width: 90%;
            height: 16vh;
            resize: none;
            margin-top : 4vh;
            border : 2px solid skyblue;
            margin-left: 2vw;
            margin-right: 2vw;
            margin-bottom: 2vh;
    }
    #revwrite {
        margin-left: 2.5vw;
        margin-right: 2.5vw; 
    }
    #revtitle {
        font-size: 5vw;
        margin-left: 2vw;
        padding-top: 1vh;
        color: black;
    }
    #revbutton{
        margin-top : 6vh;
        margin-left: 2vw;
        font-size: 1.5vw ;
        position: absolute;
            
    }

    #lab{
        color: black;
    }

    #hr1{
        background-color: skyblue;
    }
</style>
