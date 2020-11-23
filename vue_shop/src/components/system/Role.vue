<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>角色管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card>
      <!-- 搜索框的位置 -->
      <el-row :gutter="10">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addRole">添加角色</el-button>
        </el-col>
      </el-row>

      <el-row :gutter="20" class="cardRow">
        <el-col :span="16">
          <el-card>
            <div slot="header">
              <span>角色列表</span>
            </div>
            <div class="roleTable">
              <el-table
                :data="RoleList"
                style="width: 100%"
                fit
                stripe
                size="mini"
              >
                <el-table-column
                  label="序号"
                  align="center"
                  type="index"
                  width="50dp"
                >
                </el-table-column>
                <el-table-column label="名称" align="center" prop="name">
                </el-table-column>
                <el-table-column
                  label="数据权限"
                  align="center"
                  prop="dataScope"
                  width="80dp"
                >
                </el-table-column>
                <el-table-column
                  label="角色级别"
                  align="center"
                  prop="level"
                  width="80dp"
                >
                </el-table-column>
                <el-table-column label="描述" align="center" prop="level">
                </el-table-column>
                <el-table-column
                  label="创建日期"
                  align="center"
                  prop="createTime"
                >
                </el-table-column>
                <el-table-column label="操作" align="center" width="150dp">
                  <template>
                    <el-button type="primary" icon="el-icon-edit"></el-button>
                    <el-button type="danger" icon="el-icon-delete"></el-button>
                  </template>
                </el-table-column>
              </el-table>

              <!-- 分页区域 -->

              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="RoleQueryParams.pageNum"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="RoleQueryParams.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
              >
              </el-pagination>
            </div>
          </el-card>
        </el-col>
        <el-col :span="8" class="menuCol">
          <el-card>
            <div slot="header">
              <span>菜单分配 </span>
              <el-button
                style="float: right;display: flex;align-items: center"
                type="primary"
                >保存</el-button
              >
            </div>
            <div>
              <el-tree
                :props="props"
                :load="loadNode"
                lazy
                show-checkbox
                @check-change="handleCheckChange"
              >
              </el-tree>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      RoleList: [],
      RoleQueryParams: {
        pageSize: 2,
        pageNum: 1,
        query: 2
      },
      total: 1,
      props: {
        label: 'title',
        children: 'zones'
      },
      menuList: [],
      level1: [],
      prop: {
        title: ''
      }
    }
  },
  created() {
    this.getRoleList()
    this.getMenuList()
  },
  methods: {
    addRole() {},
    async getRoleList() {
      const { data: res } = await this.$http.get('api/role', {
        params: this.RoleQueryParams
      })

      // console.log(res)

      if (res.code !== 200) {
        return this.$message.error('获取用户权限数据失败！')
      }

      var that = this
      this.RoleList = res.data.map(function(params) {
        that.total = params.total
        params.createTime = that
          .$dateFormat(params.createTime)
          .format('YYYY-MM-DD HH:mm:ss')
        return params
      })
    },
    handleSizeChange(newSize) {
      this.RoleQueryParams.pageSize = newSize
      this.getRoleList()
    },
    handleCurrentChange(newPage) {
      this.RoleQueryParams.pageNum = newPage
      this.getRoleList()
    },
    handleCheckChange() {},
    loadNode(node, resolve) {
      console.log(node)
      console.log(resolve)

      if (node.level === 0) {
        this.findAreaByParentId(resolve)

        // console.log(this.level1)
        // return resolve(this.level1)
        // return resolve([{ name: 'lemon' }, { name: 'jack' }])
      }
    },
    async getMenuList() {
      const { data: res } = await this.$http.get('/api/menus/build')
      // console.log(res)
      if (res.code !== 200) {
        return this.$message.error('获取菜单数据失败！')
      }

      this.menuList = res.data
      var that = this
      this.level1 = res.data.map(function(params) {
        that.prop.title = params.title
        return that.prop
      })
    },
    findAreaByParentId(resolve) {
      resolve(this.level1)
    }
  }
}
</script>

<style lang="less" scoped>
.box-card {
  margin-top: 30px;
  width: 480px;
}
.cardRow {
  margin-top: 30px;
}
.menuCol {
  float: right;
}
.roleTable {
  padding: 0;
  .el-table {
    margin: 0;
    padding: 0;
  }
}
</style>
