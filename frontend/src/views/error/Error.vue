<template>
    <div class="container" id="error-view">
        <h1 v-text="httpStatus[status].title"></h1>
        <hr>
        <i class="fas fa-exclamation-triangle"></i>
        <h2 v-for="msg in httpStatus[status].message" :key="msg" v-text="msg"></h2>
        <div class="back" @click="goBack">
            <i class="fas fa-arrow-circle-left"></i>
            <p>뒤로</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Error',
    props: {
        status: {
            default: "404"
        }
    },
    created() {
        this.$emit('sidebar')
        this.status = this.$route.query.status
    },
    data() {
        return {
            httpStatus: {
                400: {
                    message: ["잘못된 요청입니다.", "재시도하거나 문의해주세요."],
                    title: "400 Bad Request"
                },
                401: {
                    message: ["로그인이 필요한 서비스입니다."],
                    title: "401 Unauthorized"
                },
                403: {
                    message: ["접근권한이 없습니다."],
                    title: "403 Forbidden"
                },
                404: {
                    message: ["해당하는 페이지를 찾을 수 없습니다."],
                    title: "404 Not Found"
                },
                500: {
                    message: ["죄송합니다. 서버오류로 인해 사용할 수 없습니다."],
                    title: "500 Internal Server Error"
                },
                unknown: {
                    message: ["알 수 없는 오류입니다.", "재시도하거나 문의해주세요"],
                    title: "Unkown Error"
                }
            }
        }
    },
    methods: {
        goBack() {
            this.$router.go(-2)
        }
    }
}
</script>

<style>
    #error-view {
        height: 90vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color:aliceblue;
    }
    #error-view i.fa-exclamation-triangle {
        font-size: 10rem;
        margin-bottom: 50px;
    }
    #error-view h2 {
        font-size: 1.5rem;
    }
    #error-view i.fa-arrow-circle-left {
        margin-top: 30px;
        font-size: 5rem;
    }
    #error-view p {
        font-size: 2rem;
    }
    #error-view .back {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    #error-view .back:hover {
        cursor: pointer;
        color: rgb(236,128,116);
    }
    @media (min-width: 768px) {
        #error-view h2 {
            font-size: 2rem;
        }
    }
</style>