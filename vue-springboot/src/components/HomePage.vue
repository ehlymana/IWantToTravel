<template>

<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset = "utf-8">
    <title>Welcome to TATS</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Gaegu:300,400,700" rel="stylesheet">


    <!--<link rel="stylesheet" type="text/css" href="static/css/style.css" />
    -->

</head>

<body>
<div>

<h1 class="my-h1">I Want To Travel</h1>

<ul class="ca-menu">
    <li @click="loginPage()">
        <span class="ca-icon" style="font-family: 'Gaegu', cursive; font-size: 50px">Login</span>
        <div class="ca-content">
        </div>
        
        
    </li>
    <li>
        <router-link to="/aboutus">
            <span class="ca-icon"  style="font-family: 'Gaegu', cursive; font-size: 50px">About Us</span>
            <div class="ca-content">
            </div>
        </router-link>
    </li>
    <li>
        <router-link to="/contuctus">
            <span class="ca-icon"  style="font-family: 'Gaegu', cursive; font-size: 50px">Contact Us </span>
            <div class="ca-content">
            </div>
        </router-link>
        
    </li>
</ul>

<!--<div class="footer" style="background-color: #aced84; margin-top:730px; padding:20px;">-->

<!--
    <div class="row">
        <div class="col-lg-12" style="text-align: center">
            <span style="color:#4CAF50">&copy; 2018  Team 1 - Palindrom | Travel Activity Tracker System </span>
        </div>
    </div>-->
</div>


</body>
</html>
</template>

<script>
export default {
    methods: {
        loginPage() {
            window.location.href = "http://localhost:8765/user-management-service"
        }
    }
    
}
</script>

<style scoped>
    @import url(https://fonts.googleapis.com/css?family=Roboto:400,700,400italic,300&subset=latin,latin-ext);

    /* GENERAL CSS */

    * {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'Roboto', sans-serif;
        background-repeat: no-repeat;
        background-attachment: fixed;
        background-image: url("http://www.pptgrounds.com/wp-content/uploads/2013/08/Travel-Backpacks-Backgrounds.jpg");
        background-position: bottom;
        background-repeat: no-repeat;
    }

    /* INDEX */

    .ca-menu {
        padding:0;
        margin: 100px auto;
        width: 1020px;
    }

    .ca-menu li {
        width: 230px;
        height: 230px;
        border: 10px solid #f6f6f6;
        overflow: hidden;
        position: relative;
        float:left;
        background: #e4b9b9;
        margin-right: 4px;
        -webkit-box-shadow: 1px 1px 2px rgba(0,0,0,0.2);
        -moz-box-shadow: 1px 1px 2px rgba(0,0,0,0.2);
        box-shadow: 1px 1px 2px rgba(0,0,0,0.2);
        -webkit-border-radius: 125px;
        -moz-border-radius: 125px;
        border-radius: 125px;
        -webkit-transition: all 400ms linear;
        -moz-transition: all 400ms linear;
        -o-transition: all 400ms linear;
        -ms-transition: all 400ms linear;
        transition: all 400ms linear;
    }

    .ca-menu li:last-child {
        margin-right: 0px;
    }

    .ca-menu li a {
        text-align: left;
        width: 100%;
        height: 100%;
        display: block;
        color: #333;
        position: relative;
    }

    .ca-icon {
        font-size: 35px;
        color: #e6e6e6;
        line-height: 60px;
        position: absolute;
        width: 100%;
        height: 60px;
        left: 0px;
        top: 80px;
        text-align: center;
        -webkit-transition: all 400ms linear;
        -moz-transition: all 400ms linear;
        -o-transition: all 400ms linear;
        -ms-transition: all 400ms linear;
        transition: all 400ms linear;
    }

    .ca-main {
        font-size: 24px;
        position: absolute;
        top: 110px;
        height: 80px;
        width: 170px;
        left: 50%;
        margin-left: -85px;
        opacity: 0.8;
        text-align: center;
    }

    .ca-sub {
        text-align:center;
        color: #000;
        font-size: 26px;
        position: absolute;
        height: 80px;
        width: 170px;
        left: 50%;
        margin-left: -85px;
        top: 110px;
        opacity: 0;
        -webkit-transition: all 400ms linear;
        -moz-transition: all 400ms linear;
        -o-transition: all 400ms linear;
        -ms-transition: all 400ms linear;
        transition: all 400ms linear;
    }

    .ca-menu li:hover {
        background: #f7f7f7;
        -webkit-transform: rotate(360deg);
        -moz-transform: rotate(360deg);
        -o-transform: rotate(360deg);
        -ms-transform: rotate(360deg);
        transform: rotate(360deg);
        box-shadow: 0 45px 60px -50px #000000;
    }

    .ca-menu li:hover .ca-icon {
        color: #555;
        font-size: 40px;
    }

    .ca-menu li:hover .ca-main {
        display: none;
    }

    .ca-menu li:hover .ca-sub {
        opacity: 0.8;
    }

    /* HEADER */

    .my-h1 {
        text-align: left;
        margin-top: 10px;
        margin-left: 220px;
        font-size: 70px;
        color: #665353;
        font-family: 'Gaegu', cursive;
    }


    
</style>
