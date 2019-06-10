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
                <a th:href="@{/admin}" class="my-btn-link">Back</a>
            </div>
            <div class="col-sm-2" style="float: right;text-align: right;padding: 0; font-size: 16pt;margin-top: -40px;">
                <button type="button" class="btn btn-link my-btn-link" data-toggle="modal" data-target="#addHotelModal" style="font-size: 18pt;"><i class="fa fa-plus" style="font-size:16pt;color:green"></i> Add Hotel</button>
            </div>
        </div>
        <div class="col-sm-12">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Latitude</th>
                    <th>Longitude</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="hotel: ${hotelsList}">
                    <td th:text="${hotel.getName()}"></td>
                    <td th:text="${hotel.getDescription()}"></td>
                    <td th:text="${hotel.getAddress()}"></td>
                    <td th:text="${hotel.getLocation()}"></td>
                    <td th:text="${hotel.getLatitude()}"></td>
                    <td th:text="${hotel.getLongitude()}"></td>
                    <td><button type="button" class="btn btn-link my-btn-link" data-toggle="modal" th:attr="data-target='#editModal-' + ${hotel.getHotelID()}">
                        <i class="fa fa-edit" style="font-size:16pt;color:#254A25;"></i>
                    </button> </td>
                    <td><a class="btn btn-link my-btn-link" th:href="@{'/deletehotel/' + ${hotel.getHotelID()}}">
                        <i class="fa fa-remove" style="font-size:16pt;color:#254A25;"></i>
                    </a></td>
                </tr>
                </tbody>
            </table>
        </div>

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

<div class="modal fade" id="addHotelModal">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add Hotel</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <div class="modal-body">
                <form th:id="add-hotel-form" th:action="@{/addhotel}" th:object="${hotel}" method="post" role="form" >
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter New Name" name="name"  th:field="*{name}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter New Description" name="description"  th:field="*{description}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter Address" name="address"  th:field="*{address}" >
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter City" name="city"  th:field="*{location}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter Longitude" name="longitude"   th:field="*{longitude}">
                    </div>
                    <div class="form-group">
                        <input type="text" tabindex="1" class="form-control" placeholder="Enter Latitude" name="latitude"  th:field="*{latitude}">
                    </div>
                </form>
                <div id="map-leaflet" class="map col-sm-12" style="height: 500px;"></div>

            </div>

            <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button type="submit" class="btn btn-primary" style="background-color: #4CAF50;" th:form="add-hotel-form">Add Hotel</button>
            </div>
        </div>
    </div>
</div>

<th:block th:each="h : ${hotelsList}">
    <div class="modal fade" th:id="'editModal-' + ${h.getHotelID()}">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form th:id="'edit-form-' + ${h.getHotelID()}" th:action="@{'/edit/hotel/'+${h.getHotelID()}}"  method="post" role="form" >
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter New Name" name="name" th:value="${h.getName()}">
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter New Descritpion" name="description" th:value="${h.getDescription()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter Address" name="address" th:value="${h.getAddress()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter City" name="location" th:value="${h.getLocation()}" >
                        </div>

                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter Longitude" name="longitude"  th:value="${h.getLongitude()}" >
                        </div>
                        <div class="form-group">
                            <input type="text" tabindex="1" class="form-control" placeholder="Enter Latitude" name="latitude" th:value="${h.getLatitude()}" >
                        </div>
                        <input type="hidden" th:value="${h.getHotelID()}" >
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="s ubmit" class="btn btn-primary" style="background-color: #4CAF50;" th:form="'edit-form-' + ${h.getHotelID()}">Save</button>
                </div>
            </div>
        </div>
    </div>
</th:block>




</body>
</html>
</template>

<script>

//     mapboxgl.accessToken = 'pk.eyJ1Ijoibm9yYTciLCJhIjoiY2pqc3ZhZjdvMDEycTNwbHB2YTliNHZhaiJ9.gaDdgUl4KTrCB_kvDVG-jA';

//     var atoken = 'pk.eyJ1Ijoibm9yYTciLCJhIjoiY2pqc3ZhZjdvMDEycTNwbHB2YTliNHZhaiJ9.gaDdgUl4KTrCB_kvDVG-jA';

//     $('input[name="location"]').change(function() {

//         //  var address = $('input[name="address"]').val();

//         var city = $('input[name="location"]').val();

//         var getBBoxURL = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' + city + '.json?access_token=' + atoken;

//         //Sarajevo.json?access_token=pk.eyJ1Ijoibm9yYTciLCJhIjoiY2pqc3ZhZjdvMDEycTNwbHB2YTliNHZhaiJ9.gaDdgUl4KTrCB_kvDVG-jA&country=ba'

//         minX = 0;

//         minY = 0;

//         maxX = 0;

//         maxY = 0;

//         $.ajax({

//             method: 'GET',

//             url: getBBoxURL,

//         }).done(function(data){

//             //  console.log(data.features.bbox)

//             minX = data.features[0].bbox[0];

//             minY = data.features[0].bbox[1];

//             maxX = data.features[0].bbox[2];

//             maxY = data.features[0].bbox[3];

//             console.log(minX + ',' + minY + ',' + maxX + ',' + maxY);

//             //  $('input[name="address"]').change(function() {

//             var address = $('input[name="address"]').val();

//             var hotelAddressURL = 'https://api.mapbox.com/geocoding/v5/mapbox.places/' + address + ' ' + city + '.json?access_token=' + atoken + '&bbox=' + minX + ',' + minY + ',' + maxX + ',' + maxY;

//             //ulica+marsala+tita+sarajevo.json?access_token=pk.eyJ1Ijoibm9yYTciLCJhIjoiY2pqc3ZhZjdvMDEycTNwbHB2YTliNHZhaiJ9.gaDdgUl4KTrCB_kvDVG-jA&bbox=' + minX + ',' + minY + ',' + maxX + ',' + maxY;

//             $.ajax({
//                 method: 'GET',
//                 url: hotelAddressURL,
//             }).done(function(data){
//                 var listResults = data.features;
//                 // console.log(data);
//                 for(var i = 0; i < listResults.length; i++) {
//                     console.log(listResults[i].center[0] + ' ' + listResults[i].center[1]);
//                 }

//                 var longitude = listResults[0].center[0];
//                 //
//                 var latitude = listResults[0].center[1];
//                 console.log(latitude + ' ' + longitude);

//                 $('input[name="longitude"]').val(longitude);
//                 $('input[name="latitude"]').val(latitude);

//                 var atoken = 'pk.eyJ1Ijoibm9yYTciLCJhIjoiY2pqc3ZhZjdvMDEycTNwbHB2YTliNHZhaiJ9.gaDdgUl4KTrCB_kvDVG-jA';

//                 L.mapbox.accessToken = atoken;

//                 console.log(listResults[0].center[1] + listResults[0].center[0]);

//                 var mapLeaflet = L.mapbox.map('map-leaflet', 'mapbox.streets')

//                     .setView([latitude, longitude], 15);

//                 L.marker([latitude, longitude]).addTo(mapLeaflet);

//                 mapLeaflet.scrollWheelZoom.disable();

//             });

//             //});

//         });

//     });

//     // console.log(minX + ',' + minY + ',' + maxX + ',' + maxY);

// 
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
