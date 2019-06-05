<template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a class="active" href="/addReservation">Add Reservations</a>
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
		<td colspan="2" class="tableAdd2"><select class="selectHotel" size=10 @change="onChange($event)"><option v-for="hotel in hotels" v-bind:key="hotel.hotelName">{{hotel.hotelName}}</option></select></td>
		</tr>
		<tr class="tableAdd2">
			<td class="tableAdd2"><p>Choose room:</p></td>
			<td class="tableAdd2"><select class="selectRoom"><option v-for="room in rooms" v-bind:key="room.roomId">{{room.roomId}}</option></select></td>
		</tr>
		<tr class="tableAdd2">
		<td colspan="2" class="tableAdd2"><button class="add2" type="button">Add Reservation</button></td>
		</tr>
		</table>
	</div>
	<div class="right">
		<table id="tableAdd">
		<tr class="tableAdd">
			<td class="tableAdd"><p> Hotel longitude: </p></td>
			<td class="tableAdd"><input type="text"/></td>
		</tr>
		<tr class="tableAdd">
			<td class="tableAdd"><p> Hotel latitude: </p></td>
			<td class="tableAdd"><input type="text"/></td>
		</tr>
		<tr class="tableAdd">
			<td colspan="2" class="tableAdd"><button id="filter" type="button">Filter hotels</button></td>
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
		rooms: []
      }
    },
  mounted() {
  axios.get("http://localhost:8089/hotels")
       .then(res => {
         this.hotels = res.data.hotels;
       })
       .catch(err => {
         console.log(err);
       });
	},
  methods: {
	onChange(event) {
		axios.get("http://localhost:8089/roomsByHotel/" + event.target.value)
       .then(res => {
         this.rooms = res.data;
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