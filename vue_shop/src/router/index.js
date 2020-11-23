import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import Welcome from '../components/Welcom.vue'
import User from '../components/user/User.vue'
import Job from '../components/system/Job.vue'
import Dept from '../components/system/Dept.vue'
import Role from '../components/system/Role.vue'
import Menu from '../components/system/Menu.vue'
import OnlineUser from '../components/monitor/OnlineUser.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: Login },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      { path: '/welcome', component: Welcome },
      { path: '/user', component: User },
      { path: '/job', component: Job },
      { path: '/dept', component: Dept },
      { path: '/role', component: Role },
      { path: '/menu', component: Menu },
      { path: '/online', component: OnlineUser }
    ]
  }
]
// router.push、 router.replace 和 router.go的区别

// router.push

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    return next()
  }

  const token = window.sessionStorage.getItem('token')

  if (!token) {
    return next('/login')
  }

  next()
})
export default router
