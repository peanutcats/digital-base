<template>
  <div class="home-container">
    <div class="header">
      <h1 class="platform-title">数据标准规范可视化平台</h1>
    </div>
    <div class="search-container">
      <el-input
        v-model="searchText"
        placeholder="请输入搜索内容"
        @input="handleSearch"
        clearable
        size="large"
      />
    </div>
    <div class="content-container">
      <div class="left-panel">
        <!-- 树状结构 -->
        <el-tree
          :data="tableTree"
          @node-click="handleNodeClick"
          :filter-node-method="filterNode"
          ref="treeRef"
          :default-expand-all="true"
        />
      </div>
      <div class="right-panel">
        <!-- 表格详情 -->
        <el-table 
          :data="tableData" 
          style="width: 100%" 
          height="calc(100vh - 180px)"
          :header-cell-style="{
            background: '#f5f7fa',
            color: '#333',
            fontWeight: 'bold',
            fontSize: '14px'
          }"
        >
          <el-table-column prop="fieldNo" label="编号" width="100" />
          <el-table-column prop="fieldName" label="字段名" width="150" />
          <el-table-column prop="chineseName" label="中文简称" width="150" />
          <el-table-column prop="type" label="类型" width="100" />
          <el-table-column prop="length" label="长度" width="100" />
          <el-table-column prop="constraint" label="约束" width="100" />
          <el-table-column prop="valueSpace" label="值空间" width="150" />
          <el-table-column prop="example" label="解释/举例" min-width="200" />
          <el-table-column prop="referenceNo" label="引用编号" width="100" />
        </el-table>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getXxjbsjList } from '@/api/xxjbsj'
import { ElMessage } from 'element-plus'

const tableTree = ref([
  {
    label: 'ODS_XXJBSJ',
    children: []
  }
])
const tableData = ref([])
const searchText = ref('')

// 获取数据
const fetchData = async () => {
  try {
    const response = await getXxjbsjList()
    console.log('接收到的数据:', response.data)
    if (Array.isArray(response.data)) {
      tableData.value = response.data
    } else {
      ElMessage.error('数据格式错误')
    }
  } catch (error) {
    console.error('获取数据失败:', error)
    ElMessage.error('获取数据失败: ' + error.message)
  }
}

// 处理树节点点击
const handleNodeClick = (data) => {
  fetchData()
}

// 处理搜索
const handleSearch = (value) => {
  if (!value) {
    fetchData()
    return
  }
  
  const filteredData = tableData.value.filter(item => {
    return Object.values(item).some(val => 
      val && val.toString().toLowerCase().includes(value.toLowerCase())
    )
  })
  tableData.value = filteredData
}

// 过滤节点
const filterNode = (value, data) => {
  if (!value) return true
  return data.label.includes(value)
}

onMounted(() => {
  fetchData()
})
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #f5f7fa;
}

.header {
  background-color: #409EFF;
  padding: 15px 0;
  text-align: center;
  color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}

.platform-title {
  font-size: 24px;
  font-weight: bold;
  margin: 0;
}

.search-container {
  padding: 20px;
  background-color: white;
  border-bottom: 1px solid #ddd;
}

.content-container {
  display: flex;
  flex: 1;
  background-color: white;
  margin: 20px;
  border-radius: 4px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.left-panel {
  width: 300px;
  padding: 20px;
  border-right: 1px solid #ddd;
  background-color: white;
}

.right-panel {
  flex: 1;
  padding: 20px;
  background-color: white;
}

:deep(.el-table .cell) {
  font-size: 14px;
  line-height: 1.5;
}

:deep(.el-tree-node__content) {
  height: 36px;
}

:deep(.el-input__wrapper) {
  font-size: 14px;
}

:deep(.el-table th) {
  background-color: #f5f7fa !important;
}

:deep(.el-table--enable-row-hover .el-table__body tr:hover > td) {
  background-color: #ecf5ff;
}
</style> 