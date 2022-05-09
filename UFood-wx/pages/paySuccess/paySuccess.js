import { request } from "../../request/index.js";
import regeneratorRuntime from "../../lib/runtime/runtime";
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    // 获取购物车中的缓存
    const res = wx.getStorageSync("cart");
    // 定义新的空缓存
    let arrays = [];
    // 支付成功的订单
    let bill = [];
    // 给新缓存赋值
    for( var i = 0; i < res.length; i++){
      if(res[i].checked == false){
        arrays.push(res[i]);
      }else if(res[i].checked == true){
        bill.push(res[i]);
      }
    }
    // 传输订单信息
    wx.setStorageSync("orderBill", bill);
    // 覆盖之前的缓存数据
    wx.setStorageSync("cart", arrays);

    this.submitBill();
  },

  async submitBill(){
    let orderTemp = [];
    let userTemp = [];

    // 从缓存中获取相应数据
    const user = wx.getStorageSync("userInfo");
    const orderTime = wx.getStorageSync("orderTime");
    const orderBill = wx.getStorageSync("orderBill");

    // 依次将订单信息赋值 只保留名称
    for ( var i = 0; i < orderBill.length; i++) {
      orderTemp.push(orderBill[i].name)
    }

    // 将数组转换成字符串 使用join 
    let orderContent = orderTemp.join(",");
    userTemp.push(user.nickName);
    let userInfo = userTemp.join("")

    // 传参给后台 存储
    const res = await request({
      url: "/order/setOrder",
      method: 'POST',
      data:{ orderTime,userInfo,orderContent},
      header:{ "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"}
    })
    console.log(res)
  }

})