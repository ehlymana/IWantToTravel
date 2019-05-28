<template>
  <div class="main">
    <Header/>

    <div class="view">

        <img alt="Hotel" src="../assets/hotel.png" class="icon">
        <br>
        <form @submit.prevent="getFormValues">
        
            <input class="field" type="text" name="hname" placeholder="Hotel name" v-model="hotel.name" />
            <br>
            <input class="field" type="text" name="hdesc" placeholder="Description" v-model="hotel.description" />
            <br>
            <input class="field" type="text" name="haddress" placeholder="Address" v-model="hotel.address" />
            <br>
            <input class="field" type="text" name="hcity" placeholder="City" v-model="hotel.city" />
            <br>
            <input class="field" type="number" name="hlongitude" placeholder="Longitude" v-model="hotel.longitude"/>
            <br>
            <input class="field" type="number" name="hlatitude" placeholder="Latitude" v-model="hotel.latitude" />
            <br>
            <div class="rooms">
                <input class="row" type="number" name="hbeds" placeholder="Number of beds" />
                <input class="row" type="number" name="hquantity" placeholder="Quantity" />
                <img alt="Add" src="../assets/plus.png" class="plus">
            </div>
            <br>

            <select class="field">
                <option value="jon">Jon Snow</option>
                <option value="arya">Arya Stark</option>
                <option value="sansa">Sansa Stark</option>
                <option value="bran">King of the Six Kingdoms</option>
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
  name: 'Hotel',
  components: {
    Header
  },
  data() {
      //treba id poslati kad se klikne na red u tabeli, kad je new, ne salje se, 
      //u mounted dodati da se iz propova dobavi id!
      return {
        hotel: 
          {
            name: "hotel 1",
            description: "Desc 1",
            address: "Address 1",
            city: "City 1",
            longitude: 23,
            latitude: 44
          },
        id: null
        
      }
    },
    methods: {
        getFormValues (submitEvent) {
            this.hotel = {
                name: submitEvent.target.elements.hname.value,
                description: submitEvent.target.elements.hdesc.value,
                address: submitEvent.target.elements.haddress.value,
                city: submitEvent.target.elements.hcity.value,
                longitude: submitEvent.target.elements.hlongitude.value,
                latitude : submitEvent.target.elements.hlatitude.value
            }

            if (this.id!==null) {
                console.log(this.hotel)
                //-----------------------------INSERT
                // axios.post(`localhost:3000/users/${this.id}`, this.hotel) //promijeniti adresu
                // .then(res => {
                //   console.log(res.data) /* eslint-disable-line no-console */
                // })
                // .catch(err => {
                //   console.log(err) /* eslint-disable-line no-console */
                // });
            } else {
                console.log(this.hotel)
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
