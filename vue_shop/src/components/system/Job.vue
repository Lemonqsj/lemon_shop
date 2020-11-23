<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>岗位管理</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addjob">添加岗位</el-button>
        </el-col>
      </el-row>

      <!-- 表格数据区域 -->
      <el-table
        :data="jobList"
        style="width: 100%"
        fit
        border
        stripe
        size="small"
      >
        <el-table-column label="序号" align="center" type="index" width="50dp">
        </el-table-column>
        <el-table-column label="名称" align="center" prop="name">
        </el-table-column>
        <el-table-column label="排序" align="center" prop="jobSort">
        </el-table-column>
        <el-table-column label="状态" align="center" prop="enabled">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.enabled"> </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="创建日期" align="center" prop="createTime">
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
        :current-page="jobQueryParm.pageNum"
        :page-sizes="[1, 2, 5, 10]"
        :page-size="jobQueryParm.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      >
      </el-pagination>
      <!-- 添加用户的dialog -->
      <el-dialog
        title="新增岗位"
        :visible.sync="addJobdialogVisible"
        width="30%"
      >
        <el-form ref="jobRef" :model="job" :rules="jobRules" label-width="80px">
          <el-form-item label="名称" prop="name">
            <el-input v-model="job.name"></el-input>
          </el-form-item>
          <el-form-item label="排序">
            <el-input-number
              class="addJob_input_number"
              v-model="job.sortNum"
              controls-position="right"
              @change="addJobSortNumChange"
              :min="1"
              :max="100"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio-group v-model="job.state">
              <el-radio label="启用"></el-radio>
              <el-radio label="停用"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <span slot="footer">
          <el-button @click="addJobdialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addJobdialogVisible = false"
            >确 定</el-button
          >
        </span>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  created() {
    this.getJobList()
  },
  data() {
    return {
      jobList: [],
      jobQueryParm: {
        pageSize: 2,
        pageNum: 1,
        query: 2
      },
      total: 0,
      addJobdialogVisible: false,
      job: { name: '', sortNum: 99, state: true },
      jobRules: {
        name: [
          { required: true, message: '岗位名称不能为空', trigger: 'blur' },
          {
            min: 3,
            max: 20,
            message: '岗位名称必须在3～20位之间',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    // 获取用户岗位列表
    async getJobList() {
      const { data: res } = await this.$http.get('/api/job', {
        params: this.jobQueryParm
      })
      console.log(res)

      if (res.code !== 200) {
        return this.$message.error('获取岗位列表失败！')
      }

      var that = this
      this.jobList = res.data.map(function(val) {
        that.total = val.total
        val.createTime = that
          .$dateFormat(val.createTime)
          .format('YYYY-MM-DD HH:mm:ss')
        return val
      })
    },
    // 添加岗位
    addjob() {
      this.addJobdialogVisible = true
    },
    // 每一页条数发生改变的时候
    handleSizeChange(newSize) {
      this.jobQueryParm.pageSize = newSize
      this.getJobList()
    },
    // 用户切换页码的时候调用
    handleCurrentChange(newPage) {
      this.jobQueryParm.pageNum = newPage
      this.getJobList()
    },
    // 添加岗位排序变化
    addJobSortNumChange(newNum) {
      console.log(newNum)
    }
  }
}
</script>

<style lang="less" scoped>
.addJob_input_number {
  width: 100%;
}
</style>
