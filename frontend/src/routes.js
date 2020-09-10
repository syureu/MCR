import main from './views/main.vue'
import FeedDetail from './views/feed/FeedDetail.vue'
import Signup from './views/user/Signup.vue'
import Qna from './views/qna/Qna.vue'

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

    {
        path : '/Qna',
        name : 'Qna',
        component : Qna
    },
    {
        path : '/Signup',
        name : 'Signup',
        component : Signup
    },
  
]