<template>
      <div class="navbar">
      <div class="netflixLogo">
        <a id="logo" v-if="!isLoggedIn" href="/"><img src="../../assets/logo.png" alt="Logo Image"></a>
        <a id="logo" v-if="isLoggedIn" href="/home"><img src="../../assets/logo.png" alt="Logo Image"></a>
      </div>      
      <nav class="main-nav">              
        <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal"/>
        <a @click="MoveKoreaMove">한국 영화</a>
        <a href="#">외국 영화</a>
      </nav>
      <nav class="mid-nav" v-if="isLoggedIn">
        <div class="box">
       
        <input type="text" placeholder="영화를 검색하세요" v-model="searchKeyword"  @keyup.enter="searchKeywords" />
         <a href="" @click="searchKeywords"><i class="fas fa-search sub-nav-logo"></i></a>
        </div>
      </nav>
      <Chartmovie/>
      <nav class="sub-nav">
        <div v-if="!isLoggedIn">
        <!-- <a href="#"><i class="fas fa-bell sub-nav-logo"></i></a> -->
        <a href="#" @click="changeLogin">로그인</a>
        <a href="#" @click="signup">회원가입</a>
        </div>
        <div v-else>
                    <a  @click="changeMypage">마이페이지</a>
                    <a  @click="logout">로그아웃</a>

        </div>
      </nav>
         <!-- Modal --> 
          
    </div>
    
</template>
<script>

import LoginModal from '@/components/modal/LoginModal.vue'
import Chartmovie from '@/components/navbar/Chartmovie.vue'
export default {
    name : 'Navbar',
    components:{
      LoginModal,
      Chartmovie
    },
    data(){
      return {
        loginModal : false,
        searchKeyword: "",
      

      }

    },
    computed: {
      isLoggedIn() {
          return this.$store.getters.isLoggedIn
      }
    },watch:{
        searchKeyword(){
          return this.searchKeyword = this.searchKeyword.replace(/[!{}@?#$%^&*()_+'";,.<>[/?:=-]/g,'')
        }
    },

    methods:{
     
      searchKeywords(){
          if(this.searchKeyword.trim()===""){
           alert('검색어를 입력하세요')
            return 
        }
   

          this.$router.push(`/search/${this.searchKeyword}`)
          location.reload()
      },
       
      logout(){
        this.$session.remove('jwstoken')
        this.$store.commit('logout')
        this.$router.push({name: 'Main'})
      },
      changeMypage(){
        this.$router.push({name:'Mypage'})
      },
      signup(){
        this.$router.push({name:'Signup'})
      },
      changeLogin () {
          this.loginModal = !this.loginModal
      },
      changeModal(){
        this.loginModal = !this.loginModal
      },
      MoveKoreaMove(){
        this.$router.push({name:'MovieKorea'})
      }
    }
}
</script>
<style scoped>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);

.navbar{
  font-family: 'Hanna', sans-serif;
  height: 12vh;
  padding: 2vh 2vw 2vh 2vw;
  position: fixed;
  top: 0;
  background-color: var(--light);
  width: 100%;
  margin-bottom: 0px;
  z-index: 10;
  font-size: 1.5vw;
  background-color:rgb(0, 0, 0);
}
.netflixLogo {
  object-fit: cover;
  width: 20%;
  max-height: 100%;
  padding-left: 2.5vw;
  padding-top: 0px;
  float: left;
}

.netflixLogo img {  
  
  width: 12vw;
  height: 6vh; 

}

#logo {
  color: #E50914;  
  margin: 0; 
  padding: 0; 
}
.main-nav {
  padding: 0 2.5vw 0 1vw;
  float:left;
}
.mid-nav{
  
  
}
.mid-nav input{
  display:inline;
  background-color:rgb(48, 46, 46);
  border-radius : 7px;
  margin-left:1.5vw;
  color:white;
  border :0px;  
}
.mid-nav a {
  
  color: white;
  text-decoration: none;
  margin-left: 0.5vw;
}

.mid-nav a:hover {
  color: red;
}
.main-nav a {
  font-size: 1.5vw;
  color:white;
  text-decoration: none;
  margin: 0.5vh 0.5vw 0.5vh 0.5vw;  
}

.main-nav a:hover {
  color: red;
}

.sub-nav {
  padding: 0 3.5vw 0 3.5vw;
}

.sub-nav a {
  color: white;
  font-size:1.5vw;
  margin: 0.4vh 0.4vw 0.4vh 0.4vw;
}

.sub-nav a:hover {
  color: red;
}
/* @media(max-width: 1200px) {

  .navbar {
    display: grid;
    grid-gap: 20px;
    grid-template-columns: repeat(2, 1fr);
    grid-template-areas: 
    "nt nt nt  .  .  sb sb sb . . "
    "mn mn mn mn mn mn  mn mn mn mn";
  }
} */
 /* @media (max-width: 812px){
  .navbar{
    margin: 0;
    padding: 20px 0 0 0;
    position: static;
    display: grid;
    grid-gap: 15px;
    grid-template-columns: repeat(1, 1fr);
    grid-template-areas: 
    ". nt ."    
    ". . mn"
    ". . sb";
    text-align: center;
    background-color:black;
  } 
  .main-nav{
    display:none;
  }
  .mid-nav{
    grid-area: sb;
    
  }
  .sub-nav{
    grid-area:mn;
  } 
}*/
</style>