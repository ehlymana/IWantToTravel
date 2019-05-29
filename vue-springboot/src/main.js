import Vue from 'vue'
import App from './App.vue'
import router from './router'
//var cors = require('cors');

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')


