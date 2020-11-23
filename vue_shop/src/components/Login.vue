<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" alt="" />
      </div>
      <!-- 登录表单区域 -->
      <el-form
        ref="loginFormRef"
        :model="LoginForm"
        :rules="loginFormRules"
        label-width="0px"
        class="login_form"
      >
        <el-form-item prop="username">
          <el-input
            prefix-icon="iconfont icon-user"
            v-model="LoginForm.username"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            prefix-icon="iconfont icon-3702mima"
            v-model="LoginForm.password"
          ></el-input>
        </el-form-item>

        <el-form-item class="form_btn">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      LoginForm: {
        username: '',
        password: ''
      },
      loginFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    resetLoginForm() {
      // console.log(this)
      this.$refs.loginFormRef.resetFields()
    },
    login() {
      // 箭头函数的写法，参数然后=>然后谢大括号回调  同等的
      this.$refs.loginFormRef.validate(async valid => {
        console.log(valid)
        if (!valid) return

        await this.$http
          .post('/admin/login', this.LoginForm)
          .then(res => {
            // console.log(res)
            const result = res.data

            if (result.code !== 200) {
              if (result.message === '用户名错误') {
                return this.$message.error('用户名错误,请重新输入！')
              }

              if (
                result.message === '密码错误' ||
                result.message === '密码不正确'
              ) {
                return this.$message.error('密码错误,请重新输入！')
              }
              // 登录失败
              return this.$message.error('登录失败')
            }

            console.log(result.data.token)
            console.log(result.data.tokenHead + result.data.token)
            window.sessionStorage.setItem(
              'token',
              result.data.tokenHead + result.data.token
            )
            // 登录成功
            this.$message.success('登录成功')
            this.$router.push('/home')
          })
          .catch(err => {
            console.log(err)
          })
      })
      // this.$refs.loginFormRef.validate(function callback(params) {
      //   console.log(params)
      // })
    }
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}
.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.avatar_box {
  height: 130px;
  width: 130px;
  border: 1px, solid, #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;

  img {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #eee;
  }
}
.login_form {
  position: absolute;
  width: 100%;
  bottom: 0px;
  padding: 0 20px;
  box-sizing: border-box;
}
.form_btn {
  display: flex;
  justify-content: flex-end;
}
</style>
