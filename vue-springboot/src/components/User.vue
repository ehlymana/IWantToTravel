<template>
  <div class="main">
    <Header/>

    <div class="view">

        <img alt="User" src="../assets/user.png" class="icon">
        <br>
        <form @submit.prevent="getFormValues">
        
            <input class="field" type="text" name="firstname" placeholder="First name" v-model="user.firstName"/>
            <br>
            <input class="field" type="text" name="lastname" placeholder="Last name" v-model="user.lastName" />
            <br>
            <input class="field" type="text" name="username" placeholder="Username" v-model="user.username" />
            <br>
            <input class="field" type="text" name="email" placeholder="Email" v-model="user.email" />
            <br>
            <input class="field" type="password" name="pass" placeholder="Password" v-model="user.password" />
            <br>
            <input class="field" type="password" name="confirmpass" placeholder="Confirm password" v-model="user.confirmPassword" />
            <br>

            <select class="field" name="role" v-model="user.role">
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
  data() {
      return {
        user: 
          {
            firstName: "Daenerys",
            lastName: "Targaryen",
            username: "danytargy",
            email: "dany@gmail.com",
            password: 'formermotherOF3dragons',
            confirmPassword: 'formermotherOF3dragons',
            role: "user",
          },
          id: null
        
      }
    },
    methods: {
        getFormValues (submitEvent) {
            this.user = {
                firstName: submitEvent.target.elements.firstname.value,
                lastName: submitEvent.target.elements.lastname.value,
                username: submitEvent.target.elements.username.value,
                email: submitEvent.target.elements.email.value,
                password: submitEvent.target.elements.pass.value,
                confirmPassword : submitEvent.target.elements.confirmpass.value,
                role: submitEvent.target.elements.role.value
            }
            
            console.log(this.id)

            if (this.id!==null) {
                console.log(this.user)
                //-----------------------------INSERT
                // axios.post(`localhost:3000/users/${this.id}`, this.hotel) //promijeniti adresu
                // .then(res => {
                //   console.log(res.data) /* eslint-disable-line no-console */
                // })
                // .catch(err => {
                //   console.log(err) /* eslint-disable-line no-console */
                // });
            } else {
                console.log(this.user)
                // -----------------------------UPDATE
                // axios.put(`localhost:3000/users/${this.id}`, this.hotel) //promijeniti adresu
                // .then(res => {
                //   console.log(res.data) /* eslint-disable-line no-console */
                // })
                // .catch(err => {
                //   console.log(err) /* eslint-disable-line no-console */
                // });
            }

        }
    }
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
