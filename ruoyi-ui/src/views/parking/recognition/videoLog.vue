<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="车牌号" prop="plateNumber">
        <el-input v-model="queryParams.plateNumber" placeholder="请输入车牌号" clearable size="small" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      
      <el-table-column label="视频文件" align="center" width="150">
        <template slot-scope="scope">
          <el-button type="text" icon="el-icon-video-play" @click="playVideo(scope.row.videoPath)">
            点击播放
          </el-button>
        </template>
      </el-table-column>

      <el-table-column label="关键帧截图" align="center" width="120">
        <template slot-scope="scope">
          <el-image 
            style="width: 80px; height: 60px"
            :src="scope.row.frameImage" 
            :preview-src-list="[scope.row.frameImage]">
            <div slot="error" class="image-slot">
              <i class="el-icon-picture-outline"></i>
            </div>
          </el-image>
        </template>
      </el-table-column>

      <el-table-column label="识别结果" align="center" prop="plateNumber">
        <template slot-scope="scope">
          <strong style="color: #1890ff; font-size: 16px;">{{ scope.row.plateNumber }}</strong>
        </template>
      </el-table-column>

      <el-table-column label="出现时间点" align="center" prop="timestamp" />

      <el-table-column label="识别时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-delete" class="text-danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog title="视频预览" :visible.sync="videoOpen" width="600px" append-to-body @close="stopVideo">
      <video ref="videoPlayer" :src="currentVideoUrl" controls width="100%" autoplay></video>
    </el-dialog>
  </div>
</template>

<script>
import { listVideoLog, delVideoLog } from "@/api/parking/recognition";

export default {
  name: "VideoLog",
  data() {
    return {
      loading: true,
      ids: [],
      multiple: true,
      showSearch: true,
      total: 0,
      logList: [],
      videoOpen: false,
      currentVideoUrl: "",
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plateNumber: null
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listVideoLog(this.queryParams).then(response => {
        this.logList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(() => {
        this.loading = false;
      });
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.multiple = !selection.length;
    },
    playVideo(url) {
      this.currentVideoUrl = process.env.VUE_APP_BASE_API + url; // 自动加上后端地址前缀
      this.videoOpen = true;
    },
    stopVideo() {
      this.currentVideoUrl = "";
    },
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除数据？').then(function() {
        return delVideoLog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>