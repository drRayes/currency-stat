<template>
  <div>
    <h2>Converter</h2>
    <div class='wrapper'>
    <div class='box'>
      <h3>From</h3>
      <select v-model='currentCurrency'>
          <option v-for="(currency, key) in getCurrencyList" :value='currency' :key='key'>
            {{currency.name}}
          </option>
      </select>
      <div>
        <p>{{this.currentCurrency.name}}</p>
        <p>Amount</p>
        <input v-model='amount'>
      </div>
    </div>
    <div class='box'>
      <h3>To</h3>
      <select v-model='targetCurrency'>
        <option v-for="(currency, key) in getCurrencyList" :value='currency' :key='key'>
         {{currency.name}}
        </option>
      </select>
      <div>
        <p>{{this.targetCurrency.name}}</p>
        <p>{{this.result}}</p>
        <button @click='convertCurrency'>CONVERT</button>
      </div>
    </div>
    </div>
  </div>
</template>
<script>
import {mapGetters} from 'vuex'

export default {
  name: 'Converter',
  data () {
    return {
      currentCurrency: {
        name: ''
      },
      targetCurrency: {
        name: ''
      },
      amount: 1,
      result: 1
    }
  },
  computed: mapGetters(['getCurrencyList', 'getCurrentCurrency', 'getTargetOfConvertCurrency']),
  methods: {
    convertCurrency () {
      this.result = ((this.currentCurrency.currentValue / this.currentCurrency.nominal) /
        (this.targetCurrency.currentValue / this.targetCurrency.nominal)) * this.amount
    }
  }
}
</script>
<style>
.wrapper {
  display: grid;
  grid-template-columns: 50% 50%;
  grid-gap: 10px;
  background-color: #fff;
  color: #444;
}

.box {
  height: 300px;
  background-color: #444;
  color: #fff;
  border-radius: 5px;
  padding: 20px;
  font-size: 150%;
}
button {
  background: #e3e3e3;
  border: 1px solid #bbb;
  border-radius: 3px;
  -webkit-box-shadow: inset 0 0 1px 1px #f6f6f6;
  box-shadow: inset 0 0 1px 1px #f6f6f6;
  color: #333;
  font: bold 12px/1 "helvetica neue", helvetica, arial, sans-serif;
  padding: 8px 0 9px;
  text-align: center;
  text-shadow: 0 1px 0 #fff;
  width: 150px;
}

button:hover {
  background: #d9d9d9;
  -webkit-box-shadow: inset 0 0 1px 1px #eaeaea;
  box-shadow: inset 0 0 1px 1px #eaeaea;
  color: #222;
  cursor: pointer;
}
button:active {
  background: #d0d0d0;
  -webkit-box-shadow: inset 0 0 1px 1px #e3e3e3;
  box-shadow: inset 0 0 1px 1px #e3e3e3;
  color: #000;
}
</style>
