package com.ruoyi.parking.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.parking.domain.SysParkingRecord;
import com.ruoyi.parking.service.ISysParkingRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆进出记录Controller
 * 
 * @author ruoyi
 * @date 2026-01-08
 */
@RestController
@RequestMapping("/parking/record")
public class SysParkingRecordController extends BaseController
{
    @Autowired
    private ISysParkingRecordService sysParkingRecordService;

    /**
     * 查询车辆进出记录列表
     */
    @PreAuthorize("@ss.hasPermi('parking:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysParkingRecord sysParkingRecord)
    {
        startPage();
        List<SysParkingRecord> list = sysParkingRecordService.selectSysParkingRecordList(sysParkingRecord);
        return getDataTable(list);
    }

    /**
     * 导出车辆进出记录列表
     */
    @PreAuthorize("@ss.hasPermi('parking:record:export')")
    @Log(title = "车辆进出记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysParkingRecord sysParkingRecord)
    {
        List<SysParkingRecord> list = sysParkingRecordService.selectSysParkingRecordList(sysParkingRecord);
        ExcelUtil<SysParkingRecord> util = new ExcelUtil<SysParkingRecord>(SysParkingRecord.class);
        util.exportExcel(response, list, "车辆进出记录数据");
    }

    /**
     * 获取车辆进出记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('parking:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(sysParkingRecordService.selectSysParkingRecordByRecordId(recordId));
    }

    /**
     * 新增车辆进出记录
     */
    @PreAuthorize("@ss.hasPermi('parking:record:add')")
    @Log(title = "车辆进出记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysParkingRecord sysParkingRecord)
    {
        return toAjax(sysParkingRecordService.insertSysParkingRecord(sysParkingRecord));
    }

    /**
     * 修改车辆进出记录
     */
    @PreAuthorize("@ss.hasPermi('parking:record:edit')")
    @Log(title = "车辆进出记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysParkingRecord sysParkingRecord)
    {
        return toAjax(sysParkingRecordService.updateSysParkingRecord(sysParkingRecord));
    }

    /**
     * 删除车辆进出记录
     */
    @PreAuthorize("@ss.hasPermi('parking:record:remove')")
    @Log(title = "车辆进出记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(sysParkingRecordService.deleteSysParkingRecordByRecordIds(recordIds));
    }
}
