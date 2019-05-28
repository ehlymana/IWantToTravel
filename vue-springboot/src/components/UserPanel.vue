<template>
  <div class="main">
    <HeaderU/>

    <div class="newReservation">
        <img alt="New reservation" src="../assets/addreservation.png" class="icon">
        <button type="button">Add reservation</button>
    </div>

    <div class="historyReservation">
        <img alt="History" src="../assets/history.png" class="icon">
        <button type="button">See reservation history</button>
    </div>

  </div>
</template>



<script>
import HeaderU from './HeaderU.vue';

export default {
    name: 'UserPanel',
    components: {
        HeaderU
    }
}

</script>




<style scoped>
    .newReservation {
        text-align: center;
        margin: 20px 400px 20px 400px;
        border: solid black 1px;
    }
    .historyReservation {
        text-align: center;
        margin: 20px 400px 20px 400px;
        border: solid black 1px;
    }
    .icon {
        width: 100px;
        height: 100px;
        margin:10px;
    }
    button {
        float: right;
        width: 100px;
        height: 50px;
        margin: 40px 200px auto 0px;
    }
</style>
