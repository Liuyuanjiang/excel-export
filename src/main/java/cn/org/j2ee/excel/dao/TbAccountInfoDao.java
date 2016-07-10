package cn.org.j2ee.excel.dao;

import cn.org.j2ee.excel.model.TbAccountInfoModel;
import java.util.List;

/**
 * @TypeName 账号信息 DAO接口声明
 * @TableName tb_account_info
 * @Author wuwz@live.com
 * @DateTime 2016-7-10 08:46:51
 * @Description 该类由CodeGenerator自动创建, 版权归 http://www.j2ee.org.cn 所有.
 */
public interface TbAccountInfoDao {
	
	/**
	 * 保存 账号信息, 保存后, tbAccountInfoModel对象将填充其ID值
	 * @param tbAccountInfoModel
	 */
	public void save(TbAccountInfoModel tbAccountInfoModel);
	
	/**
	 * 删除 账号信息
	 * @param id
	 */
	public void delete(java.lang.String id);
	
	/**
	 * 更新 账号信息
	 * @param tbAccountInfoModel
	 */
	public void update(TbAccountInfoModel tbAccountInfoModel);
	
	/**
	 * 更新 账号信息, 只更新不为空的字段
	 * @param tbAccountInfoModel
	 */
	public void updateBySelective(TbAccountInfoModel tbAccountInfoModel);
	
	/**
	 * 根据ID获取 账号信息
	 * @param id
	 * @return TbAccountInfoModel
	 */
	public TbAccountInfoModel getById(java.lang.String id);
	
	/**
	 * 获取所有 账号信息
	 * @return 
	 */
	public List<TbAccountInfoModel> getAll();
}
