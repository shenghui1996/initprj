package com.sas.pdg.mrr.dao;

import java.util.List;
import java.util.Map;

import com.sas.pdg.mrr.dto.UserDTO;

public class UserDAO extends BaseDAO {
	// 更新所有用户字段
	public int updateUserWizardByUid(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateUserWizardByUid", dto);
	}

	// 更新所有用户字段
	public int updateUser(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateByUid", dto);
	}

	public int updateUserBaseInfo(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateUserBaseInfoByUid", dto);
	}

	// 更新邮件验证
	public int updateUserByVericode(String vericode) {
		return getSqlMapClientTemplate().update("updateUserByVericode",
				vericode);
	}

	// 更新所有用户字段
	public int updatePassword(UserDTO dto) {
		return getSqlMapClientTemplate().update("updatePasswordByUid", dto);
	}

	// 更新头像字段
	public int updateUserAvatar(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateAvatarByUid", dto);
	}

	// 更新头像字段
	public int updateUserBackground(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateBackgroundByUid", dto);
	}

	// 新用户更新兴趣Tag和专业Tag标识
	public int updateUserIntTagFlag(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateUserIntTagFlagByUid",
				dto);
	}

	public int updateUserProTagFlag(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateUserProTagFlagByUid",
				dto);
	}

	public int updateSiteNameByUid(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateSiteNameByUid", dto);
	}

	public int updateLastLoginByUid(UserDTO dto) {
		return getSqlMapClientTemplate()
				.update("updateLastloginDateByUid", dto);
	}

	public int updateTimelineIDByUid(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateTimelineIDByUid", dto);
	}

	public int updateUserTotalCoinByUid(UserDTO dto) {
		return getSqlMapClientTemplate()
				.update("updateUserTotalCoinByUid", dto);
	}

	public int updateUserLastLotteryDateByUid(int uid) {
		return getSqlMapClientTemplate().update(
				"updateUserLastLotteryDateByUid", uid);
	}

	// 管理员更新用户角色
	public int updateRoleidByUid(UserDTO dto) {
		return getSqlMapClientTemplate().update("updateRoleidByUid", dto);
	}

	public void removeUser(int fid)

	{
		getSqlMapClientTemplate().delete("deleteUser", fid);
	}

	public UserDTO findUserByUid(String uid) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserByUid", uid);
	}

	public UserDTO findUserLotteryByUid(String uid) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserLotteryByUid", uid);
	}

	public UserDTO findVericodeByUid(String uid) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryVericodeByUid", uid);
	}

	public UserDTO findUserByFullname(String fullname) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserByFullname", fullname);
	}

	public UserDTO findOtherUserByUid(String uid) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryOtherUserByUid", uid);
	}

	public UserDTO findUserBySiteName(String sitename) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserBySiteName", sitename);
	}

	/* reset password */
	public UserDTO findUserByFullnameAndEmail(UserDTO dto) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserByFullnameAndEmail", dto);
	}

	/*
	 * public UserDTO findUserByLoginInfo(UserDTO dto) { return (UserDTO)
	 * getSqlMapClientTemplate().queryForObject("queryUserByLoginInfo", dto); }
	 */

	public UserDTO findUserByMail(String mail) {
		return (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserByMail", mail);
	}

	public int insertUser(UserDTO dto) {
		return Integer.parseInt(this.getSqlMapClientTemplate()
				.insert("insertUser", dto).toString());
	}

	public List findUsers() {
		return (List) getSqlMapClientTemplate().queryForList("queryAllUser");
	}

	public List findAllFollowUsers(String uid) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllFollowUser", uid);
	}

	public List findAllRCTeacher(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryUserByRoleID", map);
	}

	public List findAllUsersByPage(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllUsersByPage", map);
	}

	public List findAllUsersByPageForAdmin(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllUsersByPageForAdmin", map);
	}
	
	public List findAllAgsByPage(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllAgsByPage", map);
	}

	public List findAllLastLoginUsersByPageForAdmin(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllLastLoginUsersByPageForAdmin", map);
	}

	public List findAllRecommendUsersForLoginUser(Map map) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryAllRecommendUsersForLoginUser", map);
	}

	public int findAllUsersSize(UserDTO dto) {
		if (getSqlMapClientTemplate().queryForObject("queryAllUsersSize", dto) != null) {
			return (Integer) getSqlMapClientTemplate().queryForObject(
					"queryAllUsersSize", dto);
		} else {
			return 0;
		}
	}

	public List findUsersByUserNames(List list) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryUsersByUserNames", list);
	}

	public List findUsersByUserIds(List list) {
		return (List) getSqlMapClientTemplate().queryForList(
				"queryUsersByUserIds", list);
	}

	/**
	 * 根据第三方的UID查询是否存在此用户
	 * 
	 * @param thridUId
	 * @return tokenUserDTO
	 */
	public UserDTO findUserByTokenId(String thridUId) {
		UserDTO tokenUserDTO = (UserDTO) getSqlMapClientTemplate()
				.queryForObject("queryTokenUser", thridUId);
		return tokenUserDTO;
	}

	public String findThirdUidById(String uid) {
		String thridId = (String) getSqlMapClientTemplate().queryForObject(
				"queryThirdId", uid);
		return thridId;
	}

	/**
	 * 更新第三方登陆用户信息
	 * 
	 * @param tokenUser
	 * @return
	 */
	public int updateTokenUserByThirdUid(UserDTO tokenUser) {

		int updateResult = getSqlMapClientTemplate().update(
				"updateTokenUserByThridUid", tokenUser);

		return updateResult;
	}

	/**
	 * 根据不同的渠道查询用户是否存在
	 * 
	 * @param paraMap
	 * @return userDTO
	 */
	public UserDTO findUserByChannelId(Map<String, Object> paraMap) {
		UserDTO userDTO = (UserDTO) getSqlMapClientTemplate().queryForObject(
				"queryUserByAccountId", paraMap);
		return userDTO;
	}

	/**
	 * 更新发送验证码的次数
	 * 
	 * @param userDTO
	 * @return count
	 */
	public int updateUserSendEmailCount(UserDTO userDTO) {

		int updateCount = getSqlMapClientTemplate().update(
				"updateValidateEmailCount", userDTO);
		return updateCount;
	}

	public int updateUserExpirationDate(Map<String, Object> paraMap) {
		int updateResult = getSqlMapClientTemplate().update("updateEndDate",
				paraMap);
		return updateResult;
	}

	/**
	 * 保存第三方登录信息
	 * 
	 * @param userDTO
	 * @return saveResult
	 */
	public int saveThirdPartyLoginUserInfo(UserDTO userDTO) {
		int saveResult = Integer.parseInt(getSqlMapClientTemplate().insert(
				"insertThirdPatryUser", userDTO).toString());
		return saveResult;
	}
	
	/*
	 * prj&ag info
	 */
	
	public int savePrjInfoByUid(UserDTO userDTO) {		
		int updateCount = getSqlMapClientTemplate().update(
				"updatePrjInfoByUid", userDTO);
		return updateCount;
	}
	
	public int saveAgInfoByUid(UserDTO userDTO) {
		int updateCount = getSqlMapClientTemplate().update(
				"updateAgInfoByUid", userDTO);
		return updateCount;
	}
}
