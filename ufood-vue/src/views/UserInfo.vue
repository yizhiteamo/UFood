<template>
  <div>
    <el-card class="el-card" style="width: 500px">
      <el-form label-width="80px" size="small">
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="changeInfo">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "UserInfo",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    this.request.get("/user/getUserByName/" + this.user.username).then(res => {
      if (res.code === '200') {
        this.form = res.data
      }
    })
  },
  methods: {
    changeInfo() {
      this.request.post("/user/saveOrUpdateUser", this.form )
          .then(res => {
            if(res) {
              this.$message.success("保存成功 ٩( 'ω' )و")
              this.$router.push("/home")
            } else {
              this.$message.error("保存失败 (ᗜˬᗜ)")
            }
          })
    },
  }
}
</script>

<style>

.el-card {
  min-width: 380px;
  margin-right: 20px;
  transition: all .5s;
}
.el-card:hover{
  margin-top: -5px;
}

</style>
