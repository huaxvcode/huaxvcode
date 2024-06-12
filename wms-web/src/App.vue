<template>
  <div id="app">
    <Index/>
  </div>
</template>

<script>
import Index from "@/components/index.vue";
export default {
  name: 'App',
  components: {
    Index
  }
}
</script>

<style>
  #app {
    height: 100%;
    width: 100%;
    position: absolute;
  }
</style>
