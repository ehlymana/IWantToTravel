<template>
  <div class="main">
    <Header/>

    <!--<Navigation/>-->
    <div class="navigation">
      <img alt="Back" src="../assets/back.png" class="icon back">
      <div class="adduser">
          <img alt="Add user" src="../assets/user.png" class="icon">
      </div>
      <div class="text">Back</div>
      <div class="adduser">
          <div class="text">Add user</div>
      </div>
  </div>
  <!-- end of navigation -->

  <div class="table">
    <div class="firstrow cell">Username</div>
    <div class="firstrow cell">First name</div>
    <div class="firstrow cell">Last name</div>
    <div class="firstrow cell">Role</div>
    <div class="firstrow cell">Edit</div>
    <div class="firstrow cell">Delete</div>

    <div class="cell">danytargy</div>
    <div class="cell">Daenerys</div>
    <div class="cell">Targaryen</div>
    <div class="cell">User</div>
    <div class="cell"><img alt="Edit" src="../assets/edit.png" class="tableIcon"></div>
    <div class="cell"><img alt="Delete" src="../assets/delete.png" class="tableIcon"></div>
  </div>

  </div>
</template>



<script>
import Header from './Header.vue';

export default {
    name: 'AllUsers',
    components: {
        Header
    },
    users: [],

    mounted() {
  // axios.get("localhost:3000/users") //promijeniti adresu
  //     .then(res => {
  //       console.log(res.data) /* eslint-disable-line no-console */
  //       users = res.data;
  //     })
  //     .catch(err => {
  //       console.log(err) /* eslint-disable-line no-console */
  //     });
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

    .adduser {
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
