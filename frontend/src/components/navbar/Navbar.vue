<template>
      <div class="navbar">
      <div class="netflixLogo">
        <a id="logo" href="/"><img src="../../assets/logo.png" alt="Logo Image"></a>
      </div>      
      <nav class="main-nav">              
        <LoginModal v-if="loginModal" @close="changeLogin" @change="changeModal"/>
       
        <a href="#">BEST</a>
        <a href="#">TOP100</a>
        
      </nav>
      <nav class="mid-nav">
        <div class="box">
       
        <input type="text" placeholder="영화를 검색하세요" v-model="searchKeyword"  @keyup.enter="searchKeywords"/>
         <a href="" @click="searchKeywords"><i class="fas fa-search sub-nav-logo"></i></a>
        </div>
      </nav>
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
        searchKeyword: "",
      }

    },
    computed: {
      isLoggedIn() {
          return this.$store.getters.isLoggedIn
      }
    },watch:{
      


      
    },

    methods:{
      searchKeywords(){
          this.$router.push(`/search/${this.searchKeyword}`)
      },
      logout(){
        this.$session.remove('jwstoken')
        this.$store.commit('logout')
        this.$router.go()
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
      }
    }
}
</script>
<style scoped>
.logoutbox{
  
}
.navbar{
  height: 100px;
  padding: 20px 20px 0 20px;
  position: fixed;
  top: 0;
  display: grid;  
  grid-gap:5px;
  grid-template-columns: 1fr 3fr 1fr 1fr;
  grid-template-areas: 
   "nt mn mc sb . . . "; 
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

  grid-area:mc;
  
}
.mid-nav input{
  display:inline;
  background-color:rgb(48, 46, 46);
  border-radius : 7px;
  margin-left:15px;
  color:white;
  border :0px;  
}
.mid-nav a {
  color: white;
  text-decoration: none;
  margin-left: 5px;
}

.mid-nav a:hover {
  color: red;
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
  margin: 4px;
}

.sub-nav a:hover {
  color: red;
}

@media (max-width: 812px){
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
}
</style>