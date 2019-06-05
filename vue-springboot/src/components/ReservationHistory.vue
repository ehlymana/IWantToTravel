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
	<table id="tableAllReservations">
		<tr class="tableAll">
			<td colspan="3" class="tableAll"><p> All user reservations: </p></td>
		</tr>
		<tr>
		<td>
		<select id="selectR" size=15>
		<option v-for="reservation in reservations" v-bind:key="reservation.reservationID">{{reservation.reservationID}}</option>
		</select>
		</td>
		<td>
		<select id="selectH" size=15>
			<option v-for="reservation in reservations" v-bind:key="reservation.hotel.hotelId">{{reservation.hotel.hotelId}}</option>
		</select>
		</td>
		<td>
		<select id="selectR2" size=15>
			<option v-for="reservation in reservations" v-bind:key="reservation.room.roomId">{{reservation.room.roomId}}</option>
		</select>
		</td>
		</tr>
	</table>
</div>
</body>
</html>
</template>

<script>
import axios from 'axios';
export default {
  name: 'ReservationHistory', //this is the name of the component
      data() {
      return {
		reservations: [],
        hotels: [],
		rooms: []
      }
    },
	mounted() {
    // user id će se dobiti iz autentifikacije, zasad se koristi ovaj za provjeru
    var userId = 1;
    axios.get("http://localhost:8087/allReservationsFromUser?userID=" + userId)
       .then(res => {
        this.reservations = res.data.reservations;
		console.log(this.reservations);
       })
       .catch(err => {
         console.log(err);
       });
	}
}
</script>
<style scoped lang="css">
@import 'reservationHistory.css';
</style>