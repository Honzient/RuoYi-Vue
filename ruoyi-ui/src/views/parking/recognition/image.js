<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-picture"></i> 原图上传</span>
          </div>
          <div class="upload-area">
            <el-upload
              class="avatar-uploader"
              action="#"
              :show-file-list="false"
              :http-request="handleUpload"
              accept=".jpg,.jpeg,.png">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
            <div style="margin-top: 20px;">
              <el-button type="primary" @click="triggerUpload">选择图片</el-button>
              <el-button type="success" :loading="loading" @click="startRecognize" :disabled="!fileObj">开始识别</el-button>
            </div>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span><i class="el-icon-s-data"></i> 识别结果</span>
          </div>
          <el-form label-width="100px">
            <el-form-item label="车牌号码">
              <el-input v-model="result.plateNumber" readonly placeholder="等待识别..."></el-input>
            </el-form-item>
            <el-form-item label="置信度">
              <el-progress :text-inside="true" :stroke-width="20" :percentage="result.confidence"></el-progress>
            </el-form-item>
            <el-form-item label="车牌颜色">
              <el-tag>{{ result.color || '未知' }}</el-tag>
            </el-form-item>
            <el-form-item label="识别截图">
               <el-image v-if="result.imagePath" :src="result.imagePath" style="width: 200px; height: 100px" fit="contain"></el-image>
               <span v-else style="color: #909399">暂无截图</span>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { recognizeImage } from "@/api/parking/recognition";

export default {
  name: "ImageRecognition",
  data() {
    return {
      imageUrl: "",
      fileObj: null,
      loading: false,
      result: {
        plateNumber: "",
        confidence: 0,
        color: "",
        imagePath: ""
      }
    };
  },
  methods: {
    triggerUpload() {
      document.querySelector('.avatar-uploader .el-upload__input').click();
    },
    handleUpload(param) {
      this.fileObj = param.file;
      this.imageUrl = URL.createObjectURL(param.file);
      // 重置结果
      this.result = { plateNumber: "", confidence: 0, color: "", imagePath: "" };
    },
    startRecognize() {
      this.loading = true;
      let formData = new FormData();
      formData.append("file", this.fileObj);

      recognizeImage(formData).then(res => {
        this.loading = false;
        if (res.code === 200) {
          this.result = res.data;
          this.$message.success("识别成功");
        } else {
          this.$message.error(res.msg);
        }
      }).catch(() => {
        this.loading = false;
      });
    }
  }
};
</script>

<style scoped>
.upload-area { text-align: center; padding: 20px; }
.avatar-uploader .el-upload { border: 1px dashed #d9d9d9; border-radius: 6px; cursor: pointer; position: relative; overflow: hidden; }
.avatar-uploader .el-upload:hover { border-color: #409EFF; }
.avatar-uploader-icon { font-size: 28px; color: #8c939d; width: 400px; height: 300px; line-height: 300px; text-align: center; background: #f5f7fa; }
.avatar { width: 400px; height: 300px; display: block; object-fit: contain; background: #000; }
</style>