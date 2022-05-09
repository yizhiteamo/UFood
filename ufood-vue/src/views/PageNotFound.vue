<template>
  <div style="text-align: center">
    <el-image style="opacity: 0.9" :src="require('../../public/image/404.jpg')"></el-image>
    <el-footer></el-footer>
    <el-footer>
      <el-button type="primary" style="width: 100px" round @click="$router.push('/home')">返回首页</el-button>
    </el-footer>
  </div>
</template>

<script>
export default {
  data() {
    return {}
  }
}
</script>

<style>

</style>
