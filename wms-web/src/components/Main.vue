<template>
  <div class="main">
    <div class="search">
      <el-input
          placeholder="请输入名称"
          v-model="input"
          clearable
          class="input"
      >
      </el-input>
      <el-button type="primary" @click="() => {
        listP(input)
        currentSize = 5
        currentPage = 1
        mode = true
      }">查找
      </el-button>
      <el-button type="info" @click="() => {
        listPage(1, 5)
        mode = false
        input = ''
      }">重置
      </el-button>
      <el-button
          type="success"
          @click="centerDialogVisible = true"
      >新增
      </el-button>
      <el-dialog
          title="新增"
          :visible.sync="centerDialogVisible"
          width="30%"
          center
      >
        <el-form ref="form" status-icon :model="user" label-width="80px" :rules="rules">
          <el-form-item label="账号" prop="no">
            <el-input v-model="user.no"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input v-model="user.name"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="user.password"></el-input>
          </el-form-item>
          <el-form-item label="角色" prop="roleId">
            <el-select v-model="user.roleId" placeholder="请选择角色类型">
              <el-option label="超级管理员" value="0"></el-option>
              <el-option label="管理员" value="1"></el-option>
              <el-option label="用户" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="user.age"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="user.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="user.phone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="clearUser">取 消</el-button>
          <el-button type="primary" @click="commitUser('form')">确 定</el-button>
        </span>
      </el-dialog>
    </div>
    <el-table
        :data="tableData"
        border
        stripe
        style="width: 100%"
    >
      <el-table-column prop="id" label="id" sortable>
      </el-table-column>
      <el-table-column prop="no" label="账号">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
      <el-table-column
          prop="roleId"
          label="角色"
          sortable
      >
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.roleId == '0' ? 'danger' : scope.row.roleId == '1' ? undefined : 'success'"
              disable-transitions>
            {{ scope.row.roleId == '0' ? '超级管理员' : scope.row.roleId == '1' ? '管理员' : '用户' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column
          prop="sex"
          label="性别"
          sortable
      >
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.sex == '1' ? undefined : 'danger'"
              disable-transitions>{{ scope.row.sex == '1' ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="primary"
          >编辑
          </el-button>
          <el-button
              size="mini"
              type="danger"
              @click="delUserById(scope.row.id)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block">
      <el-pagination
          class="block-pagination"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="currentSize"
          layout="sizes, prev, pager, next"
          :total="totalSize">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    const checkNo = (rule, value, callback) => {
      if (value === '') callback(new Error('账号不能为空'))
      else {
        callback()
      }
    };
    const checkName = (rule, value, callback) => {
      if (value === '') callback(new Error('姓名不能为空'))
      else {
        callback()
      }
    };
    const isValidPassword = (password) => {
      // 正则表达式，用于检查密码是否完全由字母、数字或下划线组成
      const pattern = /^[A-Za-z\d_]+$/;

      // 测试密码是否匹配该模式
      return pattern.test(password);
    }
    const checkPassword = (rule, value, callback) => {
      if (value === '') callback(new Error('密码不能为空'))
      else if (value.length < 3 || value.length > 16) {
        callback(new Error('密码长度需要在范围 3 ~ 16 内'))
      }
      else if (!isValidPassword(value)) {
        callback(new Error('密码含有非法字符（只能由字母、数字、下划线组成）'))
      }
      else {
        callback()
      }
    };
    const checkRoleId = (rule, value, callback) => {
      if (value === '') callback(new Error('角色不能为空'))
      else {
        callback()
      }
    };
    const checkAge = (rule, value, callback) => {
      if (value === '') callback(new Error('年龄不能为空'))
      else if (!/^[1-9]+[0-9]+$/.test(value)) {
        callback(new Error('年龄必须为正整数'))
      }
      else {
        callback()
      }
    };
    const checkSex = (rule, value, callback) => {
      if (value === '') callback(new Error('性别不能为空'))
      else {
        callback()
      }
    };
    return {
      tableData: [],
      currentPage: 1,
      currentSize: 5,
      totalSize: 0,
      input: '',
      mode: false,
      centerDialogVisible: false,
      user: {
        no: '',
        name: '',
        password: '',
        roleId: '',
        age: '',
        sex: '',
        phone: '',
      },
      rules: {
        no: [
          {validator: checkNo, trigger: 'blur'}
        ],
        name: [
          {validator: checkName, trigger: 'blur'}
        ],
        password: [
          {validator: checkPassword, trigger: 'blur'}
        ],
        roleId: [
          {validator: checkRoleId, trigger: 'blur'}
        ],
        age: [
          {validator: checkAge, trigger: 'blur'}
        ],
        sex: [
          {validator: checkSex, trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    loadGet() {
      this.$axios.get('http://localhost:8090/user/list').then(res => res.data).then(res => {
        this.tableData = res.data
      })
    },
    loadPost(data) {
      this.$axios.post('http://localhost:8090/user/listPage', data).then(res => {
        this.totalSize = res.data.total
        return res.data
      }).then(res => {
        this.tableData = res.data
      })
    },
    handleSizeChange(val) {
      this.currentSize = val
      if (this.mode) this.listP(this.input)
      else this.listPage()
    },
    handleCurrentChange(val) {
      this.currentPage = val
      if (this.mode) this.listP(this.input)
      else this.listPage()
    },
    listPage(currentPage = this.currentPage, currentSize = this.currentSize) {
      this.currentPage = currentPage
      this.currentSize = currentSize
      this.loadPost({
        pageSize: this.currentSize,
        pageNum: this.currentPage,
      });
    },
    listP(name) {
      this.loadPost({
        pageSize: this.currentSize,
        pageNum: this.currentPage,
        params: {
          name: name
        }
      });
    },
    clearUser() {
      this.centerDialogVisible = false
      console.log(this.user)
    },
    commitUser(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.centerDialogVisible = false
          this.$axios.post('http://localhost:8090/user/save', this.user).then(res => {
            alert('submit accept')
          }).catch(res => {
            alert('submit error!')
          })
          console.log(this.user)
          return true
        } else {
          console.log('error submit!!')
          console.log(this.user)
          return false
        }
      })
    },
    delUserById(id) {
      this.$axios.get(`http://localhost:8090/user/delete?id=${id}`).then(res => {
        alert('delete accept')
        if (this.mode) this.listP(this.input)
        else this.listPage()
      }).catch(res => {
        console.log(id)
        alert('delete error!')
      })
    }
  },
  created() {
    this.listPage()
  },

}
</script>

<style scoped>
.block .block-pagination {
  display: inline-block;

}

.block {
  display: flex;
  justify-content: center;
  margin: 30px 0;
}

.input {
  width: 20%;
  margin: 0 10px 20px 0;
}
</style>