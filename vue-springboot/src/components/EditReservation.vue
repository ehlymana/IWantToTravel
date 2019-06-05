 <template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a href="/addReservation">Add Reservations</a>
			<a class="active" href="/editReservation">Edit Reservations</a>
			<a href="/rateHotel">Rate Hotels</a>
			<a href="/logout">Log Out</a>
		</nav>
	</div>
	<div id="user">
		<p id="welcome"> Welcome, User!
		<img alt="" src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/1024px-User_icon_2.svg.png" id="image"/> </p>
	</div>
</div>
<div id="content">
	<div id="left">
		<p> Pick an existing reservation: </p>
		<select id="selectReservation" size=15>
		<option v-for="reservation in reservations" v-bind:key="reservation.reservationID">{{reservation.reservationID}}</option>
		</select>
	</div>
	<div id="right">
		<p> Choose an available hotel: </p>
		<select id="selectHotel" size=15 @change="onChange($event)">
			<option v-for="hotel in hotels" v-bind:key="hotel.hotelName">{{hotel.hotelName}}</option>
		</select>
		<table>
		<tr class="table1">
			<td class="table1"><p>Choose room:</p></td>
			<td class="table1"><select id="selectRoom"><option v-for="room in rooms" v-bind:key="room.roomId">{{room.roomId}}</option></select></td>
		</tr>
		</table>
	</div>
	<table id="table2">
		<tr>
			<td><button id="historyButton" type="button" onclick="location.href='/reservationHistory';">Reservation History</button></td>
			<td><button id="editButton" type="button">Edit Reservation</button></td>
			<td><button id="deleteButton" type="button">Delete Reservation</button></td>
		</tr>
	</table>
</div>
</body>
</html>
</template>

<script>
import axios from 'axios';
export default {
  name: 'EditReservation', //this is the name of the component
    data() {
      return {
		reservations: [],
        hotels: [],
		rooms: []
      }
    },
  mounted() {
    axios.get("localhost:8087/reservations")
       .then(res => {
         this.reservations = res.data;
		 console.log(this.reservations);
       })
       .catch(err => {
         console.log(err);
       });
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
@import 'editReservation.css';
</style>