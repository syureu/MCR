<template>
  <div id="app">
    <ul class="mt-5 pt-5">
      <li style="color: white;" v-for="item in items.boxOfficeResult.dailyBoxOfficeList.dailyBoxOffice" v-bind:key="item.movieNm">
        {{ item.movieNm._text }}
      </li>
    </ul>
  </div>
</template>
<script>
var convert = require('xml-js')
export default {
  name: 'App',
  data () {
    return {
      items: []
    }
  },
  created () {
    this.$http.get('http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=424e2b88162e9154ee09a8a4d8e5bf3a&targetDt=20200925')
      .then((response) => {
        var xml = response.data
        console.log(response.data)
        var json = convert.xml2json(xml, { compact: true })
        this.items = JSON.parse(json)
      })
    console.log(this.items)
  }
}
</script>