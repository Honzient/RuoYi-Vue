package com.ruoyi.parking.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.config.RuoYiConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 车牌识别接口 (Java端中转)
 */
@RestController
@RequestMapping("/parking/recognition")
public class RecognitionController extends BaseController {

    /**
     * 图片识别接口
     */
    @PostMapping("/image")
    public AjaxResult recognizeImage(@RequestParam("file") MultipartFile file) {
        try {
            // 1. 保存文件到本地 (Ruoyi 默认上传路径)
            String filePath = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);

            // TODO: 这里是第二步要做的，调用 Python 接口进行识别
            // 目前先返回一个假数据，测试前端是否通畅
            Map<String, Object> data = new HashMap<>();
            data.put("plateNumber", "京A·88888 (模拟数据)");
            data.put("confidence", 98.5);
            data.put("color", "蓝牌");
            // 这里为了演示，直接把上传的图片作为结果图返回
            // 注意：正式环境应该是返回识别后的截图
            data.put("imagePath", filePath);

            return AjaxResult.success(data);
        } catch (Exception e) {
            return AjaxResult.error("识别请求失败：" + e.getMessage());
        }
    }

    /**
     * 视频识别接口
     */
    @PostMapping("/video")
    public AjaxResult recognizeVideo(@RequestParam("file") MultipartFile file) {
        try {
            // 1. 保存视频
            String videoPath = FileUploadUtils.upload(RuoYiConfig.getUploadPath(), file);

            // TODO: 调用 Python 处理视频
            Map<String, Object> data = new HashMap<>();
            data.put("plateNumber", "沪B·66666 (视频结果)");
            data.put("timestamp", "00:00:05");
            data.put("confidence", 95.0);
            // 视频识别通常返回截取的关键帧图片，这里暂时用空占位
            data.put("frameImage", "");

            return AjaxResult.success(data);
        } catch (Exception e) {
            return AjaxResult.error("视频处理失败：" + e.getMessage());
        }
    }
}