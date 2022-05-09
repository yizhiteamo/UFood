import { request, requestPut } from "../../request/index.js";
import regeneratorRuntime from "../../lib/runtime/runtime";
Page({
  
  /**
   * 页面的初始数据
   */
   data: {
    goodsObj:{},
    // 商品是否被收藏
    isCollect:false
  },
  // 商品对象
  goodsInfo:{},


  onShow: function(){
    let pages = getCurrentPages();
    let currentPage = pages[pages.length - 1];
    let options = currentPage.options;
    const {goods_no} = options;
    this.getGoodsDetail(goods_no);
  },

  // 获取商品详情数据
  async getGoodsDetail(goods_no){
    const res = await request({ url:"/goods/findGoodsByGoodsNO", data:{goods_no} });
    const temp = res.data
    const goodsObj = temp[0];
    this.goodsInfo = goodsObj;
    // 1 获取缓存中的商品收藏的数组
    let collect = wx.getStorageSync("collect") || [];
    // 2 判断当前商品是否被收藏
    let isCollect = collect.some(v => v.goods_no === this.goodsInfo.goods_no);
    this.setData({
      goodsObj:{
        swiperList: goodsObj.swiperList,
        name: goodsObj.name,
        price: goodsObj.price,
        collected: goodsObj.collected,
        introduce: goodsObj.introduce,
      },
      isCollect
    })
  },

  // 轮播图 点击预览大图功能
  handlePreviewImage(e){
    // 1. 先构造要预览的图片数组
    const urls = this.goodsInfo.swiperList.map(v => v.swiper_url);
    // 2. 点击事件触发后 接收传递过来的图片url
    const idx = e.currentTarget.dataset.index;
    wx.previewImage({
      current: urls[idx],
      urls,
    });
  },

  // 点击加入购物车
  handleCartAdd(){
    // 1.获取缓存中的购物车 数组
    let cart = wx.getStorageSync("cart")||[];
    // 2.判断 商品对象是否存在于购物车数组中
    let index = cart.findIndex(v=>v.goods_no===this.goodsInfo.goods_no);
    if(index === -1){
      // 3.不存在 第一次添加
      this.goodsInfo.num=1;
      this.goodsInfo.checked=true;
      cart.push(this.goodsInfo);
    }else{
      // 4.已经存在购物车数据  执行 num++
      cart[index].num++;
    }
    // 5.把购物车重新添加回缓存中
    wx.setStorageSync("cart", cart);
    // 6.弹窗提示
    wx.showToast({
      title: '加入成功',
      icon: 'success',
      // 防止用户多次点击按钮
      mask: true,
    });
  },

    // 点击 商品收藏图标
    handleCollect(){
      let isCollect=false;
      // 1 获取缓存中的商品收藏数组
      let collect=wx.getStorageSync("collect")||[];
      // 2 判断该商品是否被收藏过
      let index=collect.findIndex(v=>v.goods_no===this.goodsInfo.goods_no);
      // 3 当index！=-1表示 已经收藏过 
      if(index!==-1){
        // 能找到 已经收藏过了  在数组中删除该商品
        collect.splice(index,1);
        isCollect=false;
        wx.showToast({
          title: '取消成功',
          icon: 'success',
          mask: true
        });
          
      }else{
        // 没有收藏过
        collect.push(this.goodsInfo);
        isCollect=true;
        wx.showToast({
          title: '收藏成功',
          icon: 'success',
          mask: true
        });
      }
      // 4 把数组存入到缓存中
      wx.setStorageSync("collect", collect);
      // 5 修改data中的属性  isCollect
      this.setData({
        isCollect
      })
    }

})