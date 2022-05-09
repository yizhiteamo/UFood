import { request } from "../../request/index.js"
wx-Page({

    data:{
        // 轮播图数组
        swiperList:[],
        // 导航栏数组
        cateList:[],
        // 热销店铺数组
        popularShopList:[]
        
    },

    onLoad: function(options){
        this.getSwiperList();
        this.getCateList();
        this.getPopularShopList();
    },

    
    // 获取轮播图数据
    getSwiperList(){
        request({ url: "/goods/findIndexSwiper"})
        .then(result => {
            this.setData({
                swiperList: result.data
            })
        })
    },

    // 获取导航栏数组
    async getCateList(){
        const res = await request({ url:"/icon/getCateIcon"})
        this.setData({
            cateList: res.data
        })
    },

    // 获取热门店铺数据
    async getPopularShopList(){
        const res = await request({ url: "/shop/getPopularShopList"});
        const popularShopList = res.data;
        this.setData({
            popularShopList
        })
    },

    // 导航栏跳转事件
    handleCateNavi(e){
        const index = e.currentTarget.dataset.index;
        wx.setStorageSync("currentIndex", index);
          wx.switchTab({
            url: '/pages/category/category',
            success: function () {
              var page = getCurrentPages().pop();
              if (page == undefined || page == null) return;
              page.onLoad();
            }
          });
    }
})