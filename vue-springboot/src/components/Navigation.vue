<template>
  <div class="navigation">
    <img alt="Back" src="../assets/back.png" class="icon back">
    <div class="addhotel">
        <img alt="Add hotel" src="../assets/hotel.png" class="icon">
    </div>
    <div class="text">Back</div>
    <div class="addhotel">
        <div class="text">Add hotel</div>
    </div>
  </div>
</template>



<script>
export default {
  name: 'Navigation',
}
</script>




<style scoped>
    .navigation {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-auto-rows: minmax(50px, auto);
        background-color: #eee;
    }

    .icon {
        width: 50px;
        height: 50px;
    }

    .back {
        margin-left: 50px;
    }

    .addhotel {
        text-align: right;
        margin-right: 50px;
    }

    .text {
        margin-top: 5px;
    }
</style>
