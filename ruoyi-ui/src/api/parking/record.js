import request from '@/utils/request'

// ================= 图片识别相关 =================

// 1. 上传并识别图片
export function recognizeImage(data) {
  return request({
    url: '/parking/recognition/image',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 2. 查询图片识别记录列表
export function listImageLog(query) {
  return request({
    url: '/parking/recognition/image/list',
    method: 'get',
    params: query
  })
}

// 3. 删除图片识别记录
export function delImageLog(id) {
  return request({
    url: '/parking/recognition/image/' + id,
    method: 'delete'
  })
}

// ================= 视频识别相关 =================

// 4. 上传并识别视频
export function recognizeVideo(data) {
  return request({
    url: '/parking/recognition/video',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 5. 查询视频识别记录列表
export function listVideoLog(query) {
  return request({
    url: '/parking/recognition/video/list',
    method: 'get',
    params: query
  })
}

// 6. 删除视频识别记录
export function delVideoLog(id) {
  return request({
    url: '/parking/recognition/video/' + id,
    method: 'delete'
  })
}