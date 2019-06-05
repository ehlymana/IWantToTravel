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
		<select id="selectHotelRating" size=15 @change="onChange($event)"><option v-for="hotel in hotels" v-bind:key="hotel">Hotel {{hotel}}</option></select>
	</div>
	<div id="right">
		<p> Add comment: </p>
		<textarea id="comment" rows="16" cols="50" v-model="comment"/>
	</div>
	<button class="rate" type="button" v-on:click="rate">Rate Hotel</button>
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
		hotelIDs: [],
		selectedHotel: "",
		selectedID: -1,
		comment: ""
      }
    },
  mounted() {
    // user id će se dobiti iz autentifikacije, zasad se koristi ovaj za provjeru
    var userId = 1;
	axios.get("http://localhost:8087/allReservationsFromUser?userID=" + userId)
       .then(res => {
			this.hotels = [];
			for (var i = 0; i < res.data.reservations.length; i++) {
				var exists = false;
				for (var j = 0; j < this.hotels.length; j++) {
					if (this.hotels[j].valueOf() == res.data.reservations[i].hotel.hotelName.valueOf()) {
						exists = true;
						break;
					}
				}
				if (exists == false)
					this.hotels.push(res.data.reservations[i].hotel.hotelName);
					this.hotelIDs.push(res.data.reservations[i].hotel.hotelId);
			}
       })
       .catch(err => {
         console.log(err);
       });
	},
	methods: {
	onChange(event) {
		this.selectedHotel = event.target.value.substring(6);
		for (var i = 0; i < this.hotels.length; i++) {
			if (this.hotels[i].valueOf() == this.selectedHotel.valueOf()) {
				this.selectedID = this.hotelIDs[i];
				break;
			}
		}
	},
	rate() {
		axios.post("http://localhost:8086/addReview?hotelID=" + this.selectedID + "&reviewText=" + this.comment)
       .then(() => {
         alert("Review successfully added!");
       })
       .catch(err => {
         console.log(err);
       });
	}
  }
}
</script>
<style scoped lang="css">
@import 'rateHotel.css';
</style>