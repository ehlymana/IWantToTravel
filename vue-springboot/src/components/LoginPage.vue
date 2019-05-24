<template>
    <html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
            <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
            <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
            <!------ Include the above in your HEAD tag ---------->
        </head>

        <body>
<div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-2">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a href="#" class="active" id="login-form-link">Login</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#"  id="register-form-link">Register</a>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" role="form" style="display: block;">
                                        <div class="form-group">
                                            <input type="text" name="username" id="username" ref="username" tabindex="1" class="form-control" placeholder="Username" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" ref="password" tabindex="2" class="form-control" placeholder="Password">
                                        </div>
                                        <div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                            <label for="remember"> Remember Me</label>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-12 ">
                                                    <div class="alert alert-success" th:if="${successMessage}">
                                                        <strong th:text="${successMessage}">Success!</strong>
                                                    </div>
                                                </div>
                                                <div class="col-sm-12 " >
                                                    <div class="alert alert-danger" th:if="${failMessage}">
                                                        <strong th:text="${failMessage}">Danger!</strong>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <button @click.stop.prevent="login()" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" >
                                                        Login
                                                        </button>                                                
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="text-center">
                                                        <a th:href="@{/password/forgotten}" tabindex="5" class="forgot-password">Forgot Password?</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                    <form id="register-form" role="form" style="display: none;">
                                        <div class="user">
                                        <div class="form-group">
                                            <!-- <input type="text" v-model="user.firstName" placeholder="First Name"> -->
                                            <input type="text" v-model="user.firstName" name="firstName" id="firstName"  tabindex="1" class="form-control" placeholder="First Name">
                                        </div>
                                        <div class="form-group">
                                            <!-- <input type="text" v-model="user.lastName" placeholder="Last Name"> -->
                                            <input type="text" v-model="user.lastName" name="lastName" id="lastName" tabindex="1" class="form-control" placeholder="Last Name">
                                        </div>
                                        <div class="form-group">
                                            <!-- <input type="text" v-model="user.username" placeholder="Username"> -->
                                            <input type="text" v-model="user.username" name="username" id="username" tabindex="1" class="form-control" placeholder="Username">
                                        </div>
                                        <div class="form-group">
                                            <!-- <input type="email" v-model="user.email" placeholder="Email"> -->
                                            <input type="email" v-model="user.email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" >
                                        </div>
                                        <div class="form-group">
                                            <!-- <input type="password" v-model="user.password" name="password" placeholder="Password"> -->
                                            <input type="password" v-model="user.password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                        </div>
                                        <div class="form-group">
                                            <!-- <input type="text" v-model="user.firstName" name="confirm-password" placeholder="Confirm Password"> -->
                                            <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="Confirm Password">
                                        </div>

                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <button @click="registerUser()" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" >
                                                        Register Now
                                                        </button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--<div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                </div>
                                            </div>
                                        </div>-->
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
      
      
        </div>
</div>
        </body>
    </html>
</template>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!--  
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
-->
<script>
import {AXIOS} from '../http_common'
import JQuery from 'vue-jquery'
import { URLSearchParams } from 'url';
import Axios from 'axios';
var $ = JQuery



export default {
    name: 'user',
    data() {
        return {
            response: [],
            errors: [],
            user: {
                lastName: '',
                firstName: ''                
            },
        }
    },
    methods: {     
        login() {
            var username = this.$refs.username.value;
            var password = this.$refs.password.value;
            console.log(username);
            console.log(password);
            // var params = new URLSearchParams();
            
            // params.append('username', username);
            // params.append('password', password);
            AXIOS.post("http://localhost:8084/usersUI/login", {
                username: username,
                password: password
            }).then(response => {
                    // params = [];
                    console.log(response.data)
                    //this.$router.push({name: 'login'});
                }).catch (e => {
                    this.errors.push(e);
                })
        },   
        registerUser() {
            console.log("something")
            var params = new URLSearchParams()
            params.append('firstName', this.user.firstName)
            params.append('lastName', this.user.lastName)
            params.append('username', this.user.username)
            params.append('email', this.user.email)
            params.append('password', this.user.password)

            AXIOS.post(`http://localhost:8084/usersUI/registration`, params)
                .then(response => {
                // JSON responses are automatically parsed.
                params = [];
                console.log(response.data)
                this.$router.push({name: 'login'});
            })
            .catch(e => {
                this.errors.push(e)
            })
        }
    },
    mounted() {
        // executes on page load
        var token = this.$route.query.token;
        if(token.data != null || token.data != '') {        
            AXIOS.get('http://localhost:8084/usersUI/account/confirmation?token=' + token)
                        .then(response => {               
                        console.log(response.data)
                    })
                    .catch(e => {
                        this.errors.push(e)
                    })
        }
     }
    
}

</script>

<style scoped>
 body {
        padding-top: 145px;
        background: #4CAF50;
        background-image: url("http://www.pptgrounds.com/wp-content/uploads/2013/08/Travel-Backpacks-Backgrounds.jpg");
        background-repeat: no-repeat;
        background-position: bottom;
        background-attachment: fixed;
    }

    .panel-login {
        border-color: #ccc;
        -webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
        -moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
        box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
    }
    .panel-login>.panel-heading {
        color: #00415d;
        background-color: #fff;
        border-color: #fff;
        text-align:center;
    }
    .panel-login>.panel-heading a{
        text-decoration: none;
        color: #666;
        font-weight: bold;
        font-size: 15px;
        -webkit-transition: all 0.1s linear;
        -moz-transition: all 0.1s linear;
        transition: all 0.1s linear;
    }
    .panel-login>.panel-heading a.active{
        color: #029f5b;
        font-size: 18px;
    }
    .panel-login>.panel-heading hr{
        margin-top: 10px;
        margin-bottom: 0px;
        clear: both;
        border: 0;
        height: 1px;
        background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
        background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
        background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
        background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
    }
    .panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
        height: 45px;
        border: 1px solid #ddd;
        font-size: 16px;
        -webkit-transition: all 0.1s linear;
        -moz-transition: all 0.1s linear;
        transition: all 0.1s linear;
    }
    .panel-login input:hover,
    .panel-login input:focus {
        outline:none;
        -webkit-box-shadow: none;
        -moz-box-shadow: none;
        box-shadow: none;
        border-color: #ccc;
    }
    .btn-login {
        background-color: #59B2E0;
        outline: none;
        color: #fff;
        font-size: 14px;
        height: auto;
        font-weight: normal;
        padding: 14px 0;
        text-transform: uppercase;
        border-color: #59B2E6;
    }
    .btn-login:hover,
    .btn-login:focus {
        color: #fff;
        background-color: #53A3CD;
        border-color: #53A3CD;
    }
    .forgot-password {
        text-decoration: underline;
        color: #888;
    }
    .forgot-password:hover,
    .forgot-password:focus {
        text-decoration: underline;
        color: #666;
    }

    .btn-register {
        background-color: #1CB94E;
        outline: none;
        color: #fff;
        font-size: 14px;
        height: auto;
        font-weight: normal;
        padding: 14px 0;
        text-transform: uppercase;
        border-color: #1CB94A;
    }
    .btn-register:hover,
    .btn-register:focus {
        color: #fff;
        background-color: #1CA347;
        border-color: #1CA347;
    }

    .my-btn-link {
        text-decoration: none;
        color: #611216;
    }

    .my-btn-link:hover {
        opacity: 0.7;
        color: #611216;
        text-decoration: none;
    }

</style>
