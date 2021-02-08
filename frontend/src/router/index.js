import Vue from 'vue'
import Router from 'vue-router'
import CurrencyList from '@/components/CurrencyList'
import Converter from '@/components/Converter'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CurrencyList',
      component: CurrencyList
    },
    {
      path: '/Converter',
      name: 'Converter',
      component: Converter
    }
  ]
})
