<template>
  <div class="main">
    <Header/>

    <!--<Navigation/>-->
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
  <!-- end of navigation -->

  <div class="table">
    <div class="cell firstrow">Hotel name</div>
    <div class="cell firstrow">Description</div>
    <div class="cell firstrow">Address</div>
    <div class="cell firstrow">City</div>
    <div class="cell firstrow">Edit</div>
    <div class="cell firstrow">Delete</div>

    <div class="cell">Saray</div>
    <div class="cell">#nekiOpis</div>
    <div class="cell">Zmaja od Bosne bb</div>
    <div class="cell">Sarajevo</div>
    <div class="cell"><img alt="Edit" src="../assets/edit.png" class="tableIcon"></div>
    <div class="cell"><img alt="Delete" src="../assets/delete.png" class="tableIcon"></div>
  </div>

  </div>
</template>



<script>
import Header from './Header.vue';

export default {
    name: 'AllHotels',
    components: {
        Header
    },
    hotels: [],

    mounted() {
  // axios.get("localhost:3000/people") //promijeniti adresu
  //     .then(res => {
  //       console.log(res.data) /* eslint-disable-line no-console */
  //       hotels = res.data;
  //     })
  //     .catch(err => {
  //       console.log(err) /* eslint-disable-line no-console */
  //     });
    this.hotels = [
      {
        name: "hotel 1",
        description: "Desc 1",
        address: "Address 1",
        city: "City 1"
      }
    ]
  }

}

</script>




<style scoped>
.cell {
  border: solid black 1px;
  padding-top: 15px;
}

.firstrow {
  background-color: red;
  padding-top: 10px;
}

.table {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  grid-auto-rows: minmax(50px, auto);
  margin: auto 150px;
  text-align: center;
  font-weight: 700;
}

.navigation {
        display: grid;
        grid-template-columns: repeat(2, 1fr);
        grid-auto-rows: minmax(50px, auto);
        background-color: #eee;
    }

    .icon {
        width: 30px;
        height: 30px;
        margin-top:20px;
    }

    .back {
        margin-left: 100px;
    }

    .addhotel {
        text-align: right;
        margin-right: 100px;
    }

    .text {
        margin-left: 100px;
    }

    .tableIcon {
        width: 30px;
        height:30px;
        padding-top: -20px;
    }
</style>
