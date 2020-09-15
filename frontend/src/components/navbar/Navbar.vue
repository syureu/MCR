<template>
      <div class="navbar">
      <div class="netflixLogo">
        <a id="logo" href="/"><img src="../../assets/logo.png" alt="Logo Image"></a>
      </div>      
      <nav class="main-nav">              
        <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal"/>
       
        <a href="#">한국영화</a>
        <a href="#">미국영화</a>
        <a href="#">유럽영화</a>
        
      </nav>
      <nav class="mid-nav">
        <div class="box">
          <input type="text" placeholder="영화를 검색하세요"/>
        </div>
      </nav>
      <nav class="sub-nav">
        <div v-if="!isLogin">
          <a href="#"><i class="fas fa-search sub-nav-logo"></i></a>
        <!-- <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a> -->
        <a href="#" @click="changeLogin">로그인</a>
        <a href="#" @click="signup">회원가입</a>
        </div>
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
        loginModal : false,
        isLogin :false
      }

    },methods:{
      signup(){
        this.$router.push({name:'Signup'})
      },
      changeLogin () {
          this.loginModal = !this.loginModal
      },
      changeModal(){
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
  z-index: 10;
  
  background-color:black;
}
.netflixLogo {
  grid-area: nt;
  object-fit: cover;
  width: 50px;
  max-height: 100%;
  
  padding-left: 30px;
  padding-top: 0px;  
}

.netflixLogo img {  
  height: 50px; 

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
.mid-nav{
  margin-left: 85%;
  grid-area: mn;
}
.mid-nav input{
  background-color:rgb(48, 46, 46);
  border-radius : 7px;
  width : 200px;
  height: 40px;
  color:white;
  border :0px;
}
.main-nav a {
  font-family: 'Noto Sans JP', sans-serif;
  font-size: 20px;
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