# 食优预约就餐小程序介绍

基于 Java 的小程序，小程序端使用小程序的 MINA 框架和 Java 的 Spring Boot 框架搭建，后台数据管理端使用 Vue2 框架、Element-UI 组件搭建前端可视化模块，并使用 Spring Boot 提供前后端数据交互的接口。
PS: 服务器过期了，就不展示线上版本了_(:з」∠)_

## 目录

- [上手指南](#上手指南)
- [程序截图](#程序截图)
- [部署](#部署)
- [版权声明](#版权声明)

### 上手指南

本程序在 Java 11 环境下开发完成，建议在 Java 11 环境下运行本程序。
小程序模块建议在<a href="https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html">微信开发者工具</a>1.06版本下运行。
后台模块建议在 Vue 2 与 Element-UI 组件下运行。

### 程序截图

#### 小程序模块截图
![image](https://user-images.githubusercontent.com/45223220/175796447-384ae51f-87ab-45e0-b4fc-fae6329ff8c0.png)

![image](https://user-images.githubusercontent.com/45223220/175797049-3586d8f2-1029-4282-92a8-77e71778b8b0.png)

![image](https://user-images.githubusercontent.com/45223220/175797104-515d9bfa-900d-4bcf-9eb3-760aee2071d2.png)

![image](https://user-images.githubusercontent.com/45223220/175797117-6619a8c5-31d8-4b42-8c68-7b541aa343da.png)

#### 后台模块截图
![image](https://user-images.githubusercontent.com/45223220/175797319-96f3ba78-cb81-4100-9ea6-094d0119f95a.png)

![image](https://user-images.githubusercontent.com/45223220/175797327-9fbc5a6e-f38d-47a3-b5db-4ebf81ecccdd.png)

### 部署

小程序模块和后台模块可在 request.js 文件中，按照本地端口的设置修改对应的端口配置。
因小程序部署分包限制大小，该程序中的大部分图片均上传至<a href="https://cloud.tencent.com/product/cos">腾讯云COS图床</a>。
若要部署到 nignx 上，请将小程序的后台端口设置为443端口，并保证当前域名配置了SSL证书。

### 版权声明

该项目依据<a href="https://github.com/shaojintian/Best_README_template/blob/master/LICENSE.txt"> MIT 授权许可</a>。
