import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

// const session = this.$session
export default new Vuex.Store({
  plugins: [createPersistedState({
    storage: window.sessionStorage,
  })],
  state: {
    // isLoggedIn: false,
    userData: null,
    navbar: false,
  },
  mutations: {
    login(state, userData) {
      state.isLoggedIn = true
      state.userData = userData
    },
    logout(state) {
      console.log('test')
      state.isLoggedIn = false
      state.userData = null
      // this.$router.go('/')
    },
    
    setUserInfo(state, userinfo) {
      state.userData.userinfo.userno = userinfo.userNo
      state.userData.userinfo.birthday = userinfo.birthday
      state.userData.userinfo.pw = userinfo.pw
      state.userData.userinfo.gender = userinfo.gender
      state.userData.userinfo.nation = userinfo.nation
      state.userData.userinfo.city = userinfo.city
    }
  },
  actions: {
    setNavbar() {
      setTimeout(() =>{
        
      })
    }
  },
  modules: {
  },
  getters: {
    isLoggedIn(state) {
      if (state.userData === null) {
        return false
      }
      else {
        return true
      }
    },
    getUserData(state) {
      return state.userData
    },
    
  }
})
