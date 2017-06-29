package com.sas.pdg.mrr.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;

import com.josephoconnell.html.HTMLInputFilter;
import com.sas.pdg.mrr.dao.FriendDAO;
import com.sas.pdg.mrr.dao.UserDAO;
import com.sas.pdg.mrr.dto.FriendDTO;
import com.sas.pdg.mrr.dto.UserDTO;
import com.sas.pdg.mrr.service.IdentityService;
import com.sas.pdg.mrr.util.EncryptUtil;
import com.sas.pdg.mrr.util.SpringMailUtil;

public class IdentityServiceImpl implements IdentityService {
	private static final int SUPERADMIN = -1;
	private static final int ADMIN = -2;
	private static final int USER = 0;
	private static final int DAREN_USER = 1;
	private static final int ORG_USER = 2;

	private static final int GBIN1_UID = 2;

	private static final int GUESTID = 0;
	private static final String GUESTMAIL = "GUEST@gbtags.com";
	private static final String GUESTFULLNAME = "访客";

	private static final int FOLLOWFLAG = 1;
	private static final int DELETED = 1;
	private static final int UNDELETED = 0;
	private static final int UNACTIVIATED = -1;
	private static final int DRAFT = -1;
	private static final int REQUEST = -2;
	private static final int ISREAD = 0;

	private static final int DAREN_FLAG = 1;
	private static final int NOT_DAREN_FLAG = 0;

	private static final int UNREAD = 0;
	private static final int READ = 1;

	private static final int TAG_OFFICIAL = 1;

	private static final int COIN_TYPE_ARTICLE = 1;
	private static final int COIN_TYPE_CODE = 2;
	private static final int COIN_TYPE_LIBRARY = 3;
	private static final int COIN_TYPE_POST_VOTING = 4;
	private static final int COIN_TYPE_GUAGUALE = 5;
	private static final int COIN_TYPE_XIAOFEI = 6; // 金币消费

	private static final int COIN_TYPE_ARTICLE_NUM = 100;
	private static final int COIN_TYPE_CODE_NUM = 30;
	private static final int COIN_TYPE_LIBRARY_NUM = 300;
	private static final int COIN_TYPE_POST_VOTING_NUM = 1;

	private static final int ACTIVITY_TYPE_FOLLOWTAG = 1;
	private static final int ACTIVITY_TYPE_COMMENT = 2;
	private static final int ACTIVITY_TYPE_COMMENT_VOTING_AGREE = 3;
	private static final int ACTIVITY_TYPE_COMMENT_VOTING_DISAGREE = 4;
	private static final int ACTIVITY_TYPE_AT_TO_OTHER = 5;
	private static final int ACTIVITY_TYPE_ADDFAVORIATE = 6;
	private static final int ACTIVITY_TYPE_REQUESTLIVECLASS = 7;
	private static final int ACTIVITY_TYPE_REQUESTONLINETUTOR = 8;

	private static final int ACTIVITY_TYPE_TUTORIAL_COMMENT = 9;
	private static final int ACTIVITY_TYPE_CODEREPLAY_COMMENT = 10;

	private static final int ACTIVITY_TYPE_COIN_TRADE = 11;

	private static final int ACTIVITY_TYPE_COIN_SPENDING = 12;

	private static final int INVITATION_FLAG_AVAILABLE = 0;
	private static final int INVITATION_FLAG_NOT_AVAILABLE = 1;
	private static final int SEND_COUNT = 3;// 邮箱验证码发送次数

	private UserDAO userDAO;
	private FriendDAO friendDAO;
	
	private JavaMailSender javaMailSender;
	private SpringMailUtil springMailUtil;

	private int userID = GUESTID;
	
	public FriendDAO getFriendDAO(){
		return this.friendDAO;
	}
	
	public void setFriendDAO(FriendDAO friendDAO){
		this.friendDAO = friendDAO;
	}
	
	
	public int addFriend(FriendDTO dto){
		return friendDAO.addFriend(dto);
	}
	
	public List findAllFriendS(){
		List list = friendDAO.findAllFriendD();
		return list;
	}
	
	public int updateFriendByUid(FriendDTO dto){
		return friendDAO.updateFriendByUid(dto);
	}
	
	public List updateAndRefresh(FriendDTO dto){
		int n = friendDAO.updateFriendByUid(dto);
		List list = null;
		if(n > 0){
			list = friendDAO.findAllFriendD();
		}
		return list;
	}
	
	

	private UserDTO sessionHandler(HttpServletRequest request) {
		HttpSession userSession = request.getSession(false);
		Object onlineuserobj = null;
		if (userSession != null) {
			onlineuserobj = userSession.getAttribute("system.userinfo");
		}

		UserDTO onlineuser = null;
		if (onlineuserobj != null) {
			onlineuser = (UserDTO) onlineuserobj;
		} else {
			onlineuser = new UserDTO();
			onlineuser.setUid(GUESTID);
			onlineuser.setFullname("GUESTFULLNAME");
		}
		return onlineuser;
	}

	/* Java Service Mapping for User Module */
	// email查询用户
	public UserDTO findUserByMail(String mail) {
		return userDAO.findUserByMail(mail);
	}
	
	// 管理员分页查询全部用户
	public List findAllUsersByPageForAdmin(Map map) {
		List list = userDAO.findAllUsersByPageForAdmin(map);
		return list;
	}
	
	public int saveRoleAdminForAdmin(int uid, int roleid, List list,
			HttpServletRequest request) {
		UserDTO onlineuser = sessionHandler(request);
		
		/*
		if (onlineuser.getRoleid() != -1)
			return 0;
		*/

		int returnval = 0;

		UserDTO dto = new UserDTO();
		dto.setUid(uid);
		dto.setRoleid(roleid);
		returnval = userDAO.updateRoleidByUid(dto);

		return returnval;
	}

	/* set and get method for DAO */


	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public SpringMailUtil getSpringMailUtil() {
		return springMailUtil;
	}

	public void setSpringMailUtil(SpringMailUtil springMailUtil) {
		this.springMailUtil = springMailUtil;
	}
	
	// Java email setting
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	// utility methods
	private String htmlFilter(String s) {
		return new HTMLInputFilter(true).filter(s);
	}

	// 为密码加密
	public String PasswordEncryption(String pwd) {
		EncryptUtil encrypt = new EncryptUtil();
		String e_pwd = "";
		try {
			e_pwd = encrypt.base64Encoder(pwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return e_pwd;
	}
}