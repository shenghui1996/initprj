package com.sas.pdg.mrr.dto;

import java.util.Date;

public class UserDTO implements java.io.Serializable {
	private int uid;
	private String tokenId;// 第三方tokenId
	private String thirdUId;// 第三方UID
	private String fullname;
	private String truename;
	private String sitename;
	private String mail;
	private String avatar;
	private String background;
	private String university;
	private String company;
	private String password;
	private String qq;
	private String msn;
	private String weibo;
	private String website;
	private String mobile;
	private int deleted;
	private Date creationdate;
	private Date lastlogindate;
	private int roleid;
	private int inttagflag;
	private int protagflag;
	private int timelineid;
	private String clientid;
	private Integer channeltype;// 登陆渠道
	private Integer sex;// 性别
	private String address;// 地址
	private String disalbesendmsg;// 禁言
	private Date lastdisalbesendmsgtime;// 禁言的时间,24h后自动恢复发言
	private String headImgUrl;// 用户头像

	private String vericode;
	private int totalcoin;
	private Date lastlotterydate;

	private String selfintro;

	private Date lastlogindateforreminder;

	private String accessurl;
	private Integer validatemailcount;// 邮箱验证次数
	private Date expirationdate;// 高级用户到期日期
	
	private String signature;
	private String wechat;
	
	/* prj */
	private String prjname;
	private String prjindustry;
	private String prjcompany;
	private String prjaddress;
	private String prjcity;
	private String prjlawowner;
	private String prjstaffnum;
	private String prjstage;
	private String prjmoney;
	private String prjmoneyhistory;
	private String prjteambg;
	private String prjdesc;
	private String prjhighlight;
	private String prjurl;
	private String prjbp;
	
	/* aginfo */
	private String agname;
	private String agaddress;
	private String agmoney;
	private String agfield;
	private String agstage;
	private String agmoneyrange;
	private String agurl;
	private String agdesc;
	private String agcase;
	private String agnamecard;

	public String getPrjname() {
		return prjname;
	}

	public void setPrjname(String prjname) {
		this.prjname = prjname;
	}
	
	public String getPrjindustry() {
		return prjindustry;
	}

	public void setPrjindustry(String prjindustry) {
		this.prjindustry = prjindustry;
	}
	
	public String getPrjcompany() {
		return prjcompany;
	}

	public void setPrjcompany(String prjcompany) {
		this.prjcompany = prjcompany;
	}
	
	public String getPrjaddress() {
		return prjaddress;
	}

	public void setPrjaddress(String prjaddress) {
		this.prjaddress = prjaddress;
	}
	
	public String getPrjcity() {
		return prjcity;
	}

	public void setPrjcity(String prjcity) {
		this.prjcity = prjcity;
	}

	public String getPrjlawowner() {
		return prjlawowner;
	}

	public void setPrjlawowner(String prjlawowner) {
		this.prjlawowner = prjlawowner;
	}
	
	public String getPrjstaffnum() {
		return prjstaffnum;
	}

	public void setPrjstaffnum(String prjstaffnum) {
		this.prjstaffnum = prjstaffnum;
	}
	
	public String getPrjstage() {
		return prjstage;
	}

	public void setPrjstage(String prjstage) {
		this.prjstage = prjstage;
	}
	
	public String getPrjmoney() {
		return prjmoney;
	}

	public void setPrjmoney(String prjmoney) {
		this.prjmoney = prjmoney;
	}
	
	public String getPrjmoneyhistory() {
		return prjmoneyhistory;
	}

	public void setPrjmoneyhistory(String prjmoneyhistory) {
		this.prjmoneyhistory = prjmoneyhistory;
	}

	public String getPrjteambg() {
		return prjteambg;
	}

	public void setPrjteambg(String prjteambg) {
		this.prjteambg = prjteambg;
	}
	
	public String getPrjdesc() {
		return prjdesc;
	}

	public void setPrjdesc(String prjdesc) {
		this.prjdesc = prjdesc;
	}
	
	public String getPrjhighlight() {
		return prjhighlight;
	}

	public void setPrjhighlight(String prjhighlight) {
		this.prjhighlight = prjhighlight;
	}

	public String getPrjurl() {
		return prjurl;
	}

