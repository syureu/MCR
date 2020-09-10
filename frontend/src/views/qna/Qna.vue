<template>
    <div>
        <br><br><br>
        <div id="boardTitle">QnA<hr></div>

        <div id="qnawrite">
            <button id ="qnaBtn">질문하기</button>
        </div>
        <div id="tab">
            <table id="tabitem">
                <tr>
                    <th>No</th>
                    <th>Title</th>
                    <th></th>
                    <th>Date</th>
                    
                </tr>
                <tr v-for="qnaitem in qnaList" :key="qnaitem.qnano">
                    <td>{{ qnaitem.qnano }}</td>
                    <td><button id="qnatitle">{{ qnaitem.qtitle }}</button></td>
                    <td></td>
                    <td>{{ qnaitem.qdate }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            qnaList: [
                {
                    qnano: 1,
                    qtitle: '질문 있습니다.',
                    qdate: '2020-09-10',
                    qcontent: '사실 질문 없습니다',
                    acotent : '장난 치지 마세요',
                    adate: '2020-09-10',
                },
                {
                    qnano: 2,
                    qtitle: '질문1',
                    qdate: '2020-09-10',
                    qcontent: '질문2',
                    acotent : '',
                    adate: '2020-09-10',
                },


            ],
        }
    }
}
</script>

<style scoped>
    #boardTitle{
        margin-left : 10px;
        font-size : 50px;
    }

    #qnaBtn {
        float: right;
        margin-right : 10px;
    }
    #tab {
        clear:both;
    }
    #qnawrite{
        width: 100%;
    }

    #tabitem {
        margin-left : auto;
        margin-right : auto;
    }

    #qnatitle{
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        border: 0;
        outline: 0;
        background-color: white;
    }

    #qnatitle:hover {
        color: red;
    }

</style>