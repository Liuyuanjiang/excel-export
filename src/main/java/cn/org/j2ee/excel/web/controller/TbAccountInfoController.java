package cn.org.j2ee.excel.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;

import cn.org.j2ee.excel.common.ExcelUtil;
import cn.org.j2ee.excel.common.ExcelUtil.ExcelType;
import cn.org.j2ee.excel.common.ExportItem;
import cn.org.j2ee.excel.common.ExportUtil;
import cn.org.j2ee.excel.common.RandomUtil;
import cn.org.j2ee.excel.model.JsonView;
import cn.org.j2ee.excel.model.TbAccountInfoModel;
import cn.org.j2ee.excel.service.TbAccountInfoService;

/**
 * @TypeName 账号信息 Controller
 * @TableName tb_account_info
 * @Author wuwz@live.com
 * @DateTime 2016-7-10 08:46:51
 * @Description 该类由CodeGenerator自动创建, 版权归 http://www.j2ee.org.cn 所有.
 */
@Controller
@Transactional
@RequestMapping("/tbAccountInfo")
public class TbAccountInfoController {

	@Autowired
	private TbAccountInfoService tbAccountInfoService;
	
	// 跳转到添加页面
	@RequestMapping("/addPage")
	public ModelAndView addPage() throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		return new ModelAndView("tbAccountInfo/tbAccountInfo-add", context);
	}

	// 跳转到编辑页面
	@RequestMapping("/editPage")
	public ModelAndView editPage(java.lang.String id) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("model", tbAccountInfoService.getById(id));
		context.put("nowDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return new ModelAndView("tbAccountInfo/tbAccountInfo-edit", context);
	}
	
	// 跳转到模块主页面
	@RequestMapping("/mainPage")
	public ModelAndView mainPage() throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		
		//每次刷新生成20条数据
		for(int i = 0; i < 20; i++) {
			tbAccountInfoService.save(
				new TbAccountInfoModel.Builder()
					.setUuid(String.valueOf(UUID.randomUUID()))
					.setUsername(RandomUtil.randomStr(12))
					.setNickname("SB-"+RandomUtil.randomCnStr(5))
					.setPassword(RandomUtil.randomStr(9))
					.setEmail(RandomUtil.randomStr(5)+"@qq.com")
					.setPhone(String.valueOf(i*i*i))
					.setQQ(String.valueOf(i*(i-1)*i))
					.setRemark(RandomUtil.randomCnStr(40))
					.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
					.setAddress(RandomUtil.randomCnStr(12))
					.create()
			);
		}
		return new ModelAndView("tbAccountInfo/tbAccountInfo-main", context);
	}
	
	// 跳转到导出页面
	@RequestMapping("/exportPage")
	public ModelAndView exportPage() throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		return new ModelAndView("tbAccountInfo/tbAccountInfo-export", context);
	}
	
	// 获取实体Export属性 
	@RequestMapping("/getExportProperties")
	public @ResponseBody List<Object> getExportProperties() throws Exception {
		return new TbAccountInfoModel().getExport("id", "text");
	}
	
	@RequestMapping("/doExport")
	public void doExport(String exportConfig,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//导出类型：写死，页面上懒得写了
		ExcelType excelType = ExcelType.EXCEL2007;
		//文件名
		String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-账号信息" + ExcelUtil.getExcelSuffix(excelType);
		//工作表名称
		String sheetName = "账号信息";
		
		
		//采集导出项,是name,display,width|name,display...形式的，需要进一步处理
		List<String> items = Arrays.asList(exportConfig.split("[|]"));
		
		//准备存储导出项的集合
		List<ExportItem> exportItems = new ArrayList<ExportItem>();
		for(int i = 0; i < items.size(); i++) {
			String[] strings = items.get(i).split(",");
			exportItems.add(
				new ExportItem.Builder()
					.setName(strings[0])
					.setDisplay(strings[1])
					.setSort(i)
					.setWidth(Short.valueOf(strings[2]))
					.create()
			);
		}
		
		//准备数据
		List<TbAccountInfoModel> accountInfoModels = tbAccountInfoService.getAll();
		
		ExportUtil<TbAccountInfoModel> exportUtil = new ExportUtil<>();
		
		//开始导出
		exportUtil.setDownloadResponse(request, response, fileName,excelType);
		exportUtil.exportExcel(response.getOutputStream(), excelType, sheetName, exportItems, accountInfoModels);
	}
	
	
	// 获取EasyUI datagrid 数据, 未实现分页以及动态查询
	@RequestMapping("/getGridData")
	public @ResponseBody Map<String, Object> getGridData() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		//TODO 此处暂时不考虑分页实现,等后续封装完毕, 可修改此代码模板
		List<TbAccountInfoModel> dataList = tbAccountInfoService.getAll();
		
		jsonMap.put("rows", dataList);
		jsonMap.put("total", dataList != null ? dataList.size() : 0);
		return jsonMap;
	}
	
	//新增或更新
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public @ResponseBody JsonView saveOrUpdate(TbAccountInfoModel model)
		throws Exception {
		//新增
		if(StringUtils.isNullOrEmpty(model.getUuid())) {
			model.setUuid(String.valueOf(UUID.randomUUID()));
			
			if(StringUtils.isNullOrEmpty(model.getCreateDate())) {
				model.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			}
			
			tbAccountInfoService.save(model);
		} else {
			//修改
			tbAccountInfoService.updateBySelective(model);
		}
		return new JsonView(true, "操作完成!");
	}
	
	//删除,支持批量删除
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody JsonView delete(@RequestParam("ids[]") List<java.lang.String> ids)
		throws Exception {
		for (java.lang.String id : ids) {
			tbAccountInfoService.delete(id);
		}
		return new JsonView(true, "操作完成!");
	}

}