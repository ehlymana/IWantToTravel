<template>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Edit Hotel</title>
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
                        <div class="card-header">Edit Hotel Information</div>
                            <div class="card-body">
                                <form name="my-form" onsubmit="return validform()" action="success.php" method="">
                                    <div class="form-group row">
                                        <label for="hname" class="col-md-4 col-form-label text-md-right">Hotel Name</label>
                                        <div class="col-md-6">
                                            <input type="text" name="hname" placeholder="Hotel name" v-model="hotel.name" id="hname" class="form-control">

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hdesc" class="col-md-4 col-form-label text-md-right">Description</label>
                                        <div class="col-md-6">
                                            <input type="text" name="hdesc" placeholder="Description" v-model="hotel.description" id="hdesc" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="haddress" class="col-md-4 col-form-label text-md-right">Address</label>
                                        <div class="col-md-6">
                                            <input type="text" name="haddress" placeholder="Address" v-model="hotel.address" id="haddress" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hcity" class="col-md-4 col-form-label text-md-right">City</label>
                                        <div class="col-md-6">
                                            <input type="text" name="hcity" placeholder="City" v-model="hotel.city" id="hcity" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hlongitude" class="col-md-4 col-form-label text-md-right">Longitude</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.longitude" placeholder="Longitude" name="hlongitude" id="hlongitude" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hlatitude" class="col-md-4 col-form-label text-md-right">Latitude</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.latitude" placeholder="Latitude" name="hlatitude" id="hlatitude" class="form-control">
                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label class="col-md-4 col-form-label text-md-right">Supervizor</label>
                                           <select class="field" v-model="hotel.supervizor">
                                               <option v-for="u in users" :value="u.id">{{u.firstName}}</option>
                                           </select>
                                    </div>

                                        <div class="col-md-6 offset-md-4">
                                            <button v-on:click="addHotel()" class="btn btn-primary">
                                            Save Hotel
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
    name: 'Ed',
    data() {
        return {
             hotel:
                      {
                        name: "",
                        description: "",
                        address: "",
                        city: "",
                        longitude: 0,
                        latitude: 0,
                        supervizor: 1
                      },
                    users: [],
                    id: null
        }
    },
    mounted() {
        this.id = this.$route.params.id
            axios.get("http://localhost:8765/user-management-service/admin/getusers")
            .then(response => {
                this.users = response.data.users;

               axios.get(`http://localhost:8765/hotel-management-service/admin/hotels/${this.id}`)
               .then(res => {
                  console.log(res.data) /* eslint-disable-line no-console */
                  this.hotel=res.data
               })
               .catch(err => {
                  console.log(err) /* eslint-disable-line no-console */
               });

            }).catch(error => {
                console.log(error);
            });
        },
    methods: {
        addHotel() {

            axios.put(`http://localhost:8765/hotel-management-service/admin/hotels/${this.id}`, this.hotel).then(response => {
                console.log(response.data)
                ///this.$router.push({name: 'login'});
            })
            .catch(e => {
                console.log(e.response)
            });

            this.$router.push("/admin/hotelslist");
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
