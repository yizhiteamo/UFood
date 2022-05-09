<template>

  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px; padding-right: 5px" placeholder="请输入商家名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width: 200px; padding-right: 5px" placeholder="请输入商家分类" suffix-icon="el-icon-position" v-model="category"></el-input>
      <el-input style="width: 200px; padding-right: 5px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="location"></el-input>
      <el-button type="primary" @click="pageLoad">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='好的'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除批量这些数据吗？"
          @confirm="handleDelBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="shopNo" label="店铺编号" width="140" align="center"></el-table-column>
      <el-table-column prop="name" label="店铺名称" width="140" align="center"></el-table-column>
      <el-table-column prop="category" label="分类" width="140" align="center"></el-table-column>
      <el-table-column prop="location" label="地址" align="center"></el-table-column>
      <el-table-column prop="shopIcon" label="商家icon" align="center"></el-table-column>
      <el-table-column prop="popular" label="热门店铺" align="center" :formatter="isPopular"></el-table-column>

      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='好的'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除这个店铺数据吗？"
              @confirm="handleDel(scope.row.shopNo)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="店铺信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="店铺名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店铺类别">
          <el-input v-model="form.category" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店铺地址">
          <el-input v-model="form.location" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店铺简介">
          <el-input v-model="form.brief" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="店铺icon">
          <el-input v-model="form.shopIcon" autocomplete="off"></el-input>
        </el-form-item>
        <el-radio-group v-model="form.popular" align="center" size="mini">
          <el-radio-button :label="true">热门店铺</el-radio-button>
          <el-radio-button :label="false">非热门店铺</el-radio-button>
        </el-radio-group>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSaveUser">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
export default {
  name: "ShopAdmin",
  data() {
    return {
      // 页面数据
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 2,
      name:"",
      category: "",
      location: "",
      brief: "",
      shopIcon: '',
      shopNo: '',
      popular: '',
      // 新增弹窗
      dialogFormVisible: false,
      form: {},
      // 多选数组
      multipleSelection: [],
      headerBg: 'headerBg',
    }
  },
  created() {
    this.pageLoad()
  },
  methods: {
    // 加载店铺信息分页数据
    pageLoad() {
      //  请求店铺信息分页查询数据
      this.request.get("/shop/findByPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          category: this.category,
          location: this.location,
          popular: this.popular
        }
      }).then(res =>{
        this.tableData = res.records
        this.total = res.total})
    },

    // 点击重置按钮事件 重置搜索栏 及 页面数据
    reset() {
      this.name = ""
      this.category = ""
      this.location = ""
      this.pageLoad()
    },

    // 点击新增按钮事件
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },

    // 点击新增按钮中的确定
    handleSaveUser() {
      this.request.post("/shop/saveOrUpdateShop", this.form )
          .then(res => {
            if(res) {
              this.$message.success("保存成功 ٩( 'ω' )و")
              this.dialogFormVisible = false
              this.pageLoad()
            } else {
              this.$message.error("保存失败 (ᗜˬᗜ)")
            }
          })
    },

    // 点击编辑按钮事件
    handleEdit(row){
      // 避免编辑未确定数据就发生改变
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },

    // 点击删除按钮事件
    handleDel(shopNo){
      this.request.delete("/shop/delShopById/" + shopNo)
          .then(res => {
            if(res) {
              this.$message.success("删除成功 ٩( 'ω' )و")
              this.pageLoad()
            } else {
              this.$message.error("删除失败 (ᗜˬᗜ)")
            }
          })
    },

    // 点击全选事件
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },

    // 点击批量删除按钮事件
    handleDelBatch(){
      // 将对象数组转化成 id 数组
      let ids = this.multipleSelection.map(v => v.shopNo)
      this.request.post("/shop/delShopByBatch/", ids)
          .then(res => {
            if(res) {
              this.$message.success("批量删除成功 ٩( 'ω' )و")
              this.pageLoad()
            } else {
              this.$message.error("批量删除失败 (ᗜˬᗜ)")
            }
          })
    },

    // 点击修改页容量事件
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.pageLoad()
    },

    // 点击修改起始页事件
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.pageLoad()
    },

    // table显示布尔值
    isPopular(row) {
      let popular = row.popular;
      if (popular) {
        return "热门店铺"
      } else {
        return "非热门店铺"
      }
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
