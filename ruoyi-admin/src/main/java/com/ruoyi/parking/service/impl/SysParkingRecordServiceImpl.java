package com.ruoyi.parking.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.parking.mapper.SysParkingRecordMapper;
import com.ruoyi.parking.domain.SysParkingRecord;
import com.ruoyi.parking.service.ISysParkingRecordService;

/**
 * 车辆进出记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-08
 */
@Service
public class SysParkingRecordServiceImpl implements ISysParkingRecordService 
{
    @Autowired
    private SysParkingRecordMapper sysParkingRecordMapper;

    /**
     * 查询车辆进出记录
     * 
     * @param recordId 车辆进出记录主键
     * @return 车辆进出记录
     */
    @Override
    public SysParkingRecord selectSysParkingRecordByRecordId(Long recordId)
    {
        return sysParkingRecordMapper.selectSysParkingRecordByRecordId(recordId);
    }

    /**
     * 查询车辆进出记录列表
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 车辆进出记录
     */
    @Override
    public List<SysParkingRecord> selectSysParkingRecordList(SysParkingRecord sysParkingRecord)
    {
        return sysParkingRecordMapper.selectSysParkingRecordList(sysParkingRecord);
    }

    /**
     * 新增车辆进出记录
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 结果
     */
    @Override
    public int insertSysParkingRecord(SysParkingRecord sysParkingRecord)
    {
        sysParkingRecord.setCreateTime(DateUtils.getNowDate());
        return sysParkingRecordMapper.insertSysParkingRecord(sysParkingRecord);
    }

    /**
     * 修改车辆进出记录
     * 
     * @param sysParkingRecord 车辆进出记录
     * @return 结果
     */
    @Override
    public int updateSysParkingRecord(SysParkingRecord sysParkingRecord)
    {
        return sysParkingRecordMapper.updateSysParkingRecord(sysParkingRecord);
    }

    /**
     * 批量删除车辆进出记录
     * 
     * @param recordIds 需要删除的车辆进出记录主键
     * @return 结果
     */
    @Override
    public int deleteSysParkingRecordByRecordIds(Long[] recordIds)
    {
        return sysParkingRecordMapper.deleteSysParkingRecordByRecordIds(recordIds);
    }

    /**
     * 删除车辆进出记录信息
     * 
     * @param recordId 车辆进出记录主键
     * @return 结果
     */
    @Override
    public int deleteSysParkingRecordByRecordId(Long recordId)
    {
        return sysParkingRecordMapper.deleteSysParkingRecordByRecordId(recordId);
    }
}
