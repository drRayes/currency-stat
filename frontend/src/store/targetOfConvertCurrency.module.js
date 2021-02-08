export const targetOfConvertCurrency = {
  state: {
    targetOfConvertCurrency: {name: 'targetCurrency'}
  },
  getters: {
    getTargetOfConvertCurrency (state) {
      return state.targetOfConvertCurrency
    }
  },
  actions: {
    setTargetOfConvertCurrency (ctx, targetOfConvertCurrency) {
      ctx.commit('updateTargetOfConvertCurrency', targetOfConvertCurrency)
    }
  },
  mutations: {
    updateTargetOfConvertCurrency (state, targetOfConvertCurrency) {
      state.targetOfConvertCurrency = targetOfConvertCurrency
    }
  }
}
