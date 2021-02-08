import axios from 'axios'
const API_URL = '/api'
export const currencyList = {
  state: {
    currency: []
  },
  getters: {
    getCurrencyList (state) {
      return state.currency
    }
  },
  actions: {
    fetchCurrency (ctx) {
      axios
        .get(API_URL + '/loadCurrency')
        .then(response => {
          ctx.commit('updateCurrency', response.data)
        })
    }
  },
  mutations: {
    updateCurrency (state, currency) {
      state.currency = currency
    }
  }
}
