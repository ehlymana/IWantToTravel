<template>
  <div class="main">
    <Header/>

    <div class="view">

        <img alt="User" src="../assets/user.png" class="icon">
        <br>
        <form>
        
            <input class="field" type="text" name="firstname" placeholder="First name" />
            <br>
            <input class="field" type="text" name="lastname" placeholder="Last name" />
            <br>
            <input class="field" type="text" name="username" placeholder="Username" />
            <br>
            <input class="field" type="text" name="email" placeholder="Email" />
            <br>
            <input class="field" type="password" name="pass" placeholder="Password" />
            <br>
            <input class="field" type="password" name="confirmpass" placeholder="Confirm password" />
            <br>

            <select class="field">
                <option value="user">User</option>
                <option value="supervizor">Supervizor</option>
                <option value="admin">Admin</option>
            </select>
            <br>

            <button class="btn" type="submit">Save</button>
        
        </form>

    </div>

  </div>
</template>



<script>
import Header from './Header.vue';

export default {
  name: 'User',
  components: {
    Header
  },
}
</script>




<style scoped>
    .view {
        border: solid 3px black;
        margin: auto 350px;
        background-color: #eee;
        text-align: center;
        margin-top: 20px;
    }
    .field {
        margin: 10px;
        height: 30px;
        width: 300px;
    }
    .row {
        width: 120px;
        margin: 10px;
        height: 30px;
    }
    .btn {
        width: 200px;
        height: 70px;
        margin: 25px;
    }
    .icon {
        width: 100px;
        height: 100px;
    }
    .plus {
        width: 30px;
        height: 30px;
    }
</style>
