export default {
    tokenKeyword: 'Bearer',
    getTokenHeader() {
        return `${this.tokenKeyword} ${this.$session.get('jwstoken')}`
    }
}