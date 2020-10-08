import main from './views/main.vue'
import FeedDetail from './views/feed/FeedDetail.vue'
import Signup from './views/user/Signup.vue'
import Qna from './views/qna/Qna.vue'
import Home from './views/home.vue'
import ActorDetail from './views/actor/ActorDetail.vue'
import Mypage from './views/user/Mypage.vue'
import Search from './views/search/Search.vue'
import MovieKorea from './views/Detailhome/MovieKorea.vue'
import Error from './views/error/Error.vue'
import Movieforeign from './views/Detailhome/Movieforeign.vue'

export default [
 
    {
        path : '/',
        name : 'main',
        component : main
    },


    {
        path : '/FeedDetail/:movieId',
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
    {
        path : '/home',
        name : 'Home',
        component : Home
    }, 
    {
        path : '/ActorDetail/:personId',
        name : 'ActorDetail',
        component : ActorDetail
    },
    {
        path : '/Mypage',
        name : 'Mypage',
        component : Mypage
    },
    {
        path: '/search/:keyword',
        name: 'Search',
        component : Search
    },
    {
        path: '/koreanmovie',
        name: 'MovieKorea',
        component : MovieKorea
    },
    {
        path: '*',
        name: 'Error',
        component: Error
    },
    {
        path: '/Movieforeign',
        name: 'Movieforeign',
        component : Movieforeign
    },
  
]