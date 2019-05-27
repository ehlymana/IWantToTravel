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
		<select id="selectHotelRating" size=20>
		<option>Hotel 1 </option>
		<option>Hotel 2</option>
		</select>
	</div>
	<div id="right">
		<p> Add comment: </p>
		<textarea id="comment" rows="21" cols="70"/>
	</div>
	<button class="rate" type="button">Rate Hotel</button>
</div>
</body>
</html>
</template>

<script>
export default {
  name: 'RateHotel' //this is the name of the component
}
</script>
<style scoped lang="css">
@import 'rateHotel.css';
</style>