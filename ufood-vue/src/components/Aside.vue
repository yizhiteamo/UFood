<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="true"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">食优后台管理系统</b>
    </div>

    <el-menu-item index="/home">
      <i class="el-icon-house"></i>
      <span slot="title">食优首页</span>
    </el-menu-item>

    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>

        <el-menu-item index="/shopAdmin">
          <i class="el-icon-s-custom"></i>
          <span slot="title">商家管理</span>
        </el-menu-item>

      <el-menu-item index="/goodsAdmin">
        <i class="el-icon-s-custom"></i>
        <span slot="title">菜品管理</span>
      </el-menu-item>

      <el-menu-item index="/orderAdmin">
        <i class="el-icon-s-custom"></i>
        <span slot="title">订单管理</span>
      </el-menu-item>

      <el-menu-item index="/miniProgramIndex">
        <i class="el-icon-s-custom"></i>
        <span slot="title">小程序首页管理</span>
      </el-menu-item>
    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  },
}
</script>

<style scoped>

</style>