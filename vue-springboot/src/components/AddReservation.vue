<template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a class="active" href="/user/dashboard">Add Reservations</a>
			<a href="/editReservation">Edit Reservations</a>
			<a href="/rateHotel">Rate Hotels</a>
			<a href="/logout">Log Out</a>
		</nav>
	</div>
	<div id="user">
		<p id="welcome"> Welcome, User!
		<img alt="" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/1024px-User_icon_2.svg.png" id="image"/> </p>
	</div>
</div>
<div class="content">
	<div class="left">
		<table class="tableAdd2">
		<tr class="tableAdd2">
		<td colspan="2" class="tableAdd2"><p> Choose an available hotel: </p></td>
		</tr>
		<tr class="tableAdd2">
		<td colspan="2" class="tableAdd2"><select class="selectHotel" size=10 @change="onChange($event)"><option v-for="hotel in hotels" v-bind:key="hotel.hotelName">Hotel {{hotel.hotelName}}, {{hotel.hotelLocation}} ({{hotel.hotelLongitude}}°, {{hotel.hotelLatitude}}°)</option></select></td>
		</tr>
		<tr class="tableAdd2">
			<td class="tableAdd2"><p>Choose room:</p></td>
			<td class="tableAdd3">
			<select class="selectRoom" @change="onChange2($event)">
			<option :selected="true">Choose Room</option>
			<option v-for="room in rooms" v-bind:key="room.roomId">Room no.{{room.roomId}} ({{room.roomBeds}} beds)</option>
			</select></td>
		</tr>
		<tr class="tableAdd2">
		<td colspan="2" class="tableAdd2"><button class="add2" type="button" v-on:click="add">Add Reservation</button></td>
		</tr>
		</table>
	</div>
	<div class="right">
		<table id="tableAdd">
		<tr class="tableAdd">
			<td class="tableAdd"><p> Near longitude: </p></td>
			<td class="tableAdd"><input type="text" v-model="longitude"/></td>
		</tr>
		<tr class="tableAdd">
			<td class="tableAdd"><p> Near latitude: </p></td>
			<td class="tableAdd"><input type="text" v-model="latitude"/></td>
		</tr>
		<tr class="tableAdd">
			<td colspan="2" class="tableAdd"><button id="filter" type="button" v-on:click="filter">Filter hotels</button></td>
		</tr>
		</table>
	</div>
	
</div>
</body>
</html>
</template>

<script>
import axios from 'axios';
export default {
  name: 'AddReservation', //this is the name of the component
  data() {
      return {
        hotels: [],
		rooms: [],
		selectedHotel: "",
		selectedRoom : "",
		longitude: null,
		latitude: null
      }
    },
  mounted() {
	axios.get("http://localhost:8765/hotel-management-service/hotels")
       .then(res => {
         this.hotels = res.data.hotels;
       })
       .catch(err => {
         console.log(err);
       });
	},
  methods: {
	onChange(event) {
		this.selectedHotel = event.target.value.substring(6);
		var i = 0;
		while (this.selectedHotel[i] != ',' && i < this.selectedHotel.length) i++;
		this.selectedHotel = this.selectedHotel.substring(0, i);
		axios.get("http://localhost:8765/hotel-management-service/roomsByHotel/" + this.selectedHotel)
       .then(res => {
         this.rooms = res.data;
       })
       .catch(err => {
         console.log(err);
       });
	},
	onChange2(event) {
		this.selectedRoom = event.target.value.substring(8);
		var i = 0;
		while (this.selectedRoom[i] != ' ' && i < this.selectedRoom.length) i++;
		this.selectedRoom = this.selectedRoom.substring(0, i);
	},
	filter() {
		var http = "";
		if (this.longitude == null || this.longitude.length == 0) this.longitude = -1000;
		if (this.latitude == null || this.latitude.length == 0) this.latitude = -1000;
		if (this.longitude != null || this.latitude != null) http = "http://localhost:8765/hotel-management-service/filterHotels?hotelLongitude=" + this.longitude + "&hotelLatitude=" + this.latitude;
		if (this.longitude == -1000) this.longitude = "";
		if (this.latitude == -1000) this.latitude = "";
		axios.get(http)
       .then(res => {
         this.hotels = res.data.hotels;
       })
       .catch(err => {
         console.log(err);
       });
	},
	add() {
		// user id će se dobiti iz autentifikacije, zasad se koristi ovaj za provjeru
		var userID = 1;
		axios.post("http://localhost:8765/reservations-service/addReservation?hotelName=" + this.selectedHotel + "&userID=" + userID + "&roomID=" + this.selectedRoom)
		.then( () => {
         alert("Reservation successfully added!");
       })
       .catch(err => {
         console.log(err);
       });
	}
  }
}
</script>
<style scoped lang="css">
@import 'addReservation.css';
</style>