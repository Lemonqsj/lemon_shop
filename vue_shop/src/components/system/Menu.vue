<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
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
          <el-button type="primary" @click="addMenu">添加菜单</el-button>
        </el-col>
      </el-row>

      <el-table
        :data="MenuList"
        style="width: 100%"
        fit
        border
        stripe
        size="small"
        ref="treeRef"
        row-key="menuId"
        :tree-props="treeProps"
        highlight-current
        render-content
        show-checkbox
        check-strictly
        node-key="id"
        lazy
        :load="loadNode"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="菜单标题" align="center" prop="title">
        </el-table-column>
        <el-table-column label="图标" align="center" prop="icon" width="65">
        </el-table-column>
        <el-table-column label="排序" align="center" prop="menuSort" width="55">
        </el-table-column>
        <el-table-column label="权限标识" align="center" prop="permission">
        </el-table-column>
        <el-table-column label="组件路径" align="center" prop="path">
        </el-table-column>
        <el-table-column label="外链" align="center" prop="iframe" width="55">
        </el-table-column>
        <el-table-column label="缓存" align="center" prop="cache" width="55">
        </el-table-column>
        <el-table-column label="可见" align="center" prop="hidden" width="55">
        </el-table-column>
        <el-table-column label="创建日期" align="center" prop="createTime">
        </el-table-column>
        <el-table-column label="操作" align="center" prop="title">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  created() {
    this.getMenuList()
  },
  data() {
    return {
      MenuList: [],
      total: 1,
      pid: 0,
      treeProps: {
        children: 'children',
        label: 'label',
        hasChildren: 'hasChildren'
      },
      childrenData: []
    }
  },
  methods: {
    async getMenuList() {
      const { data: res } = await this.$http.get('/api/menus?pid=' + null)
      // console.log(res)
      if (res.code !== 200) {
        return this.$message.error('获取菜单数据失败！')
      }

      var that = this
      this.MenuList = res.data.map(function(params) {
        params.hidden = params.hidden ? '是' : '否'
        params.iframe = params.iframe ? '是' : '否'
        params.cache = params.cache ? '是' : '否'
        params.createTime = that
          .$dateFormat(params.createTime)
          .format('YYYY-MM-DD HH:mm:ss')
        params.children = []
        params.hasChildren = true
        return params
      })
      console.log(this.MenuList)
    },
    addMenu() {
      console.log('add menu')
    },
    handleSelectionChange(node, change) {
      console.log(node.checked)
      console.log(node.length)
    },
    loadNode(tree, node, resolve) {
      console.log('-----children tree--' + tree)
      console.log('-----children node--' + node)
      // const { data: res } = await this.$http.get(
      //   '/api/menus?pid=' + node.menuId
      // )
      // console.log('-----children data--' + res)
    },
    renderContent(h, { node, data, store }) {
      console.log(h)
      console.log(node)
    }
  }
}
</script>

<style lang="less" scoped></style>
