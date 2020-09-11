<template>
    <div id="content">
    <h1 id="movies" v-text="name"></h1>
    <div class="box">
        <MovieItem v-for="movie in movies" :key="movie.m_no" :movie="movie" />
    </div>
    </div>
</template>
<script>

import MovieItem from './MovieItem'
export default {
    components:{
        MovieItem
    },
    props:{
        name : String,
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

.content h1 {
  
  padding-top: 60px;  
}


</style>