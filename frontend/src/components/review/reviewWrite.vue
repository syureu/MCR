<template>
    <div id="revcontainer">
        <div id="revtitle">리뷰</div>
        <hr>
        <div id="content">
        <div id="line1">
        <label id="lab" for="">내평점</label> <button id="togglebtn" @click="changetoggle">펼치기</button>
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
            toggle: true,

        }
    },
    methods:{
        changetoggle() {
            this.toggle = !this.toggle
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
        float: right;
    }
    #incontainer{
        border: 2px solid black;
        margin : 10px;
        padding : 10px;
    }
    #revcontainer{
        background-color : #be9b24;
    }
    #revcontent {
            width: 90%;
            height: 100px;
            resize: none;
            margin-top : 20px;
            border : 2px solid black;
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
