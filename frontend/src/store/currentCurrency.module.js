export const currentCurrency = {
  state: {
    currentCurrency: {name: 'currentCurrency'}
  },
  getters: {
    getCurrentCurrency (state) {
      return state.currentCurrency
    }
  },
  actions: {
    setCurrentCurrency (ctx, currency) {
      ctx.commit('updateCurrency', currency)
    }
  },
  mutations: {
    updateCurrency (state, currency) {
      state.currentCurrency = currency
    }
  }
}
