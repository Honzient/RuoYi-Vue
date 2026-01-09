<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="14">
        <el-card>
          <div slot="header"><span><i class="el-icon-video-camera"></i> 视频上传与预览</span></div>
          <div class="video-box">
            <video v-if="videoUrl" :src="videoUrl" controls class="video-player"></video>
            <div v-else class="placeholder">请上传视频文件</div>
          </div>
          <div style="margin-top: 20px; text-align: center;">
            <el-upload
              action="#"
              :http-request="uploadVideo"
              :show-file-list="false"
              accept=".mp4,.avi,.mov">
              <el-button type="primary" icon="el-icon-upload">选择视频</el-button>
            </el-upload>
            <el-button type="success" icon="el-icon-cpu" @click="handleRecognize" :loading="loading" :disabled="!fileObj" style="margin-left: 10px;">
              开始智能识别
            </el-button>
          </div>
        </el-card>
      </el-col>

      <el-col :span="10">
        <el-card>
          <div slot="header"><span>识别结果 (自动截取关键帧)</span></div>
          <div class="result-img-box">
            <img v-if="result.frameImage" :src="result.frameImage" class="result-img"/>
            <span v-else>暂无识别画面</span>
          </div>
          <el-descriptions :column="1" border style="margin-top: 20px;">
            <el-descriptions-item label="车牌号码">
              <span style="font-size: 20px; font-weight: bold; color: #1890ff;">{{ result.plateNumber || '-' }}</span>
            </el-descriptions-item>
            <el-descriptions-item label="视频时间点">{{ result.timestamp || '-' }}</el-descriptions-item>
            <el-descriptions-item label="置信度">
              <el-tag type="success" v-if="result.confidence">{{ result.confidence }}%</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { recognizeVideo } from "@/api/parking/recognition";

export default {
  data() {
    return {
      videoUrl: "",
      fileObj: null,
      loading: false,
      result: {
        plateNumber: "",
        frameImage: "",
        confidence: 0,
        timestamp: ""
      }
    };
  },
  methods: {
    uploadVideo(param) {
      this.fileObj = param.file;
      this.videoUrl = URL.createObjectURL(param.file);
      this.result = { plateNumber: "", frameImage: "", confidence: 0 };
    },
    handleRecognize() {
      this.loading = true;
      let formData = new FormData();
      formData.append("file", this.fileObj);

      recognizeVideo(formData).then(res => {
        this.loading = false;
        if (res.code === 200) {
          this.result = res.data;
          this.$message.success("视频分析完成！");
        } else {
          this.$message.error(res.msg);
        }
      }).catch(() => {
        this.loading = false;
        this.$message.error("识别服务请求超时");
      });
    }
  }
};
</script>

<style scoped>
.video-box { width: 100%; height: 400px; background: #000; display: flex; justify-content: center; align-items: center; }
.video-player { width: 100%; height: 100%; }
.placeholder { color: #fff; font-size: 16px; }
.result-img-box { width: 100%; height: 250px; border: 1px solid #eee; display: flex; justify-content: center; align-items: center; background: #f9f9f9; }
.result-img { max-width: 100%; max-height: 100%; }
</style>