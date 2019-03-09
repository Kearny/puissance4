<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>Puissance 4</h1>

    <div class="row">
      <div class="col-md-4 player player1">
        <h2>Player 1</h2>
        <input type="number" v-model="player1Column">
        <button
          v-on:click="play('player1')"
          :disabled="player1Column < 1 || player1Column > 7"
        >Play !</button>
      </div>

      <table class="col-md-4 table">
        <tbody>
          <tr v-for="(row, rowIndex) in grid" :key="rowIndex">
            <td v-for="(col, colIndex) in row" :key=" colIndex">
              <span class="content" v-if="col != '_'" :class="col == 'y' ? 'yellow' : 'red'"></span>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="col-md-4 player player2">
        <h2>Player 2</h2>
        <input type="number" v-model="player2Column">
        <button
          v-on:click="play('player2')"
          :disabled="player2Column < 1 || player2Column > 7"
        >Play !</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { error } from "util";

export default {
  name: "app",
  data() {
    return {
      grid: [[]],
      player1Column: 0,
      player2Column: 0
    };
  },
  methods: {
    play: function(player) {
      let data = {
        player: player === "player1" ? 1 : 2,
        column:
          player === "player1"
            ? parseInt(this.$data.player1Column)
            : parseInt(this.$data.player2Column)
      };

      let headers = {
        "Content-Type": "application/json"
      };

      axios
        .post("http://localhost:8085", data, { headers: headers })
        .then(response => {
          this.grid = response.data;
        });
    }
  },
  mounted() {
    axios.get("http://localhost:8085").then(
      response => {
        this.grid = response.data;
      },
      error => {
        console.error(error);
      }
    );
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
  border: 10px solid gold;
}

.player2 {
  border: 10px solid red;
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
  border-radius: 100%;
}

.yellow {
  background-color: gold;
}

.red {
  background-color: red;
}
</style>
