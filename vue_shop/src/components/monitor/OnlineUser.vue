<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统监控</el-breadcrumb-item>
      <el-breadcrumb-item>在线用户</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片视图区域 -->
    <el-card class="box-card">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="QueryOnlineUser"
            >查询用户</el-button
          >
        </el-col>
      </el-row>

      <!-- 表格数据区域 -->
      <el-table
        :data="OnlineUserList"
        style="width: 100%"
        fit
        border
        stripe
        size="small"
      >
        <el-table-column label="序号" align="center" type="index" width="50dp">
        </el-table-column>
        <el-table-column label="用户名" align="center" prop="userName">
        </el-table-column>
        <el-table-column label="用户昵称" align="center" prop="nickName">
        </el-table-column>
        <el-table-column label="部门" align="center" prop="dept">
        </el-table-column>
        <el-table-column label="登录ip" align="center" prop="ip">
        </el-table-column>
        <el-table-column label="登录地点" align="center" prop="address">
        </el-table-column>
        <el-table-column label="浏览器" align="center" prop="browser">
        </el-table-column>
        <el-table-column label="登录时间" align="center" prop="loginTime">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template>
            <el-popover placement="top" width="160" v-model="popVisible">
              <p>这是一段内容这是一段内容确定删除吗？</p>
              <div style="text-align: right; margin: 0">
                <el-button size="mini" type="text" @click="popVisible = false"
                  >取消</el-button
                >
                <el-button
                  type="primary"
                  size="mini"
                  @click="popVisible = false"
                  >确定</el-button
                >
              </div>
              <el-button type="text" @click="tickUser" slot="reference"
                >强退</el-button
              >
              <!-- <el-button slot="reference">删除</el-button> -->
            </el-popover>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区域 -->

      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="OnlineQueryParams.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="OnlineQueryParams.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      OnlineUserList: [],
      OnlineQueryParams: {
        pageSize: 2,
        pageNum: 1,
        query: 2
      },
      total: 0,
      popVisible: false
    }
  },
  methods: {
    QueryOnlineUser() {},
    async getOnlineUserList() {
      const { data: res } = await this.$http.get('/auth/online', {
        params: this.OnlineQueryParams
      })

      if (res.code !== 200) {
        return this.$message.error('获取在线用户列表失败！')
      }
      var that = this
      this.OnlineUserList = res.data.map(function(params) {
        that.total = params.total
        params.loginTime = that
          .$dateFormat(params.loginTime)
          .format('YYYY-MM-DD HH:mm:ss')
        return params
      })
      console.log(res)
    },
    handleSizeChange(newSize) {
      this.OnlineQueryParams.pageSize = newSize
      this.QueryOnlineUser()
    },
    handleCurrentChange(newPage) {
      this.OnlineQueryParams.pageNum = newPage
      this.QueryOnlineUser()
    },
    tickUser() {
      // this.popVisible = !this.popVisible
      // console.log(this.popVisible)
    }
  },
  created() {
    this.getOnlineUserList()
  }
}
</script>

<style lang="less" scoped></style>
