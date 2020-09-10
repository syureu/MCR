<template>
    <div class="main-container">
        <div class="location" id="home">
            <MovieItemList :movies="LikeMovie"/>
        </div>
    </div>
</template>
<script>
import MovieItemList from '@/components/movie/MovieItemList.vue'

export default {
    name : 'Home',
    components:{
        MovieItemList
    },
    data(){
        return {
            LikeMovie:[],
            
        }
    },
    methods(){

    }
}
</script>