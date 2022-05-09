<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px; opacity: 0.9">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" round  autocomplete="off" @click="login">登录</el-button>
          <el-button type="warning" size="small" round  autocomplete="off" @click="$router.push('/register')">注册</el-button>
          <el-button type="danger" size="small" round  autocomplete="off" @click="beforeTo">找回密码</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 30 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    // 点击登录按钮
    login() {
      this.$refs['userForm'].validate((valid) => {
        // 表单校验合法
        if (valid) {
          this.request.post("/user/login", this.user).then(res => {
            if(res.code === '200') {
              // 存储拦截器的token值
              sessionStorage.setItem("token", 'isLogin')
              // 存储用户信息到浏览器
              localStorage.setItem("user", JSON.stringify(res.data))
              this.$router.push("/home")
              this.$message.success("登录成功")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    },
    // 点击找回密码
    beforeTo() {
      if (this.user.username === '') {
        this.$notify({
          title: '跳转失败',
          message: "输入用户名再去重置密码吧",
          type: 'error'
        });
      } else {
        this.$router.push({
          name: 'Retrieve',
          query: {
            username: this.user.username,
          }
        })
      }
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: url("../../public/image/login.jpg");
  overflow: hidden;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
  width: 100%;
  position: fixed;
}
</style>
