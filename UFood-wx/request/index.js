// 同时发送异步代码的数量
let ajaxTimes = 0;
export const request=(params)=>{
    ajaxTimes++;
    // 加载中图标
    wx.showLoading({
        title: "加载中~ ('▿')...",
        mask: true,
    });
    // 定义公共的url地址
    // const baseUrl = "https://ufood.yizhiteamo.com";
    const baseUrl = "http://localhost:8082";
    return new Promise((resolve, rejects)=>{
        wx.request({
            ...params,
            url: baseUrl + params.url,
            success:(result)=>{
                resolve(result);
            },
            fail:(err)=>{
                rejects(err);
            },
            complete:()=>{
                ajaxTimes--;
                // 当所有异步请求结束触发
                if(ajaxTimes === 0){
                    // 关闭正在等待的图标
                    setTimeout(function (){
                        wx.hideLoading()
                    }, 495)
                }

                
            }
        })
    })
}