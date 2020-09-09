<template>
    <div>
        <div id ="positiveRev"> 
            <div class = "revitem" v-for="review in positiveList" :key="review.name">
                <div>
                <div class ="revName">
                    {{ review.name }} 
                </div>    
                    <StarRating class="starR" v-model="review.score" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.score}} / 10
                
                </div>
                <br>
                <div class="revContent">
                    {{ review.content }}
                </div>
            </div>
        </div>

        <div id ="negativeRev">
            <div class = "revitem" v-for="review in negativeList" :key="review.name">
                <div>
                <div class ="revName">
                    {{ review.name }}
                </div> <StarRating class="starR" v-model="review.score" v-bind:max-rating="10" :star-size="starsize" :show-rating="F" :read-only="true"/> {{ review.score }} / 10
                </div>
                <br>
                <div>
                    {{ review.content }}
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import StarRating from 'vue-star-rating';
export default {
    components: {
        StarRating
    },
    data() {
        return {
            F: false,
            starsize: 20,

            positiveList: [
                {name: '박지훈' , content: '정말 재미있는 영화 입니다.', score: 7},
                {name: '박현수' , content: '감동적인 영화 입니다. 시간 가는 줄 모르고 시청했습니다.', score: 10},
                {name: '허진기' , content: '굿굿굿 베리굿 입니다.', score: 8}
            
            
            
            
            ],

            negativeList: [
                {name: '박지훈' , content: '정말 재미없는 영화 입니다.', score: 3},
                {name: '박현수' , content: '감동도 없고 시간을 버리는 영화입니다.', score: 4},
                {name: '허진기' , content: '밷밷밷 베리배드 입니다.' , score: 2}
            ],
        }
    }
}
</script>

<style scoped>
    #positiveRev{
        float: left;
        width : 48%;
    }

    #negativeRev{
        float: right;
        width: 48%;
    }

    .revName{
        font-size: 20px;
        float: left;
    }

    .revitem{
        border-bottom: 1px solid black;
        margin-top: 10px;
        padding: 10px;
    }
    
    .starR{
        float:left;
        margin-left: 10px;
        margin-right: 10px;
    }

    .revContent{
    }
</style>