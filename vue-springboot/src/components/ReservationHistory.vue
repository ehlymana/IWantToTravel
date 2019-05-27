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
			<option>Reservation 1 </option>
			<option>Reservation 2</option>
		</select>
		</td>
		<td>
		<select id="selectH" size=20>
			<option>Hotel 1 </option>
			<option>Hotel 2</option>
		</select>
		</td>
		<td>
		<select id="selectR2" size=20>
			<option>Room 1 </option>
			<option>Room 2</option>
		</select>
		</td>
		</tr>
	</table>
</div>
</body>
</html>
</template>

<script>
export default {
  name: 'ReservationHistory' //this is the name of the component
}
</script>
<style scoped lang="css">
@import 'reservationHistory.css';
</style>