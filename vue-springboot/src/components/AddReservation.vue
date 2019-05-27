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
		<p> Choose an available hotel: </p>
		<select class="selectHotel" size=20>
		<option>Hotel 1 </option>
		<option>Hotel 2</option>
		</select>
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
		<tr class="tableAdd">
			<td class="tableAdd"><p>Choose room:</p></td>
			<td class="tableAdd"><select class="selectRoom"><option>Room 1</option></select></td>
		</tr>
		</table>
	</div>
	<button class="add" type="button">Add Reservation</button>
</div>
</body>
</html>
</template>

<script>
export default {
  name: 'AddReservation' //this is the name of the component
}
</script>
<style scoped lang="css">
@import 'addReservation.css';
</style>