<template>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Bootstrap Example</title>
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
            <h1>Admin Panel</h1>
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
        <div class="col-sm-12">
            <div class="col-sm-10" style="padding-top: 20px;font-size: 16pt;padding-left: 0;">
                <router-link to="/admin/dashboard">
                    <a class="my-btn-link">Back</a>
                </router-link>
            </div>
            <div class="col-sm-2" style="float: right;text-align: right;padding: 0; font-size: 16pt;margin-top: -40px;">
                <router-link to="/admin/adduser">
                    <button type="button" class="btn btn-link my-btn-link" style="font-size: 18pt;">
                        <i class="fa fa-plus" style="font-size:16pt;color:green"></i> Add User
                    </button>
                </router-link>
            </div>
        </div>
        <div class="col-sm-12">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th>Longitude</th>
                        <th>Latitude</th>
                        <th>Edit</th>
                        <th>Delete</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in users" v-bind:key="user">
                        <td>{{ user.username }}</td>
                        <td>{{ user.firstName }}</td>
                        <td>{{ user.lastName }}</td>
                        <td>{{ user.role.roleName }}</td>
                        <td>{{ user.longitude }}</td>
                        <td>{{ user.latitude}}</td>  
                        <td><button type="button" class="btn btn-link my-btn-link" @click="editUser(user.userID)">
                            <i class="fa fa-edit" style="font-size:16pt;color:#254A25;"></i>
                        </button> </td>
                        <td><a class="btn btn-link my-btn-link">
                            <i class="fa fa-remove" style="font-size:16pt;color:#254A25;"></i>
                        </a></td>                      
                    </tr>
                    
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="addUserModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add User</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <form th:id="add-user-form" th:action="@{/adduser}" th:object="${user}" method="post" role="form" >
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter new Username" name="username" th:value="${user.getUsername()}" th:field="*{username}">
                    </div>
                    <div class="form-group">
                        <input type="password" tabindex="1" class="form-control" placeholder="Enter New Password" name="password" th:value="${user.getPassword()}" th:field="*{password}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter email" name="email" th:value="${user.getEmail()}" th:field="*{email}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter first name" name="firstName" th:value="${user.getFirstName()}" th:field="*{firstName}" >
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter last name" name="lastName" th:value="${user.getLastName()}" th:field="*{lastName}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter role" name="role" th:value="${user.getRole()}" th:field="*{role}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter longitude" name="longitude"  th:value="${user.getLongitude()}" th:field="*{longitude}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter latitude" name="latitude" th:value="${user.getLatitude()}" th:field="*{latitude}">
                    </div>
                    <input type="hidden" th:value="${user.getUserID()}" th:field="*{userID}">
                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" style="background-color: #4CAF50;" th:form="add-user-form">Add User</button>
            </div>
        </div>
    </div>
</div>
<!-- 
<th:block th:each="u : ${usersList}">
    <div class="modal fade" th:id="'editModal-' + ${u.getUserID()}">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form th:id="'edit-form-' + ${u.getUserID()}" th:action="@{'/edit/user/'+${u.getUserID()}}" th:object="${user}" method="post" role="form" >
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter new Username" name="username" th:value="${u.getUsername()}">
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter email" name="email" th:value="${u.getEmail()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter first name" name="firstName" th:value="${u.getFirstName()}"  >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter last name" name="lastName" th:value="${u.getLastName()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter role" name="role" th:value="${u.getRole()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter longitude" name="longitude"  th:value="${u.getLongitude()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter latitude" name="latitude" th:value="${u.getLatitude()}" >
                        </div>
                        <input type="hidden" th:value="${u.getUserID()}" >
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary" style="background-color: #4CAF50;" th:form="'edit-form-' + ${u.getUserID()}">Save</button>
                </div>
            </div>
        </div>
    </div>
</th:block> -->

</body>
</html>
</template>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

import axios from 'axios';

export default {
    data() {
        return {
            users: []
        }
    },
    methods: {
        getUsers() {

        },
        editUser(userID) {
            console.log(userID);
            
        }
    },
    mounted () {
        
        axios.get("http://localhost:8765/user-management-service/admin/getusers")
        .then(response => {
          //  console.log(JSON.stringify(response.data.users));
            this.users = response.data.users;

           
        }).catch(error => {
            console.log(error);
        });
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
