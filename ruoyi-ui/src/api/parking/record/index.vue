import request from '@/utils/request'

// 查询车辆进出记录列表
export function listRecord(query) {
  return request({
    url: '/parking/record/list',
    method: 'get',
    params: query
  })
}

// 查询车辆进出记录详细
export function getRecord(recordId) {
  return request({
    url: '/parking/record/' + recordId,
    method: 'get'
  })
}

// 新增车辆进出记录
export function addRecord(data) {
  return request({
    url: '/parking/record',
    method: 'post',
    data: data
  })
}

// 修改车辆进出记录
export function updateRecord(data) {
  return request({
    url: '/parking/record',
    method: 'put',
    data: data
  })
}

// 删除车辆进出记录
export function delRecord(recordId) {
  return request({
    url: '/parking/record/' + recordId,
    method: 'delete'
  })
}