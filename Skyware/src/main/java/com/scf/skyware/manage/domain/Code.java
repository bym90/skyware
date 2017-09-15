package com.scf.skyware.manage.domain;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Code
{
	private String codeId;
	private String codeNm;
	private String codeDiv;
	private String codeDivNm;
	private int codeSort;
	private String codeDescription;
	private String codeUrl;
	private Timestamp codeRegDate;
	private String useYn;
	
	public String getCodeId()
	{
		return codeId;
	}
	
	public void setCodeId(String codeId)
	{
		this.codeId = codeId;
	}
	
	public String getCodeNm()
	{
		return codeNm;
	}
	
	public void setCodeNm(String codeNm)
	{
		this.codeNm = codeNm;
	}
	
	public String getCodeDiv()
	{
		return codeDiv;
	}
	
	public void setCodeDiv(String codeDiv)
	{
		this.codeDiv = codeDiv;
	}
	
	public String getCodeDivNm()
	{
		return codeDivNm;
	}
	
	public void setCodeDivNm(String codeDivNm)
	{
		this.codeDivNm = codeDivNm;
	}
	
	public int getCodeSort()
	{
		return codeSort;
	}
	
	public void setCodeSort(int codeSort)
	{
		this.codeSort = codeSort;
	}
	
	public String getCodeDescription()
	{
		return codeDescription;
	}
	
	public void setCodeDescription(String codeDescription)
	{
		this.codeDescription = codeDescription;
	}
	
	public Timestamp getCodeRegDate()
	{
		return codeRegDate;
	}
	
	public void setCodeRegDate(Timestamp codeRegDate)
	{
		this.codeRegDate = codeRegDate;
	}
	
	public String getUseYn()
	{
		return useYn;
	}
	
	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	public String getCodeUrl()
	{
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl)
	{
		this.codeUrl = codeUrl;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
