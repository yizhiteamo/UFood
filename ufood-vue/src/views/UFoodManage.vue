<template>
  <el-container style="min-height: 100vh">

    <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgba(0 21 41 35);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"></Aside>
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc">
        <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse"></Header>
      </el-header>

      <el-main>
        <!--页面的子路由会在 router-view 中展示 -->
        <router-view></router-view>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>

// 引入侧边栏
import Aside from "../components/Aside";

// 引入头部组件
import Header from "../components/Header";

export default {
  name: 'Home',
  data() {
    return {
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  components: {Aside, Header},
  methods: {
    // 点击收缩按钮触发
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },


  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
