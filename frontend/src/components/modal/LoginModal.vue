<template>
    <transition name="modal">
        <div class="modal-mask">
        <div class="modal-wrapper">
            <div class="modal-container">

            <div class="modal-header">
                <slot name="header">
                <h3>Login</h3> 
                </slot>
            </div>

            <div class="modal-body">
                <div class="form">
                    <label class="input-label" for="inputname">아이디</label>
                    <input type="text" id="inputname" placeholder="이메일을 입력하세요." v-model="userid">                
                    <div v-if="errorData.userid" v-text="errorData.userid"></div>  
                </div>
                <div class="form">
                    <label class="input-label" for="inputpassword">비밀번호</label>
                    <input type="password" id="inputpassword" placeholder="패스워드를 입력하세요." v-model="password">    
                </div>
            </div>

            <div class="modal-footer">
                <button class="modal-default-button" @click="login">로그인</button>
                <button class="modal-default-button" @click="$emit('close')">
                    취소
                </button>
                </div>
            </div>
        </div>
        </div>
        
    </transition>
</template>

<script>
// import * as EmailValidator from 'email-validator'
import axios from 'axios'
import URL from '@/util/http-common.js'
export default {
    name : 'LoginModal',
    data() {
        return{ 
            userid:"",
            password:"",
            errorData:{
              userid : false,
            }
        }
    },
    methods: {
      formcheck(){
        if(this.userid=== "" || (this.userid.length>0))
          this.errorData.userid = "올바른 아이디 형식이 아닙니다."
        else this.errorData.userid = false
      },
      login(){
           this.formcheck()
           let loginData={
             userid: this.userid,
             pw : this.password
           } 
      axios.post(`${URL.BASE_URL}/mcr/login`, loginData)
      .then(res => {
        if (res.data.status === true) {
          console.log(res)
          this.$session.set('jwstoken', res.headers.jwstoken)
          this.$store.commit('login', res.data.object)
          this.modalclose()
        }
        else {
          alert('일치하는 회원정보가 없습니다.')
        }
      })
      .catch(err => {
        alert(err)
      })
    },

    modalclose () {
      this.$emit('close')
    },
    },
}
</script>

<style scoped>
    /* model */
  .modal-mask {
    position: fixed;
    z-index: 9998;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, .5);
    display: table;
    transition: opacity .3s ease;
  }

  .modal-wrapper {
    display: table-cell;
    vertical-align: middle;
  }
  .form input{
    float:right;
    margin-right :10%;
    width: 60%;
  }
  .modal-container {
    width: 450px;
    margin: 0px auto;
    padding: 20px 30px;
    background-color: black;
    border-radius: 2px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
    transition: all .3s ease;
    font-family: Helvetica, Arial, sans-serif;
  }

  .modal-header h3 {
    margin-top: 0;
    color: red;
  }

  .modal-body {
    margin: 20px 0;
    
  }
  .modal-body label{
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
</style>
