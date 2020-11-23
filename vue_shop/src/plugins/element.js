import Vue from 'vue'
import {
  Button,
  Form,
  FormItem,
  Input,
  Message,
  container,
  header,
  aside,
  main,
  Menu,
  MenuItem,
  Submenu,
  Breadcrumb,
  BreadcrumbItem,
  Card,
  Col,
  Row,
  Table,
  TableColumn,
  Switch,
  Pagination,
  MessageBox,
  Dialog,
  Radio,
  Select,
  Option,
  RadioGroup,
  InputNumber,
  Tree,
  Popover
} from 'element-ui'

Vue.use(Button)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Input)
Vue.use(container)
Vue.use(header)
Vue.use(aside)
Vue.use(main)
Vue.use(Menu)
Vue.use(MenuItem)
Vue.use(Submenu)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Card)
Vue.use(Col)
Vue.use(Row)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(Switch)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Radio)
Vue.use(Select)
Vue.use(Option)
Vue.use(RadioGroup)
Vue.use(InputNumber)
Vue.use(Tree)
Vue.use(Popover)
Vue.prototype.$message = Message
Vue.prototype.$confirm = MessageBox.confirm
