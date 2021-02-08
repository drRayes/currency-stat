<template>
  <div id="app">
  <h1>Currency stat</h1>
  <div>
      <div class="tab">
        <button @click="tab = 1">Currency list</button>
        <button @click="tab = 2">Converter</button>
      </div>
      <div class="tabcontent" v-if="tab === 1"><CurrencyList/></div>
      <div class="tabcontent" v-if="tab === 2"><Converter/></div>
    </div>
  </div>
</template>

<script>
import CurrencyList from './components/CurrencyList.vue'
import Converter from './components/Converter.vue'

export default {
  name: 'App',
  components: { CurrencyList, Converter },
  data () {
    return {
      tab: 1
    }
  }
}
</script>

<style>

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}

/* Style the buttons that are used to open the tab content */
.tab button {
  background-color: inherit;
  float: centr;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  text-align: center;
  padding: 6px 12px;
  border: 1px solid #ccc;
  border-top: none;
}
h1 {
  text-align: center;
}
</style>
