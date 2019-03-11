<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <h1>Puissance 4</h1>
    <h3 v-if="winner != null">{{ winner }} won !</h3>

    <div class="row">
      <div class="col-md-4 player player1">
        <h2>PLAYER1</h2>
        <input type="number" v-model="player1Column">
        <button
          v-on:click="play('player1')"
          :disabled="player1Column < 1 || player1Column > 7 || winner != null"
        >Play !</button>
      </div>

      <table class="col-md-4 table">
        <tbody v-if="grid != null">
          <tr v-for="(row, rowIndex) in grid" :key="rowIndex">
            <td v-for="(col, colIndex) in row" :key=" colIndex">
              <span class="content" v-if="col != 0" :class="col == '1' ? 'yellow' : 'red'"></span>
            </td>
          </tr>
        </tbody>
      </table>

      <div class="col-md-4 player player2">
        <h2>PLAYER2</h2>
        <input type="number" v-model="player2Column">
        <button
          v-on:click="play('player2')"
          :disabled="player2Column < 1 || player2Column > 7 || winner != null"
        >Play !</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { error } from "util";

/**
 * @typedef {Object} Data The data used
 * @property {string[][] | undefined} grid The grid
 * @property {number} player1Column The player1 chosed column
 * @property {number} player2Column The player2 chosed column
 * @property {string | undefined} winner The name of the winner
 */
export default {
  name: "app",
  /**
   * @returns {Data} The data
   */
  data() {
    return {
      grid: undefined,
      player1Column: 0,
      player2Column: 0,
      winner: undefined
    };
  },
  methods: {
    play: function(player) {
      let data = {
        player: player.toUpperCase(),
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
          this.grid = response.data.grid;

          if (null != response.data.winner) {
            this.winner = response.data.winner;
          }
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
