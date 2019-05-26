import Vue from "vue";
import Router from "vue-router";
import HomePage from "./components/HomePage.vue";
import LoginRegisterPage from "./components/LoginRegisterPage.vue";
import AboutUsPage from "./components/AboutUsPage.vue";
import LoginPage from "./components/LoginPage.vue";
import AddReservation from "./components/AddReservation.vue";
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
        path: "/register",
        name: "register",
        component: LoginRegisterPage
    },
     {
         path: "/login",
         name: "login",
         component: LoginPage
     },
     {
         path: "/aboutus",
         name: "aboutus",
         component: AboutUsPage
     },
     {
         path: '/adduser',
         name: 'AddUser',
         component: HomePage
     },
     {
         path: '/account/confirmation',
         name: 'AccountConfirmation',
         component: LoginPage,
         props: (route) => ({ query: route.query.q })
     },
	 {
         path: '/addReservation',
         name: 'AddReservation',
         component: AddReservation
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