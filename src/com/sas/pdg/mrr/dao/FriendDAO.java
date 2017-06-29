package com.sas.pdg.mrr.dao;

import java.util.List;
import java.util.Map;

import com.sas.pdg.mrr.dto.FriendDTO;
import com.sas.pdg.mrr.dto.UserDTO;

public class FriendDAO extends BaseDAO {
	
	public int addFriend(FriendDTO dto){
		return Integer.parseInt(getSqlMapClientTemplate().insert(
				"insertFriend", dto).toString());
	}
	
	public List findAllFriendD(){
		return getSqlMapClientTemplate().queryForList("findAllFriend");
	}
	
	public int updateFriendByUid(FriendDTO dto){
		return getSqlMapClientTemplate().update("updateFriendByUid",dto);
	}
	
}
