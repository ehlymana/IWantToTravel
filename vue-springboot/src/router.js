import Vue from "vue";
import Router from "vue-router";
import HomePage from "./components/HomePage.vue";
import LoginRegisterPage from "./components/LoginRegisterPage.vue";
import AboutUsPage from "./components/AboutUsPage.vue";
import LoginPage from "./components/LoginPage.vue";
import AddReservation from "./components/AddReservation.vue";
import EditReservation from "./components/EditReservation.vue";
import ReservationHistory from "./components/ReservationHistory.vue";
import RateHotel from "./components/RateHotel.vue";
import AdminDashboard from "./components/AdminDashboard.vue";
import UsersListPage from "./components/UsersListPage.vue";
import HotelsListPage from "./components/HotelsListPage.vue";
import AddUserPage from "./components/AddUserPage.vue";
import AddHotel from "./components/AddHotel.vue";
import EditHotel from "./components/AddHotel.vue";

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
        path: '/admin/dashboard',
        name: 'admindashboard',
        component: AdminDashboard
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
     },
	{
         path: '/editReservation',
         name: 'EditReservation',
         component: EditReservation
     },
	{
         path: '/reservationHistory',
         name: 'ReservationHistory',
         component: ReservationHistory
     },
	{
         path: '/rateHotel',
         name: 'RateHotel',
         component: RateHotel
     },
     {
        path: '/admin/userslist',
        name: 'userslist',
        component: UsersListPage
     },
     {
         path: '/admin/hotelslist',
         name: 'hotelslist',
         component: HotelsListPage
     },
     {
        path: '/admin/adduser',
        name: 'adduser',
        component: AddUserPage
     },
      {
          path: '/admin/addhotel',
          name: 'AddHotel',
          component: AddHotel
      },
      {
          path: '/admin/editHotel/:id',
          name: 'EditHotel',
          component: EditHotel
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