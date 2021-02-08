import Vue from 'vue'
import Vuex from 'vuex'

import { currencyList } from './currencyList.module.js'
import { currentCurrency } from './currentCurrency.module.js'
import { targetOfConvertCurrency } from './targetOfConvertCurrency.module.js'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    currencyList, currentCurrency, targetOfConvertCurrency
  }
})
