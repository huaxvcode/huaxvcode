<template>
  <div class="index">
    <el-container style="height: 100%;">
      <Aside :is-collapse="!asideExist"/>
      <el-container style="height: 100%" >
        <el-header style="text-align: right; font-size: 12px">
          <Head @isShowAside="isShowAside"/>
        </el-header>

        <el-main style="height: 100%" class="main">
          <Main/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.el-header {
  color: #333;
  line-height: 60px;
  border-bottom: 1px solid #eee;
}

.index {
  height: 100%;
  position: relative;
}
</style>

<script>
import Aside from "@/components/Aside.vue";
import Head from "@/components/Head.vue";
import Main from "@/components/Main.vue";

export default {
  components: {
    Aside,
    Head,
    Main,
  },
  data() {
    return {
      asideExist: true,
    }
  },
  methods: {
    isShowAside(status) {
      this.asideExist = status;
    }
  }
};
</script>