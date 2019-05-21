import Vue from "vue";
import Router from "vue-router";
import HomePage from "./components/HomePage.vue";
import LoginRegisterPage from "./components/LoginRegisterPage.vue";
import AboutUsPage from "./components/AboutUsPage.vue";
// import AddCustomer from "./components/AddCustomer.vue";
// import SearchCustomers from "./components/SearchCustomers.vue";
// import Customer from "./components/Customer.vue";
 
Vue.use(Router);
 
export default new Router({
  mode: "history",
  routes: [
    {
        path: "/",
        name: "home",
        alias: "/home",
        component: HomePage
     },
     {
         path: "/login",
         name: "login",
         component: LoginRegisterPage
     },
     {
         path: "/aboutus",
         name: "aboutus",
         component: AboutUsPage
     }
//     {
//       path: "/add",
//       name: "add",
//       component: AddCustomer
//     },
//     {
//       path: "/search",
//       name: "search",
//       component: SearchCustomers
//     }
 ]
});