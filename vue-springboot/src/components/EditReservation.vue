 <template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a href="/user/dashboard">Add Reservations</a>
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
		<select id="selectReservation" size=15 @change="onChange3($event)">
		<option v-for="reservation in reservations" v-bind:key="reservation.reservationID">Reservation no.{{reservation.reservationID}}, Hotel {{reservation.hotel.hotelName}} </option>
		</select>
	</div>
	<div id="right">
		<p> Choose an available hotel: </p>
		<select id="selectHotel" size=15 @change="onChange($event)">
			<option v-for="hotel in hotels" v-bind:key="hotel.hotelName">Hotel {{hotel.hotelName}}, {{hotel.hotelLocation}}</option>
		</select>
		<table>
		<tr class="table1">
			<td class="table1"><p>Choose room:</p></td>
			<td class="table1">
			<select id="selectRoom2" @change="onChange2($event)">
				<option :selected="true">Choose Room</option>
				<option v-for="room in rooms" v-bind:key="room.roomId">Room no.{{room.roomId}} ({{room.roomBeds}} beds)</option>
			</select></td>
		</tr>
		</table>
	</div>
	<table id="table2">
		<tr>
			<td><button id="historyButton" type="button" onclick="location.href='/reservationHistory';">Reservation History</button></td>
			<td><button id="editButton" type="button" v-on:click="editR">Edit Reservation</button></td>
			<td><button id="deleteButton" type="button" v-on:click="deleteR">Delete Reservation</button></td>
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
		hotelsReservations: [],
        hotels: [],
		rooms: [],
		selectedReservation: "",
		selectedHotel: "",
		selectedRoom : ""
      }
    },
  mounted() {
	this.getEverything();
	},
	methods: {
		getEverything() {
			// user id će se dobiti iz autentifikacije, zasad se koristi ovaj za provjeru
			var userId = 1;
			axios.get("http://localhost:8765/reservations-service/allReservationsFromUser?userID=" + userId)
				.then(res => {
					this.reservations = res.data.reservations;
				})
				.catch(err => {
					console.log(err);
				});
			axios.get("http://localhost:8765/hotel-management-service/hotels")
				.then(res => {
					this.hotels = res.data.hotels;
				})
				.catch(err => {
					console.log(err);
				});
		},
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
		onChange3(event) {
			this.selectedReservation = event.target.value.substring(15);
			var i = 0;
			while (this.selectedReservation[i] != ',' && i < this.selectedReservation.length) i++;
			this.selectedReservation = this.selectedReservation.substring(0, i);
		},
		editR() {
		console.log("http://localhost:8765/reservations-service/editReservation?hotelName=" + this.selectedHotel + "&roomID=" + this.selectedRoom + "&reservationID=" + this.selectedReservation);
			axios.post("http://localhost:8765/reservations-service/editReservation?hotelName=" + this.selectedHotel + "&roomID=" + this.selectedRoom + "&reservationID=" + this.selectedReservation)
			.then( () => {
				alert("Reservation successfully edited!");
				this.getEverything();
			})
			.catch(err => {
				console.log(err);
			});
		},
		deleteR() {
			axios.post("http://localhost:8765/reservations-service/deleteReservation?id=" + this.selectedReservation)
			.then( () => {
				alert("Reservation successfully deleted!");
				this.getEverything();
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