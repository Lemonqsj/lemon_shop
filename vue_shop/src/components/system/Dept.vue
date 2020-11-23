<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>部门管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 搜索框区域 -->
    <el-card class="box-card">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary">添加部门</el-button>
        </el-col>
      </el-row>

      <el-table
        :data="DeptList"
        style="width: 100%"
        fit
        border
        stripe
        size="small"
        ref="treeRef"
        row-key="deptId"
        :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
        show-checkbox
        check-strictly
        node-key="id"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column label="名称" align="center" prop="name">
        </el-table-column>
        <el-table-column label="状态" align="center" prop="enabled">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.enabled"> </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="排序" align="center" prop="deptSort">
        </el-table-column>
        <el-table-column label="创建日期" align="center" prop="createTime">
        </el-table-column>
        <el-table-column label="操作" align="center" prop="label">
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
        :current-page="DeptQueryParm.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="DeptQueryParm.pageSize"
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
      DeptList: [],
      DeptQueryParm: {
        pageSize: 2,
        pageNum: 1,
        query: 2
      },
      total: 1
    }
  },
  created() {
    this.getDeptList()
  },
  methods: {
    async getDeptList() {
      const { data: res } = await this.$http.get('/api/dept', {
        params: this.DeptQueryParm
      })

      if (res.code !== 200) {
        return this.$message.error('获取部门列表失败！')
      }

      // this.DeptList = res.data

      var that = this
      this.DeptList = res.data.map(function(val) {
        // that.total = val.total
        val.createTime = that
          .$dateFormat(val.createTime)
          .format('YYYY-MM-DD HH:mm:ss')

        return val
      })

      // this.DeptList = this.fixDateFormate(res.data)
      console.log(res)
    },
    addDept() {
      console.log('add dept')
    },
    // fixDateFormate(val) {
    //   var that = this
    //   return val.map(function(params) {
    //     params.createTime = that
    //       .$dateFormat(params.createTime)
    //       .format('YYYY-MM-DD HH:mm:ss')

    //     if (params.children) {
    //       fixDateFormate(params.children)
    //     }
    //     return val
    //   })
    // },

    // checkChange(data) {
    //   const node = this.$refs.treeRef.getNode(data.id)
    //   this.setNode(node)
    //   console.log(node)
    // },
    // 递归设置子节点和父节点
    // setNode(node) {
    //   // this.setChildNode(node)

    //   if (node.checked) {
    //     if (node.childNodes && node.childNodes.length) {
    //       node.childNodes.forEach(item => {
    //         item.checked = true
    //         this.setChildNode(item)
    //       })
    //     }
    //   } else {
    //     if (node.childNodes && node.childNodes.length) {
    //       node.childNodes.forEach(item => {
    //         item.checked = false
    //         this.setChildNode(item)
    //       })
    //     }
    //   }
    //   // if (node.checked) {
    //   //   // 如果当前是选中checkbox,则递归设置父节点和父父节点++选中
    //   //   this.setParentNode(node)
    //   // } else {
    //   //   // 如果当前是取消选中checkbox,则递归设置子节点全部取消选中
    //   //   this.setChildNode(node)
    //   // }
    // },
    // 递归设置子节点全部取消选中
    // setChildNode(node) {
    //   if (node.childNodes && node.childNodes.length) {
    //     node.childNodes.forEach(item => {
    //       item.checked = true
    //       this.setChildNode(item)
    //     })
    //   }
    // },
    handleSelectionChange(node, change) {
      console.log(node.checked)
      console.log(node.length)

      // this.$refs.treeRef.toggleRowSelection(node.children[0])
      // var that = this
      if (node.length > 0) {
        node.map(function(item) {
          if (item.children && !item.children.checked) {
            console.log(item)
            console.log(item.children)
            // console.log(
            //   '------item.children[0].checked----' + item.children[0].checked
            // )
            // if (!item.children[0].checked) {
            // that.$refs.treeRef.toggleRowSelection(item.children[0])
            // }
            // that.$refs.treeRef.toggleRowSelection(item.children[0])
            // item.checked = false
            // this.setChildNode(item)
          }
        })
      }
      // if (node.childNodes && node.childNodes.length) {
      //   node.childNodes.forEach(item => {
      //     item.checked = true
      //     this.setChildNode(item)
      //   })
      // }
    },
    // handelSelectRow(row, column, event) {
    //   console.log(row)
    //   if (row.children && row.children.length > 0) {
    //     this.toggleRowSelection(row.children)
    //   }
    // },
    // toggleRowSelection(rows) {
    //   // if (判断表格是否有数据) {
    //   for (let i = 0; i < rows.length; i++) {
    //     const row = rows[i]
    //     this.$refs.treeRef.toggleRowSelection(row)
    //   }
    //   // }
    // },
    // 每页显示的数量改变的时候调用
    handleSizeChange(newSize) {
      this.DeptQueryParm.pageSize = newSize
      this.getDeptList()
    },
    // 当前页码改变的时候调用
    handleCurrentChange(newPage) {
      this.DeptQueryParm.pageNum = newPage
      this.getDeptList()
    }
  }
}
</script>

<style lang="less" scoped></style>
