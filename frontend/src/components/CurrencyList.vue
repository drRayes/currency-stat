<template>
  <div class='wrapper'>
    <div v-for="(currency, key) in getCurrency" :key="currency.id">
      <div class='box' :id="key">
        <p>{{currency.name}}<p>
        <p>{{currency.charCode}}</p>
        <p>Nominal = {{currency.nominal}}</p>
        <p>Current value = {{currency.currentValue}}</p>
        <p>Previous value = {{currency.previousValue}}</p>
      </div>
    </div>
  </div>
</template>
<script>
import {mapGetters} from 'vuex'
export default {
  name: 'CurrencyList',
  data () {
    return {}
  },
  computed: mapGetters(['getCurrency']),
  mounted: function () {
    this.$store.dispatch('fetchCurrency')
  }
}
</script>
<style scoped>
body {
  margin: 40px;
}

.wrapper {
  display: grid;
  grid-template-columns: 33% 33% 33%;
  grid-gap: 10px;
  background-color: #fff;
  color: #444;
}

.box {
  background-color: #444;
  color: #fff;
  border-radius: 5px;
  padding: 20px;
  font-size: 150%;
  height: 270px
}
p {
  color: rgb(242, 162, 12);
}
</style>
