<template>
  <button @click="$emit('input', id)" :class="[active, 'tab']" style="vertical-align:middle">
    {{ label }}
  </button>
</template>

<script>
export default {
  props: {
    id: Number,
    label: String,
    value: Number
  },
  computed: {
    active() {
      return this.value === this.id ? 'active' : false
    }
  }
}
</script>

<style>
.tab {
    display: inline-block;
  background-color: black;
  border: 0;
  outline: 0;
  color: #FFFFFF;
  text-align: center;
  font-size: 15px;
  padding: 20px;
  width: 100px;
  height: 40px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}
.tab span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.tab:focus{
  border: 0;
  outline: 0;
}

.tab:hover {
  background: black;
  text-decoration: underline;
  color: red;
}
.active {
  background: black;
}
</style>