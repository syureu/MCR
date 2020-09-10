import Vue from 'vue'
import App from './App.vue'
import routes from './routes'
import VueRouter from 'vue-router'
import store from './store'
import VueSession from 'vue-session'

Vue.config.productionTip = false
Vue.use(VueRouter)
const router = new VueRouter({
  // mode: 'history',
  store,
  routes,
});
new Vue({
  render: h => h(App),
  router,
  
}).$mount('#app')
Vue.use(VueSession)
