<template>
      <div class="navbar">
      <div class="netflixLogo">
        <a id="logo" href="#home"><img src="https://postfiles.pstatic.net/MjAyMDA5MDRfODcg/MDAxNTk5MTk2MzEyOTc0.pl2V80Z0RXDWOksTQNYBAbSpxFDfyd4bI54_3X6PE4kg.8PzKTA8eG5Jm6KqUcXjVKyTlz2ElQ7OSlECOaKLiBHwg.PNG.god85a/output-onlinepngtools.png?type=w966" alt="Logo Image"></a>
      </div>      
      <nav class="main-nav">              
        <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal"/>
        <a href="#movies">기능</a>
        <a href="#originals">기능</a>
        <a href="#">기능</a>
        <a target="_blank" href="https://codepen.io/cb2307/full/NzaOrm">기능</a>        
      </nav>
      <nav class="sub-nav">
        <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
        <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a>
        
        <a href="#" @click="changeLogin()">로그인</a>
      
        <a href="#">회원가입</a>        
      </nav>      
    </div>
</template>
<script>
import LoginModal from '@/components/modal/LoginModal.vue'
export default {
    name : 'Navbar',
    components:{
      LoginModal
    },
    data(){
      return {
        loginModal : false
      }

    },methods:{
      changeLogin () {
      this.loginModal = !this.loginModal
      }
    }
}
</script>
<style scoped>
.navbar{
  padding: 20px 20px 0 20px;
  position: fixed;
  top: 0;
  display: grid;  
  grid-gap:5px;
  grid-template-columns: 1fr 4fr 1fr;
  grid-template-areas: 
   "nt mn mn sb . . . "; 
  background-color: var(--light);
  width: 100%;
  margin-bottom: 0px;
  
  background-color:black;
}
.netflixLogo {
  grid-area: nt;
  object-fit: cover;
  width: 100px;
  max-height: 100%;
  
  padding-left: 30px;
  padding-top: 0px;  
}

.netflixLogo img {  
  height: 35px;     
}

#logo {
  color: #E50914;  
  margin: 0; 
  padding: 0; 
}
.main-nav {
  grid-area: mn;
  padding: 0 30px 0 20px;
}

.main-nav a {
  color:white;
  text-decoration: none;
  margin: 5px;  
}

.main-nav a:hover {
  color: red;
}

.sub-nav {
  grid-area: sb;
  padding: 0 40px 0 40px;
}

.sub-nav a {
  color: white;
  text-decoration: none;
  margin: 5px;
}

.sub-nav a:hover {
  color: red;
}

</style>