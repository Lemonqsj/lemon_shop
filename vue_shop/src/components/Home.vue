<template>
  <el-container>
    <el-header>
      <div>
        <img src="../assets/kc_header.png" />
        <span>后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出</el-button>
    </el-header>
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="toggle_collapse_flag ? '64px' : '200px'">
        <div class="toggle_button" @click="toggle_collapse">|||</div>
        <!-- 侧边栏区域 -->
        <el-menu
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          unique-opened
          :collapse="toggle_collapse_flag"
          :collapse-transition="false"
          router
          :default-active="active_menu"
        >
          <!-- 一级菜单栏区域 -->
          <el-submenu
            :index="item.menuId + ''"
            v-for="item in menuList"
            :key="item.menuId"
          >
            <!-- 一级菜单栏的模版区域 -->
            <template slot="title">
              <!-- 图标 -->
              <i class="el-icon-menu"></i>
              <!-- 文本 -->
              <span>{{ item.title }}</span>
            </template>
            <!-- 二级菜单栏 -->
            <el-menu-item
              :index="subItem.path + ''"
              v-for="subItem in item.children"
              :key="subItem.menuId"
              @click="save_active_menu(subItem.path + '')"
            >
              <template slot="title">
                <!-- 图标 -->
                <i class="el-icon-menu"></i>
                <!-- 文本 -->
                <span>{{ subItem.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList: [],
      toggle_collapse_flag: false,
      active_menu: ''
    }
  },
  methods: {
    logout() {
      window.sessionStorage.clear()
      this.$router.push('/login')
    },
    async getMenuList() {
      const { data: res } = await this.$http.get('/api/menus/build')
      console.log(res)
      if (res.code !== 200) {
        return this.$message.error('获取菜单数据失败！')
      }

      this.menuList = res.data
    },
    // 点击按钮实现菜单的折叠与展开
    toggle_collapse() {
      this.toggle_collapse_flag = !this.toggle_collapse_flag
    },
    save_active_menu(val) {
      window.sessionStorage.setItem('activeMenu', val)
      this.active_menu = window.sessionStorage.getItem('activeMenu')
    }
  },
  created() {
    this.getMenuList()
    this.active_menu = window.sessionStorage.getItem('activeMenu')
  }
}
</script>

<style lang="less" scoped>
.el-container {
  height: 100%;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #fff;
  font-size: 30px;
  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
    img {
      height: 50px;
    }
  }
}
.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: none;
  }
}
.el-main {
  background-color: #eaedf1;
}
.toggle_button {
  background-color: #333744;
  font-size: 18px;
  color: #fff;
  line-height: 24px;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
