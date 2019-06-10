<template>
    <html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>TATS</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Gaegu:300,400,700" rel="stylesheet">

</head>
<body>

<div class="container-fluid">
    <div class="row my-header">
        <div class="col-sm-10" >
            <h1>Admin panel</h1>
        </div>
        <div class="col-sm-2 logout-div">
            <form th:action="@{/logout}" th:method="post">
                <button type="submit" class="btn btn-link">
                    <i class="fa fa-sign-out"></i> Logout
                </button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="col-sm-10 text-center" style="margin: auto; margin-top: 120px;border: 2px solid #050505;padding-top: 30px;padding-bottom: 30px;border-radius: 5px; text-decoration: none; color: black">
                <a v-on:click="seeUsers()" class="my-btn-link">
                    <i class="fa fa-users fa-5x" style="font-size: 200px;"></i>
                    <h4>See Users</h4>
                </a>
                
            </div>
        </div>
        <div class="col-sm-6">
            <div class="col-sm-10 text-center" style="margin: auto; margin-top: 120px;border: 2px solid #050505;padding-top: 30px;padding-bottom: 30px;border-radius: 5px;">
                <a v-on:click="seeHotels()" class="my-btn-link">
                    <i class="fas fa-hotel fa-5x" style="font-size: 195px;"></i>
                    <h4>See Hotels</h4>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
</template>

<script>
export default {
    methods: {
        seeUsers() {
            this.$router.push("/admin/userslist");
        },
        seeHotels() {
            this.$router.push("/admin/hotelslist");
        }
    }
    
}
</script>


<style>
    .my-header {
        height: 75px;
        font-family: 'Roboto', sans-serif;
        color: white;
        background-color: #611216;
    }
    .my-header h1 {
        margin-top: 15px;
        margin-left: 20px;
    }
    .logout-div {

    }
    .logout-div button {
        margin-top: 26px;float: right;
        color: white;

    }
    .logout-div button:hover{
        text-decoration: none;
        color: #611216;
    }
    i:hover{
        color: #611216;
        cursor: pointer;
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
        color: #000;
    }

    .my-btn-link:hover {
        color: #611216;
        text-decoration: none;
    }

</style>

