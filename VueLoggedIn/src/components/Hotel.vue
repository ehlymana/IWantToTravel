<template>
  <div class="main">
    <Header/>

    <div class="view">

        <img alt="Hotel" src="../assets/hotel.png" class="icon">
        <br>
        <form>
        
            <input class="field" type="text" name="hname" placeholder="Hotel name" />
            <br>
            <input class="field" type="text" name="hdesc" placeholder="Description" />
            <br>
            <input class="field" type="text" name="haddress" placeholder="Address" />
            <br>
            <input class="field" type="text" name="hcity" placeholder="City" />
            <br>
            <input class="field" type="number" name="hlongitude" placeholder="Longitude" />
            <br>
            <input class="field" type="number" name="hlatitude" placeholder="Latitude" />
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
