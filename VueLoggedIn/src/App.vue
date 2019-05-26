<template>
  <div id="app">
    <UserPanel/>
  </div>

</template>

<script>
/* eslint-disable */
import UserPanel from './components/UserPanel.vue';
import axios from 'axios';

export default {
  name: 'app',
  components: {
    UserPanel
  },

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  background-color: #eee;
}
</style>
