<template>
<html>
<body>
<div id="header">
	<div id="menu">
		<nav>
			<a href="#">Add Reservations</a>
			<a href="#">Edit Reservations</a>
			<a href="#">Rate Hotels</a>
			<a href="#">Log Out</a>
		</nav>
	</div>
	<div id="user">
		<p id="welcome"> Welcome, User!
		<img alt="" src="https://avatars.servers.getgo.com/2205256774854474505_medium.jpg" id="image"/> </p>
	</div>
</div>
<div id="content">
	<div id="left">
		<p> Choose an available hotel: </p>
		<select id="selectHotel" size=20>
		<option>Hotel 1 </option>
		<option>Hotel 2</option>
		</select>
	</div>
	<div id="right">
		<table>
		<tr>
			<td><p> Hotel longitude: </p></td>
			<td><input type="text"/></td>
		</tr>
		<tr>
			<td><p> Hotel latitude: </p></td>
			<td><input type="text"/></td>
		</tr>
		<tr>
			<td colspan="2"><button type="button">Filter hotels</button></td>
		</tr>
		<tr>
			<td><p>Choose room:</p></td>
			<td><select id="selectRoom"><option>Room 1</option></select></td>
		</tr>
		</table>
	</div>
	<button id="add" type="button">Add Reservation</button>
</div>
</body>
</html>
</template>

<script>
export default {
  name: 'AddReservation' //this is the name of the component
}
</script>

<style>
#selectRoom {
width: 110%;
padding: 4%;
}
input {
padding: 2%;
}
button {
padding-left: 6%;
padding-right: 6%;
padding-top: 3%;
padding-bottom: 3%;
}
#add {
margin-left: 45%;
margin-right: 45%;
margin-top: 4%;
padding: 0.5%;
}
th, td{
padding-left: 20%;
padding-top: 14%;
}
#selectHotel {
width:65%;
margin-top: 5%;
margin-bottom: 5%;
}
#right {
padding: 0;
vertical-align: top;
display: block;
padding-top: 5%;
}
#left {
margin: 0;
padding: 0;
float: left;
width: 50%;
padding-top: 5%;
vertical-align: top;
display: block;
}
* {
padding: 0; margin: 0;
}
html, body {
  height: 100%;
  display: flex;
  flex-direction: column;
}
#content {
height: 100%;
background-color: #AAE3F1;
background-image: url('https://us.123rf.com/450wm/imaginarybo/imaginarybo1608/imaginarybo160800003/62107258-seamless-daisy-flower-pattern-on-white-background-frizzy-ornate-stem-leaves-.jpg?ver=6');
vertical-align: top;
display: block;
padding-bottom: 10%;
}
#header {
background-color: #F3D3CD;
padding-top: 1%;
margin-bottom: -8%;
}
#menu {
float:left; margin-left: 15%; margin-top: -2%;
}
#welcome {
margin-bottom: 10%;
}
#image {
width:3%;
height:3%;
padding-top: 1.5em;
padding-left: 1em;
}
*{
    margin:0;
    padding:0;
}

body{
    font:15px/1.3 'Open Sans', sans-serif;
    color: #5e5b64;
    text-align:center;
}

a, a:visited {
    outline:none;
    color:#389dc1;
}

a:hover{
    text-decoration:none;
}

section, footer, header, aside, nav{
    display: block;
}

nav{
    display:inline-block;
    margin:60px auto 45px;
    background-color:#5597b4;
    box-shadow:0 1px 1px #ccc;
    border-radius:2px;
}

nav a{
    display:inline-block;
    padding: 18px 30px;
    color:#fff !important;
    font-weight:bold;
    font-size:16px;
    text-decoration:none !important;
    line-height:1;
    text-transform: uppercase;
    background-color:transparent;

    -webkit-transition:background-color 0.25s;
    -moz-transition:background-color 0.25s;
    transition:background-color 0.25s;
}

nav a:first-child{
    border-radius:2px 0 0 2px;
}

nav a:last-child{
    border-radius:0 2px 2px 0;
}

p{
    font-size:22px;
    font-weight:bold;
}

p b{
    color:#ffffff;
    display:inline-block;
    padding:5px 10px;
    background-color:#c4d7e0;
    border-radius:2px;
    text-transform:uppercase;
    font-size:18px;
}
.resource {
  margin: 20px 0;
}
</style>