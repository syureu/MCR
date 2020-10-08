<template>
    <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">

            <div class="modal-header">
                <slot name="header">
                <h3>Box ofiice 순위</h3> 
                <button type="button" class="close" style="color:white;" data-dismiss="modal" @click="modalclose">×</button>
                </slot>
            </div>
            <div class="" v-for="(movie, index) in rolling" :key="movie.movieId">
                <ul>
        
                    <li @click="gotomovie(movie)"><p style="color: white">  {{ index + 1}} .  {{ movie }} </p></li>
                    
                </ul>
            </div>

            </div>
        </div>
        </div>
        
    </transition>
</template>

<script>
// import * as EmailValidator from 'email-validator'
import axios from 'axios'
import HTTP from "@/util/http-common.js"
export default {
    props : {
    rolling : Object
    },
    name : 'LoginModal',
    data() {
        return{ 
            moN:"",
            errorData:{
              userid : false,
            }
        }
    },
    methods: {
     gotomovie(title1) {
         axios.get(`${HTTP.BASE_URL}/mcr/daummovie/returnidbytitle` ,
        {
                params: { title: title1 }
        }
        )
        .then(res => {
           this.moN = res.data.object
           if(res.data.object == null){
             alert('아직 영화가 업데이트되지 않았습니다.')
           }
           else{
            this.gogo()
           }
           
        
          
        })
        .catch(err => {
   
            console.log(err)
        })
     },
     gogo() {
         this.$router.push({name: 'FeedDetail', params: {movieId: this.moN}})
         this.modalclose()
         location.reload()
         
     },
    modalclose () {
      this.$emit('close')
    },
    },
}
</script>

<style scoped>
ul{
   list-style:none;
   padding-left:0px;
   }
li:hover {background-color: gray; cursor: pointer;}

  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height:100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }
  
  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .form input{   
    float: right;
    margin-right :10%;
    width: 60%;
    border-radius: 5px;
  }
  .modal-container {
    font-size:15px;
    width: 450px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: black;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
  }

  .modal-header h3 {
    margin-top: 0;
    color: red;
  }

  .modal-body {
    margin: 20px 0;
    
  }
  .modal-body label{
    font-family: 'Hanna', sans-serif;
    font-size:15px;
    color: white;
  }

  .modal-default-button {
    float: right;
    background-color:black;
    color:white;
    border:0;
    outline:0;
  }
  .modal-default-button:hover{
    color:rgb(167, 7, 7);
  }

  
  .modal-enter {
    opacity: 0;
  }

  .modal-leave-active {
    opacity: 0;
  }

  .modal-enter .modal-container,  
  .modal-leave-active .modal-container {
    -webkit-transform: scale(1.1);
    transform: scale(1.1);
  }
  @media (max-width: 812px) {
    
    .modal-container {
      width:350px;
    }
  }
</style>
