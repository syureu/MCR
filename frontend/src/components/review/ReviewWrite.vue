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
        if(this.$store.getters.geutUserData == null){
            this.onlogin = false;
        } else{
            this.onlogin = true;
        }
    }
 }
</script>

<style scoped>
    #content{
        padding-bottom: 10px;
    }
    #line1{
        height : 35px;
    }
    #lab{
        float: left;
    }
    #togglebtn{
        color: white;
        background-color: black;
        float: right;
        margin-right : 10px;
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        border: 0;
        outline: 0;
        
    }
    #togglebtn:hover{
        color: red;
    }
    #incontainer{
        border: 2px solid white;
        margin : 10px;
        padding : 10px;
    }
    #revcontainer{
        background-color : black;
    }
    #revcontent {
            width: 90%;
            height: 100px;
            resize: none;
            margin-top : 20px;
            border : 2px solid white;
            margin-left: 10px;
            margin-right: 10px;
            margin-bottom: 10px;
    }
    #revwrite {
        margin-left: 30px;
        margin-right: 30px; 
    }
    #revtitle {
        font-size: 40px;
        margin-left: 10px;
        padding-top: 5px;
        color: white;
    }
    #revbutton{
        margin-top : 84px;
        margin-left: 10px;
        font-size: 20px;
        position: absolute;
            
    }

    #lab{
        color: white;
    }

    #hr1{
        background-color:grey;
    }
</style>
