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
                        <div class="card-header">Add Hotel Information</div>
                            <div class="card-body">
                                <form name="my-form" onsubmit="return validform()" action="success.php" method="">
                                    <div class="form-group row">
                                        <label for="hotelName" class="col-md-4 col-form-label text-md-right">Hotel Name</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.hotelName" name="hotelName" id="hotelName" class="form-control">

                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hotelDescription" class="col-md-4 col-form-label text-md-right">Hotel Description</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.hotelDescription" name="hotelDescription" id="hotelDescription" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hotelLocation" class="col-md-4 col-form-label text-md-right">City</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.hotelLocation" name="hotelLocation" id="hotelLocation" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hotelAddress" class="col-md-4 col-form-label text-md-right">Address</label>
                                        <div class="col-md-6">
                                            <input type="text" v-model="hotel.hotelAddress" name="hotelAddress" id="hotelAddress" class="form-control">
                                        </div>
                                    </div>


                                    <div class="form-group row">
                                        <label for="hotelLongitude" class="col-md-4 col-form-label text-md-right">Longitude</label>
                                        <div class="col-md-6">
                                            <input type="number" v-model="hotel.hotelLongitude" name="hotelLongitude" id="hotelLongitude" class="form-control">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label for="hotelLatidude" class="col-md-4 col-form-label text-md-right">Latitude</label>
                                        <div class="col-md-6">
                                            <input type="number" v-model="hotel.hotelLatidude" name="hotelLatidude" id="hotelLatidude" class="form-control">
                                        </div>
                                    </div>                                   

                                        <div class="col-md-6 offset-md-4">
                                            <button v-on:click="addHotel()" class="btn btn-primary">
                                            Add Hotel
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
    name: 'hotel',
    data() {
        return {
            hotel: {
                hotelName: '',
                hotelDescription: '',
                hotelLocation: '',
                hotelAddress: '',
                hotelLongitude: 0,
                hotelLatitude: 0             
            },
        }
    },
    methods: {
        addHotel() {
            console.log("something")
            var params = new URLSearchParams()
            params.append('hotelName', this.hotel.hotelName)
            params.append('hotelDescription', this.hotel.hotelDescription)
            params.append('hotelLocation', this.hotel.hotelLocation)
            params.append('hotelAddress', this.hotel.hotelAddress)
            params.append('hotelLongitude', this.hotel.hotelLongitude)
            params.append('hotelLatitude', this.hotel.hotelLatitude)

            axios.post('http://localhost:8765/hotel-management-service/addHotel', params).then(response => {
                console.log(response.data)
                window.alert("Hotel added!");
            })
            .catch(e => {
                window.alert("Error adding hotel!");
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

