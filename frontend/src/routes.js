import main from './views/main.vue'
import FeedDetail from './views/feed/FeedDetail.vue'


export default [
 
    {
        path : '/',
        name : 'main',
        component : main
    },


    {
        path : '/FeedDetail',
        name : 'FeedDetail',
        component : FeedDetail
    },

  
]