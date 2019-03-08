<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>Puissance 4</h1>

    <div class="row">
      <div class="col-md-4 player player1"></div>

      <table class=" col-md-4 table">
        <tbody>
          <tr v-for="(row, rowIndex) in grid" :key="rowIndex">
            <td v-for="(col, colIndex) in row" :key=" colIndex">
              <span class="content" v-if="col != '_'">{{ col }}</span>
            </td>
          </tr>
        </tbody>
      </table>

      <div class=" col-md-4 player player2"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "app",
  data() {
    return {
      grid: [[]]
    };
  },
  mounted() {
    axios.get("http://localhost:8085").then(response => {
      this.grid = response.data;
    });
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.player1 {
  border: 1px solid yellow;
}

.player2 {
  border: 1px solid red;
}

.table {
  width: 35%;
  margin: auto;
  border-left: 1px solid black;
  border-right: 1px solid black;
  border-bottom: 1px solid black;
}

.table td {
  border: none;
  border-left: 1px solid black;
  border-right: 1px solid black;
  text-align: center;
  width: 3.5%;
  position: relative;
}

.table td:after {
  content: "";
  display: block;
  margin-top: 100%;
}

.table td .content {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: gold;
}
</style>
