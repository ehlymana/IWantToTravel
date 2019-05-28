<template>
  <div class="header">
    <img alt="Home" src="../assets/home.png" class="icon home">
    <div class="logout">
        <img alt="Logout" src="../assets/logout.png" class="icon">
    </div>
    <div class="text">Admin Panel</div>
    <div class="logout">
        <div class="text">Log out</div>
    </div>
  </div>
</template>



<script>
export default {
  name: 'Header',
}
</script>




<style scoped>
    .header {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-auto-rows: minmax(50px, auto);
        background-color: red;
        border: solid 3px black;
    }

    .icon {
        width: 50px;
        height: 50px;
    }

    .home {
        margin-left: 20px;
    }

    .logout {
        text-align: right;
        margin-right: 20px;
    }

    .text {
        margin-top: 5px;
    }
</style>
