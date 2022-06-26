import { request } from "../../request/index.js";
import regeneratorRuntime from "../../lib/runtime/runtime";
Page({
  data: {
    // 商品列表
    goodsList:[],
    // 店铺信息
    shopInfo:{}
  },
  // 接口需要的参数
  QueryParams:{
    shop_no:"",
    pageNum:1,
    pageSize:10
  },
  // 总页数 默认值
  totalPages: 1,

  onLoad: function (options) {
    this.QueryParams.shop_no = options.shop_no;
    this.getShop();
    this.getGoodsList();
  },

  // 重新获取商家信息
  async getShop(){
    const res = await request({ url:"/shop/getShopByNO",data:this.QueryParams});
    this.setData({
      shopInfo: res.data[0]
    })
  },
  
  // 获取商品列表数据
  async getGoodsList(){
    const res = await request({ url:"/goods/findGoodsByPage",data:this.QueryParams});
    // 获取商品总条数
    const total = res.data.total;
    // 计算商品总页数
    this.totalPages = Math.ceil(total / this.QueryParams.pagesize);
    const goodsList = res.data.pageData;
    this.setData({
      // 拼接数组
      goodsList: [...this.data.goodsList, ...goodsList]
    })
  },

  // 页面上滑 滚动条触底事件
  onReachBottom(){
    if(this.QueryParams.pageNum >= this.totalPages){
      wx.showToast({title: '我也是有底线的 >_<'});
    }else{
      this.QueryParams.pageNum++;
      this.getGoodsList();
    }
  },
})
