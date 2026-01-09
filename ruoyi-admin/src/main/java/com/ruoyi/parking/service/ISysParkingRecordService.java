package com.ruoyi.parking.service;

import java.util.List;
import com.ruoyi.parking.domain.SysParkingRecord;

/**
 * 车辆进出记录Service接口
 * 
 * @author ruoyi
 * @date 2026-01-08
 */
public interface ISysParkingRecordService 
{
    /**
     * 查询车辆进出记录
     * 
     * @param recordId 车辆进出记录主键
     * @return 车辆进出记录
     */
    public SysParkingRecord selectSysParkingRecordByRecordId(Long recordId);

    /**
     * 查询车辆进出记录列表
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 车辆进出记录集合
     */
    public List<SysParkingRecord> selectSysParkingRecordList(SysParkingRecord sysParkingRecord);

    /**
     * 新增车辆进出记录
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 结果
     */
    public int insertSysParkingRecord(SysParkingRecord sysParkingRecord);

    /**
     * 修改车辆进出记录
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 结果
     */
    public int updateSysParkingRecord(SysParkingRecord sysParkingRecord);

    /**
     * 批量删除车辆进出记录
     * 
     * @param recordIds 需要删除的车辆进出记录主键集合
     * @return 结果
     */
    public int deleteSysParkingRecordByRecordIds(Long[] recordIds);

    /**
     * 删除车辆进出记录信息
     * 
     * @param recordId 车辆进出记录主键
     * @return 结果
     */
    public int deleteSysParkingRecordByRecordId(Long recordId);
}
