package com.scf.skyware.main.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class User
{
	private String userNo;
	private String userId;
	private String userPw;
	private String userNm;
	private String userEmail;
	private String userPhone;
	private String userGender;
	private String userAddrFull;
	private String userAddrFullRoad;
	private String userAddrSido;
	private String userAddrSigungu;
	private String userAddrEMD;			//읍면동
	private String userAddrJibun;			
	private String userAddrRoad;			//도로명
	private String userAddrBuldNo;			
	private String userAddrDetail;			
	private String userZipcode;
	private String userBirth;
	private String userSL;						//양,음력
	private Timestamp userRegDate;
	private Timestamp userModDate;
	private Timestamp userJoinDate;
	private String useYn;
	private String saveId;
	
	//페이징
	private int pageSize;
	private int totalPage;
	private int curPage;
	
	//검색조건
	private String schState;
	private String schType;
	private String schText;
	
	public String getUserNo()
	{
		return userNo;
	}

	public void setUserNo(String userNo)
	{
		this.userNo = userNo;
	}

	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserPw()
	{
		return userPw;
	}

	public void setUserPw(String userPw)
	{
		this.userPw = userPw;
	}

	public String getUserNm()
	{
		return userNm;
	}

	public void setUserNm(String userNm)
	{
		this.userNm = userNm;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}

	public String getUserPhone()
	{
		return userPhone;
	}

	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}

	public String getUserGender()
	{
		return userGender;
	}

	public void setUserGender(String userGender)
	{
		this.userGender = userGender;
	}

	public String getUserAddrFull()
	{
		return userAddrFull;
	}

	public void setUserAddrFull(String userAddrFull)
	{
		this.userAddrFull = userAddrFull;
	}

	public String getUserAddrSido()
	{
		return userAddrSido;
	}

	public void setUserAddrSido(String userAddrSido)
	{
		this.userAddrSido = userAddrSido;
	}

	public String getUserAddrSigungu()
	{
		return userAddrSigungu;
	}

	public void setUserAddrSigungu(String userAddrSigungu)
	{
		this.userAddrSigungu = userAddrSigungu;
	}

	public String getUserAddrEMD()
	{
		return userAddrEMD;
	}

	public void setUserAddrEMD(String userAddrEMD)
	{
		this.userAddrEMD = userAddrEMD;
	}

	public String getUserAddrRoad()
	{
		return userAddrRoad;
	}

	public void setUserAddrRoad(String userAddrRoad)
	{
		this.userAddrRoad = userAddrRoad;
	}

	public String getUserBirth()
	{
		return userBirth;
	}

	public void setUserBirth(String userBirth)
	{
		this.userBirth = userBirth;
	}

	public String getUserSL()
	{
		return userSL;
	}

	public void setUserSL(String userSL)
	{
		this.userSL = userSL;
	}

	public Timestamp getUserRegDate()
	{
		return userRegDate;
	}

	public void setUserRegDate(Timestamp userRegDate)
	{
		this.userRegDate = userRegDate;
	}

	public Timestamp getUserModDate()
	{
		return userModDate;
	}

	public void setUserModDate(Timestamp userModDate)
	{
		this.userModDate = userModDate;
	}

	public Timestamp getUserJoinDate()
	{
		return userJoinDate;
	}

	public void setUserJoinDate(Timestamp userJoinDate)
	{
		this.userJoinDate = userJoinDate;
	}

	public String getUseYn()
	{
		return useYn;
	}

	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	public String getSaveId()
	{
		return saveId;
	}

	public void setSaveId(String saveId)
	{
		this.saveId = saveId;
	}

	public String getUserZipcode()
	{
		return userZipcode;
	}

	public void setUserZipcode(String userZipcode)
	{
		this.userZipcode = userZipcode;
	}

	public String getUserAddrFullRoad()
	{
		return userAddrFullRoad;
	}

	public void setUserAddrFullRoad(String userAddrFullRoad)
	{
		this.userAddrFullRoad = userAddrFullRoad;
	}

	public String getUserAddrJibun()
	{
		return userAddrJibun;
	}

	public void setUserAddrJibun(String userAddrJibun)
	{
		this.userAddrJibun = userAddrJibun;
	}

	public String getUserAddrBuldNo()
	{
		return userAddrBuldNo;
	}

	public void setUserAddrBuldNo(String userAddrBuldNo)
	{
		this.userAddrBuldNo = userAddrBuldNo;
	}

	public String getUserAddrDetail()
	{
		return userAddrDetail;
	}

	public void setUserAddrDetail(String userAddrDetail)
	{
		this.userAddrDetail = userAddrDetail;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getTotalPage()
	{
		return totalPage;
	}

	public void setTotalPage(int totalPage)
	{
		this.totalPage = totalPage;
	}

	public int getCurPage()
	{
		return curPage;
	}

	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}
	
	public String getSchState()
	{
		return schState;
	}

	public void setSchState(String schState)
	{
		this.schState = schState;
	}

	public String getSchType()
	{
		return schType;
	}

	public void setSchType(String schType)
	{
		this.schType = schType;
	}

	public String getSchText()
	{
		return schText;
	}

	public void setSchText(String schText)
	{
		this.schText = schText;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
