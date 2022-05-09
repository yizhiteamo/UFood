import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'UFoodManage',
    component: () => import('../views/UFoodManage.vue'),
    redirect: "/login",
    children: [
      {path: 'home', name: '管理员', meta: {requireAuth: true}, component: () => import('../views/UserAdmin.vue')},
      {path: 'userInfo', name: '个人信息', meta: {requireAuth: true}, component: () => import('../views/UserInfo.vue')},
      {path: 'shopAdmin', name: '店铺管理', meta: {requireAuth: true}, component: () => import('../views/ShopAdmin.vue')},
      {path: 'goodsAdmin', name: '商品管理', meta: {requireAuth: true}, component: () => import('../views/GoodsAdmin.vue')},
      {path: 'orderAdmin', name: '订单管理', meta: {requireAuth: true}, component: () => import('../views/OrderAdmin.vue')},
      {path: 'miniProgramIndex', name: '轮播图管理', meta: {requireAuth: true}, component: () => import('../views/SwiperAdmin.vue')},
    ]
  },
  {
    path: '/login',
    name: "Login",
    component: () => import('../views/Login')
  },
  {
    path: '/register',
    name: "Register",
    component: () => import('../views/Register')
  },
  {
    path: '/retrieve',
    name: 'Retrieve',
    component: () => import('../views/Retrieve')
  },
  {
    path: '/pageNotFound',
    name: "404",
    component:() => import('../views/PageNotFound')
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {

  // 判断当前路由是否存在
  if (to.matched.length === 0) {
    from.name ? next({name: from.name}) : next('/pageNotFound')
  } else {
    next()
  }

  // 判断当前路由是否需要用户登录权限
  if (to.meta.requireAuth) {
    // 判断拦截器token是否生效
    if (sessionStorage.getItem("token") == 'isLogin') {
      // 放行路由
      next()
    } else {
      Vue.prototype.$message.warning('请先登录哦(ᗜˬᗜ)')
      // 跳转到登录界面
      next({path:'/login'})
    }
  } else {
    // 不需要用户登录权限
    next()
  }

})

export default router
