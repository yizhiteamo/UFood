import { request } from "../../request/index.js";
import regeneratorRuntime from "../../lib/runtime/runtime";
Page({

  data: {
    // 左侧菜单数据
    leftMenuList:[],
    // 右侧商家数据
    rightContent:[],
    // 被点击的左侧菜单
    currentIndex:0,
    // 右侧菜单距顶部的距离
    scrollTop:0
  },
  Cates:[],


  onLoad: function () {
    const currentIndex = wx.getStorageSync("currentIndex");
    // 使用本地缓存 避免数据量过大加载过久
    const Cates = wx.getStorageSync("cates");
    // 判断
    if(!Cates){
      // 不存在本地缓存 发生请求数据
      this.getCates();
    }else{
      // 有旧数据 自定义缓存过期时间
      if(Date.now()-Cates.time>1000*10){
        // 重新发送请求
        this.getCates();
      }else{
        // 可以使用旧数据
        this.Cates=Cates.data;
        let leftMenuList=this.Cates.map(v=>v.category);
        let rightContent=this.Cates[currentIndex].shops;
        this.setData({
          currentIndex,
          leftMenuList,
          rightContent,
        })
      }
    }
  },

  // 获取分类数据
  async getCates(){
    // 使用es7的async await 来发送请求
    const res = await request({ url: "/shop/getShopAndCateList" });
      this.Cates=res.data.shopList;
      // 把接口中的数据存入到本地缓存中
      wx.setStorageSync("cates", {time:Date.now(),data:this.Cates});
      // 构造左侧大菜单
      let leftMenuList=this.Cates.map(v=>v.category);
      // 构造右侧的商品数据
      let rightContent=this.Cates[0].shops;
      this.setData({
        leftMenuList,
        rightContent
      })
  },

  // 左侧菜单的点击事件
  handleItemTap(e){
    const {index} = e.currentTarget.dataset;
    let rightContent=this.Cates[index].shops;
    this.setData({
      currentIndex:index,
      rightContent,
      // 重新设置右侧scroll-view的高度
      screenTop: 0
    })
  }

})