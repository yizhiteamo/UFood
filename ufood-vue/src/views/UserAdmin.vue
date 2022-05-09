<template>

  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px; padding: 3px" placeholder="请输入管理员名称"  suffix-icon="el-icon-search" v-model="username"></el-input>
      <el-input style="width: 200px; padding: 3px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"></el-input>
      <el-input style="width: 200px; padding: 3px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"></el-input>
      <el-button type="primary" @click="pageLoad">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="username" label="用户名" width="140" align="center"></el-table-column>
      <el-table-column prop="phone" label="电话" align="center"></el-table-column>
      <el-table-column prop="email" label="邮箱" align="center"></el-table-column>
      <el-table-column prop="address" label="地址" align="center"></el-table-column>
    </el-table>
  </div>

</template>

<script>
export default {
  name: "UserAdmin",
  data() {
    return {
      // 页面数据
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      username:"",
      email: "",
      address: "",
      phone:""
    }
  },
  created() {
    this.pageLoad()
  },
  methods: {

    // 加载用户分页数据
    pageLoad() {
      //  请求用户分页查询数据
      this.request.get("/user/findByPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address,
          phone: this.phone
        }
      }).then(res =>{
        this.tableData = res.data.records
        this.total = res.data.total})

    },

    // 点击重置按钮事件 重置搜索栏 及 页面数据
    reset() {
      this.username = ""
      this.email = ""
      this.address = ""
      this.pageLoad()
    },



    // 点击全选事件
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },

    // 点击批量删除按钮事件
    handleDelBatch(){
      // 将对象数组转化成 id 数组
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/user/delUserByBatch/", ids)
          .then(res => {
            if(res.data) {
              this.$message.success("批量删除成功 ٩( 'ω' )و")
              this.pageLoad()
            } else {
              this.$message.error("批量删除失败 (ᗜˬᗜ)")
            }
          })
    },

    // 点击修改页容量事件
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.pageLoad()
    },

    // 点击修改起始页事件
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.pageLoad()
    }

  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
