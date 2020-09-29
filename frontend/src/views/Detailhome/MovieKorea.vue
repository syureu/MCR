<template>
    <div id="container">
         <div class="location" id="home">
            <MovieItemList :name="recommandMovie.title" :movies1="recommandMovie.Movie1" :movies2="recommandMovie.Movie2"/>
            <MovieItemList2 :name="recommandMovie2.title" :movies1="recommandMovie2.Movie1" :movies2="recommandMovie2.Movie2"/>
            <MovieItemList1 :name="recommandMovie1.title" :movies1="recommandMovie1.Movie1" :movies2="recommandMovie1.Movie2"/>
            </div>
    </div>
</template>
<script>

import MovieItemList from '@/components/Movie/MovieItemList.vue'
import MovieItemList1 from '@/components/Movie/MovieItemList1.vue'
import MovieItemList2 from '@/components/Movie/MovieItemList2.vue'

export default {
    data(){
       return {
            recommandMovie:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
            recommandMovie1:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
             
            recommandMovie2:{
                title:"",
                Movie1:[],
                Movie2:[],

            },
            
        }
    },
    created(){

    },
    components:{
        MovieItemList,
        MovieItemList1,
        MovieItemList2,
    }
}
</script>