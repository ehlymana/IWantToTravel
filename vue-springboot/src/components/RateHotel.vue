  <template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a href="/addReservation">Add Reservations</a>
			<a href="/editReservation">Edit Reservations</a>
			<a class="active" href="/rateHotel">Rate Hotels</a>
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
		<p> Choose a visited hotel: </p>
		<select id="selectHotelRating" size=15 @change="onChange($event)"><option v-for="hotel in hotels" v-bind:key="hotel.hotelName">Hotel {{hotel.hotelName}}, {{hotel.hotelLocation}}</option></select>
	</div>
	<div id="right">
		<p> Add comment: </p>
		<textarea id="comment" rows="24" cols="70"/>
	</div>
	<button class="rate" type="button">Rate Hotel</button>
</div>
</body>
</html>
</template>

<script>
import axios from 'axios';
export default {
  name: 'RateHotel', //this is the name of the component
  data() {
      return {
        hotels: [],
		selectedHotel: ""
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
		this.selectedHotel = event.target.value.substring(6);
		var i = 0;
		while (this.selectedHotel[i] != ',' && i < this.selectedHotel.length) i++;
		this.selectedHotel = this.selectedHotel.substring(0, i);
		axios.get("http://localhost:8089/roomsByHotel/" + this.selectedHotel)
       .then(res => {
         this.rooms = res.data;
       })
       .catch(err => {
         console.log(err);
       });
	},
	onChange2(event) {
		this.selectedRoom = event.target.value;
	}
  }
}
</script>
<style scoped lang="css">
@import 'rateHotel.css';
</style>