<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
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
          <el-button type="primary" @click="addUser">添加用户</el-button>
        </el-col>
      </el-row>

      <!-- 表格数据区域 -->
      <el-table
        :data="userList"
        style="width: 100%"
        fit
        border
        stripe
        size="small"
      >
        <el-table-column label="序号" align="center" type="index" width="50dp">
        </el-table-column>
        <el-table-column prop="user.username" label="用户名" align="center">
        </el-table-column>
        <el-table-column prop="user.username" label="昵称" align="center">
        </el-table-column>
        <el-table-column
          prop="user.gender"
          label="性别"
          align="center"
          width="80dp"
        >
        </el-table-column>
        <el-table-column prop="user.phone" label="电话" align="center">
        </el-table-column>
        <el-table-column prop="user.email" label="邮箱" align="center">
        </el-table-column>
        <el-table-column prop="address" label="部门" align="center">
        </el-table-column>
        <el-table-column label="状态" align="center" width="100dp">
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.user.enabled"
              @change="onUserStatusSwitch(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="user.createTime" label="创建日期" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
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
        :current-page="usersQueryParms.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="usersQueryParms.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalSize"
      >
      </el-pagination>

      <!-- 添加用户的表单 -->
      <el-dialog
        title="添加用户"
        :visible.sync="dialogUserAddVisible"
        @close="addUserDialogClose"
      >
        <el-form
          :model="user"
          ref="userRef"
          :rules="userRule"
          :label-width="dialogFormLabelWidth"
        >
          <el-row :gutter="15">
            <el-col :span="12">
              <el-form-item label="用户名" prop="username">
                <el-input v-model="user.username"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="电话" prop="phone">
                <el-input v-model="user.phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="12">
              <el-form-item label="昵称" prop="nikename">
                <el-input v-model="user.nikename"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="邮箱" prop="email">
                <el-input v-model="user.email"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="15">
            <el-col :span="12">
              <el-form-item label="部门">
                <el-select
                  v-model="user.dept"
                  placeholder="请选择"
                  class="dept-select"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="岗位">
                <el-select
                  v-model="user.job"
                  placeholder="请选择"
                  class="dept-select"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="15">
            <el-col :span="12">
              <el-form-item label="性别">
                <el-radio-group v-model="user.sex">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="状态">
                <el-radio-group v-model="user.enabled">
                  <el-radio label="1">激活</el-radio>
                  <el-radio label="0">禁用</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="15">
            <el-col :span="12">
              <el-form-item label="角色">
                <el-select
                  v-model="user.role"
                  placeholder="请选择"
                  size="medium"
                  class="role-select"
                >
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12"> </el-col>
          </el-row>
        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogUserAddVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitAddUserForm">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    var checkEmail = (rules, value, callback) => {
      const regEmail = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (regEmail.test(value)) {
        return callback()
      }
      callback(new Error('请输入合法的邮箱'))
    }
    var checkPhone = (rules, value, callback) => {
      const regMobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/
      if (regMobile.test(value)) {
        return callback()
      }
      callback(new Error('请输入合法的手机号'))
    }
    return {
      userList: [],
      totalSize: 2,
      usersQueryParms: {
        pageSize: 2,
        pageNum: 1,
        query: 2
      },
      dialogUserAddVisible: false,
      dialogFormLabelWidth: '70px',
      options: [
        {
          value: '选项1',
          label: '黄金糕'
        },
        {
          value: '选项2',
          label: '双皮奶'
        },
        {
          value: '选项3',
          label: '蚵仔煎'
        },
        {
          value: '选项4',
          label: '龙须面'
        },
        {
          value: '选项5',
          label: '北京烤鸭'
        }
      ],
      value: '',
      user: {
        username: '',
        nikename: '',
        phone: '',
        email: '',
        dept: '',
        job: '',
        sex: '',
        enabled: '',
        role: ''
      },
      userRef: {},
      userRule: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 10, message: '用户名在3～10位之间', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
          {
            validator: checkPhone,
            message: '请输入正确的电话号码',
            trggier: 'blur'
          }
        ],
        nikename: [
          { required: true, message: '请输入昵称', trigger: 'blur' },
          { min: 3, max: 10, message: '昵称在3～10位之间', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入用户邮箱', trggier: 'blur' },
          {
            validator: checkEmail,
            message: '请输入正确的邮箱地址',
            trggier: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getUserList()
  },
  methods: {
    async getUserList() {
      const { data: res } = await this.$http.get('/api/user', {
        params: this.usersQueryParms
      })
      console.log(res.data)

      const that = this
      this.userList = res.data.map(function(val) {
        val.user.createTime = that
          .$dateFormat(val.user.createTime)
          .format('YYYY-MM-DD HH:mm:ss')
        console.log(val.user.createTime)
        if (val.user.enabled === 1) {
          val.user.enabled = true
        } else {
          val.user.enabled = false
        }
        that.totalSize = val.total
        return val
      })
    },
    // 用户状态切换
    onUserStatusSwitch(val) {
      // TODO
      console.log('------------' + val.user.enabled)

      this.$confirm(
        '此操作将 "禁用"' + val.user.username + ' , 是否继续？',
        '提示',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      )
        .then(() => {
          this.$message({
            type: 'success',
            message: '禁用成功!'
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消禁用'
          })
        })
    },
    // 切换每页展示的数量
    handleSizeChange(newSize) {
      console.log(newSize)
      this.usersQueryParms.pageSize = newSize
      this.getUserList()
    },
    // 监听当前页码
    handleCurrentChange(nemPage) {
      console.log(nemPage)
      this.usersQueryParms.pageNum = nemPage
      this.getUserList()
    },
    addUser() {
      this.dialogUserAddVisible = !this.dialogUserAddVisible
    },
    submitAddUserForm() {
      const that = this
      this.$refs.userRef.validate(valid => {
        console.log(valid)

        if (!valid) return false

        // 进行添加用户的操作

        console.log(that.user)
      })
      // this.dialogUserAddVisible = false
    },
    // 添加用户的dialog关闭的时候调用
    addUserDialogClose() {
      this.$refs.userRef.resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
.box-card {
  margin-top: 20dp;
  padding-top: 20dp;
}
.el-table-column {
  text-align: center;
}
/deep/ .role-select {
  width: 100%;
}

.dept-select {
  width: 100%;
}
</style>
