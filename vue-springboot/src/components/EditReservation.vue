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
		<select id="selectReservation" size=20>
		<option>{{reservation.Id}}</option>
		</select>
	</div>
	<div id="right">
		<p> Choose an available hotel: </p>
		<select id="selectHotel" size=20>
			<option>{{hotel.name}} </option>
		</select>
		<table>
		<tr class="table1">
			<td class="table1"><p>Choose room:</p></td>
			<td class="table1"><select id="selectRoom"><option>{{room.Id}}</option></select></td>
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
		reservation: {
			Id: "1"
		},
        hotel: 
        {
			name: "Hotel 1"
        },
		room:
		{
			Id: "1"
		},
		reservations: [],
        hotels: [],
		rooms: []
        
      }
    },
  mounted() {
    axios.get("localhost:8087/reservations")
       .then(res => {
         console.log(res.data);
         this.reservations = res.data;
       })
       .catch(err => {
         console.log(err);
       });
  axios.get("localhost:8089/hotels")
       .then(res => {
         console.log(res.data);
         this.hotels = res.data;
       })
       .catch(err => {
         console.log(err);
       });
  axios.get("localhost:8089/rooms")
       .then(res => {
         console.log(res.data);
         this.rooms = res.data;
       })
       .catch(err => {
         console.log(err);
       });
	}
}
</script>
<style scoped lang="css">
@import 'editReservation.css';
</style>