package com.ruoyi.parking.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆进出记录对象 sys_parking_record
 * 
 * @author ruoyi
 * @date 2026-01-08
 */
public class SysParkingRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String plateNumber;

    /** 入场时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入场时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /** 出场时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出场时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date exitTime;

    /** 停车时长 */
    @Excel(name = "停车时长")
    private String parkingDuration;

    /** 停车费用 */
    @Excel(name = "停车费用")
    private BigDecimal fee;

    /** 状态（0入场 1出场） */
    @Excel(name = "状态", readConverterExp = "0=入场,1=出场")
    private String status;

    /** 入场抓拍图片 */
    @Excel(name = "入场抓拍图片")
    private String entryImage;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setPlateNumber(String plateNumber) 
    {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() 
    {
        return plateNumber;
    }

    public void setEntryTime(Date entryTime) 
    {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() 
    {
        return entryTime;
    }

    public void setExitTime(Date exitTime) 
    {
        this.exitTime = exitTime;
    }

    public Date getExitTime() 
    {
        return exitTime;
    }

    public void setParkingDuration(String parkingDuration) 
    {
        this.parkingDuration = parkingDuration;
    }

    public String getParkingDuration() 
    {
        return parkingDuration;
    }

    public void setFee(BigDecimal fee) 
    {
        this.fee = fee;
    }

    public BigDecimal getFee() 
    {
        return fee;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setEntryImage(String entryImage) 
    {
        this.entryImage = entryImage;
    }

    public String getEntryImage() 
    {
        return entryImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("plateNumber", getPlateNumber())
            .append("entryTime", getEntryTime())
            .append("exitTime", getExitTime())
            .append("parkingDuration", getParkingDuration())
            .append("fee", getFee())
            .append("status", getStatus())
            .append("entryImage", getEntryImage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
