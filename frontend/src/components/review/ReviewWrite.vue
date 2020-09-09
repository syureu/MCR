<template>
    <div id="revcontainer">
        <div id="revtitle">리뷰</div>
        <hr>
        <div id="content">
        <div id="line1">
        <label id="lab" for="">내평점</label> <button id="togglebtn" @click="changetoggle">{{ name }}</button>
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
import StarRating from 'vue-star-rating';
export default {
    components: {
        StarRating,
    },
    data(){
        return {
            temp:false,
            rating:"",
            toggle: false,
            name: "펼치기",

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
        color: black;
        background-color: white;
        float: right;
        margin-right : 10px;
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        border: 0;
        outline: 0;
        
    }
    #togglebtn:hover{
        color: #96063b;
    }
    #incontainer{
        border: 2px solid black;
        margin : 10px;
        padding : 10px;
    }
    #revcontainer{
    }
    #revcontent {
            width: 90%;
            height: 100px;
            resize: none;
            margin-top : 20px;
            border : 2px solid grey;
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
        }
        #revbutton{
            margin-top : 84px;
            margin-left: 10px;
            font-size: 20px;
            position: absolute;
            
        }
</style>
