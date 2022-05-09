// pages/collect/index.js
Page({
  collect:[],
  data: {},

  onShow(){
    const collect = wx.getStorageSync("collect")||[];
    this.setData({
      collect
    })
  },

  // 标题点击事件 从子组件传递过来
  handleTabsItemChange(e){
    // 1.获取被点击的标题索引
    const {index} = e.detail;
    // 2.修改原数组
    let {tabs} = this.data;
    tabs.forEach((v,i)=>i===index?v.isActive=true:v.isActive=false);
    // 3.赋值到data中
    this.setData({
      tabs
    })
  },
})