	public void setPrjurl(String prjurl) {
		this.prjurl = prjurl;
	}
	
	public String getPrjbp() {
		return prjbp;
	}

	public void setPrjbp(String prjbp) {
		this.prjbp = prjbp;
	}
	
	/* ag */
	public String getAgname() {
		return agname;
	}

	public void setAgname(String agname) {
		this.agname = agname;
	}
	
	public String getAgaddress() {
		return agaddress;
	}

	public void setAgaddress(String agaddress) {
		this.agaddress = agaddress;
	}
	
	public String getAgmoney() {
		return agmoney;
	}

	public void setAgmoney(String agmoney) {
		this.agmoney = agmoney;
	}
	
	public String getAgfield() {
		return agfield;
	}

	public void setAgfield(String agfield) {
		this.agfield = agfield;
	}

	public String getAgstage() {
		return agstage;
	}

	public void setAgstage(String agstage) {
		this.agstage = agstage;
	}

	public String getAgmoneyrange() {
		return agmoneyrange;
	}

	public void setAgmoneyrange(String agmoneyrange) {
		this.agmoneyrange = agmoneyrange;
	}
	
	public String getAgurl() {
		return agurl;
	}

	public void setAgurl(String agurl) {
		this.agurl = agurl;
	}
	
	public String getAgdesc() {
		return agdesc;
	}

	public void setAgdesc(String agdesc) {
		this.agdesc = agdesc;
	}

	public String getAgcase() {
		return agcase;
	}

	public void setAgcase(String agcase) {
		this.agcase = agcase;
	}
	
	public String getAgnamecard() {
		return agnamecard;
	}

	public void setAgnamecard(String agnamecard) {
		this.agnamecard = agnamecard;
	}	
	
	
	
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public int getTimelineid() {
		return timelineid;
	}

	public void setTimelineid(int timelineid) {
		this.timelineid = timelineid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getLastlogindate() {
		return lastlogindate;
	}

	public void setLastlogindate(Date lastlogindate) {
		this.lastlogindate = lastlogindate;
	}

	public int getProtagflag() {
		return protagflag;
	}

	public void setProtagflag(int protagflag) {
		this.protagflag = protagflag;
	}

	public int getInttagflag() {
		return inttagflag;
	}

	public void setInttagflag(int inttagflag) {
		this.inttagflag = inttagflag;
	}

	public String getSelfintro() {
		return selfintro;
	}

	public void setSelfintro(String selfintro) {
		this.selfintro = selfintro;
	}

	public Date getLastlogindateforreminder() {
		return lastlogindateforreminder;
	}

	public void setLastlogindateforreminder(Date lastlogindateforreminder) {
		this.lastlogindateforreminder = lastlogindateforreminder;
	}

	public String getAccessurl() {
		return accessurl;
	}

	public void setAccessurl(String accessurl) {
		this.accessurl = accessurl;
	}

	public String getVericode() {
		return vericode;
	}

	public void setVericode(String vericode) {
		this.vericode = vericode;
	}

	public int getTotalcoin() {
		return totalcoin;
	}

	public void setTotalcoin(int totalcoin) {
		this.totalcoin = totalcoin;
	}

	public Date getLastlotterydate() {
		return lastlotterydate;
	}

	public void setLastlotterydate(Date lastlotterydate) {
		this.lastlotterydate = lastlotterydate;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getThirdUId() {
		return thirdUId;
	}

	public void setThirdUId(String thirdUId) {
		this.thirdUId = thirdUId;
	}

	public Integer getChanneltype() {
		return channeltype;
	}

	public void setChanneltype(Integer channeltype) {
		this.channeltype = channeltype;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDisalbesendmsg() {
		return disalbesendmsg;
	}

	public void setDisalbesendmsg(String disalbesendmsg) {
		this.disalbesendmsg = disalbesendmsg;
	}

	public Date getLastdisalbesendmsgtime() {
		return lastdisalbesendmsgtime;
	}

	public void setLastdisalbesendmsgtime(Date lastdisalbesendmsgtime) {
		this.lastdisalbesendmsgtime = lastdisalbesendmsgtime;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Integer getValidatemailcount() {
		return validatemailcount;
	}

	public void setValidatemailcount(Integer validatemailcount) {
		this.validatemailcount = validatemailcount;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

}