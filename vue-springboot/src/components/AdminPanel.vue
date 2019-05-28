<template>
  <div class="main">
    <Header/>

    <div class="userPart">
        <img alt="User" src="../assets/user.png" class="icon">
        <br>
        <button type="button">See all users</button>
        <br>
        <button type="button">Manage users</button>
    </div>

    <div class="hotelPart">
        <img alt="Hotel" src="../assets/hotel.png" class="icon">
        <br>
        <button type="button">See all users</button>
        <br>
        <button type="button">Manage users</button>
    </div>

  </div>
</template>



<script>
import Header from './Header.vue';

export default {
    name: 'AdminPanel',
    components: {
        Header
    }
}

</script>




<style scoped>
    .icon {
        width: 100px;
        height: 100px;
        margin-bottom:20px;
    }

    .userPart {
        float: left;
        width:50%;
        text-align: center;
        margin-top: 10px;
        margin-left: 50px;
        margin-right: 50px;
    }

    .hotelPart {
        text-align: center;
        margin-top: 10px;
        margin-left: 50px;
        margin-right: 50px;
    }

    button {
        margin: 10px;
        width: 300px;
        height: 50px;
    }
</style>
