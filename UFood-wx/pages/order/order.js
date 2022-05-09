import { request } from "../../request/index.js";
import regeneratorRuntime, { async } from "../../lib/runtime/runtime";
Page({

   data: {
    //  订单信息
    orders: [],
  },
  userInfo:{},

  
  /**
   * 生命周期函数--监听页面显示
   */
  
   onShow() {
    this.checkLogin();
    this.getOrders();
  },

  checkLogin(){
    const userInfo = wx.getStorageSync("userInfo");
    // 判断缓存中是否有用户信息 / 用户是否登录
    if(Object.keys(userInfo).length === 0) {
      wx.showModal({
        title: '提示(ᗜˬᗜ)',
        content: '请先登录哦​',
        success (res) {
          if (res.confirm) {
            // 用户点击确定 用户登录
            wx.getUserProfile({
              desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
              success: (res) => {
                const userInfo = res.userInfo;
                wx.setStorageSync("userInfo", userInfo);
                wx.navigateTo({
                  url: '/pages/order/order'
                });
              }
            })
          } else if (res.cancel) {
            // 用户点击取消 跳转回之前的页面
            wx.navigateBack({
              delta: 1
            });
          }
        }
      })
    }
  },

  // 获取用户对应的订单信息
  async getOrders(){
    const user =  wx.getStorageSync("userInfo");

    let userTemp = [];
    userTemp.push(user.nickName);

    let userInfo = userTemp.join("")
    const res = await request({ url:"/order/findOrderByUser", data: {userInfo}});
    this.setData({
      orders: res.data
    })
  },

  // 用户点击退餐按钮
  handleRefund(e) {
    wx.showModal({
      content: '请问是否要退餐？',
      success: res=> {
        if (res.confirm) {
          this.refundOrder(e.currentTarget.dataset.id)
        } else if (res.cancel) {
          // console.log("点击取消")
        }
      }
    })
  },

  // 用户退餐功能
  async refundOrder(id) {
    await request({ url: "/order/orderRefund/" + id, method: 'POST'})
    this.getOrders()
  }
    

})