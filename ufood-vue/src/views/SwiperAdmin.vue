<template>

  <div>

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
      <el-table-column prop="id" label="轮播图编号" width="140" align="center"></el-table-column>
      <el-table-column prop="swiperUrl" label="轮播图路径" align="center"></el-table-column>
      <el-table-column prop="goodsNo" label="所属的菜品编号" width="140" align="center"></el-table-column>
      <el-table-column prop="indexSwiper" label="是否为首页轮播图" width="140" :formatter="isIndex" align="center"></el-table-column>

      <el-table-column label="操作"  width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='好的'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除这个轮播图数据吗？"
              @confirm="handleDel(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="轮播图信息" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="80px" size="small">
        <el-form-item label="图片路径">
          <el-input v-model="form.swiperUrl" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜品编号">
          <el-input v-model="form.goodsNo" autocomplete="off"></el-input>
        </el-form-item>
        <el-radio-group v-model="form.indexSwiper" align="center" size="mini">
          <el-radio-button :label="true">首页轮播图</el-radio-button>
          <el-radio-button :label="false">普通轮播图</el-radio-button>
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
      swiperUrl: '',
      indexSwiper: '',
      id: '',
      goodsNo: '',
      // 新增弹窗
      dialogFormVisible: false,
      form: {},
      // 多选数组
      multipleSelection: [],
      headerBg: 'headerBg'
    }
  },
  created() {
    this.pageLoad()
  },
  methods: {

    // 加载店铺信息分页数据
    pageLoad() {
      //  请求店铺信息分页查询数据
      this.request.get("/swiper/findByPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          swiperUrl: this.swiperUrl,
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
      this.request.post("/swiper/saveOrUpdateSwiper", this.form )
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
    handleDel(id){
      this.request.delete("/swiper/delSwiperById/" + id)
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
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/swiper/delSwiperByBatch/", ids)
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

    //  table显示布尔值
    isIndex(row) {
      let indexSwiper = row.indexSwiper;
      if (indexSwiper) {
        return "首页轮播图"
      } else {
        return "普通轮播图"
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
