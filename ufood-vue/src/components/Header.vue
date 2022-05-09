<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1">
<!--      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 20px" @click="collapse"></span>-->
    </div>

    <el-dropdown style="width: 100px; cursor: pointer">
      <div style="display: inline-block">
        <span>{{ user.username }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="$router.push('/userInfo')">个人信息</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>

</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    // collapse: Boolean
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    collapse() {
      this.$emit("asideCollapse")
    },
    logout() {
      sessionStorage.removeItem("token")
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  }
}
</script>

<style scoped>

</style>
