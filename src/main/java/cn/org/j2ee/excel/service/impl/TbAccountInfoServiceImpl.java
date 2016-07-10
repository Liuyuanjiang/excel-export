package cn.org.j2ee.excel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.j2ee.excel.dao.TbAccountInfoDao;
import cn.org.j2ee.excel.model.TbAccountInfoModel;
import cn.org.j2ee.excel.service.TbAccountInfoService; 

/**
 * @TypeName 账号信息 Service接口实现类
 * @TableName tb_account_info
 * @Author wuwz@live.com
 * @DateTime 2016-7-10 08:46:51
 * @Description 该类由CodeGenerator自动创建, 版权归 http://www.j2ee.org.cn 所有.
 */
@Service("tbAccountInfoService")
public class TbAccountInfoServiceImpl implements TbAccountInfoService {

	@Autowired
	private TbAccountInfoDao tbAccountInfoDao;
	
	@Override
	public void save(TbAccountInfoModel tbAccountInfoModel) {
		tbAccountInfoDao.save(tbAccountInfoModel);
	}

	@Override
	public void delete(java.lang.String id) {
		tbAccountInfoDao.delete(id);
	}

	@Override
	public void update(TbAccountInfoModel tbAccountInfoModel) {
		tbAccountInfoDao.update(tbAccountInfoModel);
	}

	@Override
	public void updateBySelective(TbAccountInfoModel tbAccountInfoModel) {
		tbAccountInfoDao.updateBySelective(tbAccountInfoModel);
	}

	@Override
	public TbAccountInfoModel getById(java.lang.String id) {
		return tbAccountInfoDao.getById(id);
	}

	@Override
	public List<TbAccountInfoModel> getAll() {
		return tbAccountInfoDao.getAll();
	}

}