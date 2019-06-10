<template>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Add User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Gaegu:300,400,700" rel="stylesheet">

</head>
<body>
<main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Add User Information</div>
                            <div class="card-body">
                                <form name="my-form" onsubmit="return validform()" action="success.php" method="">
                                    <div class="form-group row">
                                        <label for="firstName" class="col-md-4 col-form-label text-md-right">First Name</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.firstName" name="firstName" id="firstName" class="form-control">

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="lastName" class="col-md-4 col-form-label text-md-right">Last Name</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.lastName" name="lastName" id="lastName" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="email" class="col-md-4 col-form-label text-md-right">E-Mail Address</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.email" name="email" id="email" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="username" class="col-md-4 col-form-label text-md-right">Username</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.username" name="username" id="username" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                        <div class="col-md-6">
                                            <input type="password" v-model="user.password" name="password" id="password" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="roleName" class="col-md-4 col-form-label text-md-right">User Role</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="roleName" name="roleName" id="roleName" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="longitude" class="col-md-4 col-form-label text-md-right">Longitude</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.longitude" name="longitude" id="longitude" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="latitude" class="col-md-4 col-form-label text-md-right">Latitude</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="user.latitude" name="latitude" id="latitude" class="form-control">
                                        </div>
                                    </div>                                   

                                        <div class="col-md-6 offset-md-4">
                                            <button v-on:click="addUser()" class="btn btn-primary">
                                            Add User
                                            </button>
                                        </div>
                                </form>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
</template>

<script>
import axios from 'axios';

export default {
    name: 'user',
    data() {
        return {
            user: {
                lastName: '',
                firstName: '',
                username: '',
                password: '',
                email: '',
                longitude: '',
                latitude: ''              
            },
            roleName: ''
        }
    },
    methods: {
        addUser() {
            // let user = {
            //     'firstName': this.user.firstName,
            //     'lastName': this.user.lastName,
            //     'email': this.user.email,
            //     'username': this.user.username,
            //     'password': this.user.password,
            //     'longitude': this.user.longitude,
            //     'latitude': this.user.latitude
            // }
            console.log("something")
            var params = new URLSearchParams()
            params.append('firstName', this.user.firstName)
            params.append('lastName', this.user.lastName)
            params.append('username', this.user.username)
            params.append('email', this.user.email)
            params.append('password', this.user.password)
            params.append('roleName', this.roleName)
            // console.log("user");
            // console.log(this.user);
            // console.log(user);
            // console.log(this.roleName);
            axios.post('http://localhost:8765/user-management-service/admin/adduser', params).then(response => {
                // JSON responses are automatically parsed.
                console.log(response.data)
                ///this.$router.push({name: 'login'});
            })
            .catch(e => {
                console.log(e.response)
            });

            this.$router.push("/admin/userslist");
        }
    }
}
</script>


<style>

@import url(https://fonts.googleapis.com/css?family=Raleway:300,400,600);


body{
    margin: 0;
    font-size: .9rem;
    font-weight: 400;
    line-height: 1.6;
    color: #212529;
    text-align: left;
    background-color: #f5f8fa;
}

.navbar-laravel
{
    box-shadow: 0 2px 4px rgba(0,0,0,.04);
}

.navbar-brand , .nav-link, .my-form, .login-form
{
    font-family: Raleway, sans-serif;
}

.my-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}

.my-form .row
{
    margin-left: 0;
    margin-right: 0;
}

.login-form
{
    padding-top: 1.5rem;
    padding-bottom: 1.5rem;
}

.login-form .row
{
    margin-left: 0;
    margin-right: 0;
}

</style>

