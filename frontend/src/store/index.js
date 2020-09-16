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
      state.userData.userno = userinfo.userNo
      state.userData.birthday = userinfo.birthday
      state.userData.age = userinfo.age
      state.userData.gender = userinfo.gender
      state.userData.nation = userinfo.nation
      state.userData.city = userinfo.city
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