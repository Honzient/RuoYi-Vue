import request from '@/utils/request'

// 图片识别上传
export function recognizeImage(data) {
  return request({
    url: '/parking/recognition/image',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// 视频识别上传
export function recognizeVideo(data) {
  return request({
    url: '/parking/recognition/video',
    method: 'post',
    data: data,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}