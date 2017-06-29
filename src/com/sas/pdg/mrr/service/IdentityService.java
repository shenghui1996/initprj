package com.sas.pdg.mrr.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sas.pdg.mrr.dto.FriendDTO;
import com.sas.pdg.mrr.dto.UserDTO;

public interface IdentityService
{
	UserDTO findUserByMail(String mail);
	
	String PasswordEncryption(String pwd);
	
	List findAllUsersByPageForAdmin(Map map);
	
	int saveRoleAdminForAdmin(int uid, int roleid, List list,
			HttpServletRequest request);
	
	int addFriend(FriendDTO dto);
	
	List findAllFriendS();
	
	int updateFriendByUid(FriendDTO dto);
	
	List updateAndRefresh(FriendDTO dto);
	
}
