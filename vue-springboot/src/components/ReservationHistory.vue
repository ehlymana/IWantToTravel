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
		<select id="selectR" size=20>
			<option>{{reservation.Id}}</option>
		</select>
		</td>
		<td>
		<select id="selectH" size=20>
			<option>{{hotel.Name}}</option>
		</select>
		</td>
		<td>
		<select id="selectR2" size=20>
			<option>{{room.Id}}</option>
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
@import 'reservationHistory.css';
</style>