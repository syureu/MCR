<template>
    <div class="box">
        <MovieItem v-for="movie in movies" :key="movie.m_no" :movie="movie" />
    </div>
</template>
<script>

import MovieItem from './MovieItem'
export default {
    name : 'MovieItemList',
    components:{
        MovieItem
    },
    props:{
        movies : Array,
    }
    
}
</script>
<style scoped>
    .box {
  display: grid;
  grid-gap: 20px;
  grid-template-columns: repeat(6, minmax(100px, 1fr));
}


</style>