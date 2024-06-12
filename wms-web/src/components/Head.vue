<script>
export default {
  data() {
    return {
      open: true,
    }
  },
  methods: {
    toUser() {
      console.log('to_user')
    },
    logout() {
      console.log('logout')
    },
    isShowAside() {
      this.open = !this.open;
      this.$emit('isShowAside', this.open);
    }
  },
}
</script>

<template>
  <div class="head">
    <span class="menu" @click="isShowAside">
      <i class="el-icon-s-fold" style="font-size: 25px" v-show="open"></i>
      <i class="el-icon-s-unfold" style="font-size: 25px" v-show="!open"></i>
    </span>
    <el-dropdown>
      <span>王小虎</span>
      <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<style scoped>
.head {
  width: 100%;
  position: relative;
}
span {
  margin-right: 10px;
}
.menu i {
  text-align: left;
  line-height: 60px;
}
.menu {
  position: absolute;
  left: 0;
}
</style>