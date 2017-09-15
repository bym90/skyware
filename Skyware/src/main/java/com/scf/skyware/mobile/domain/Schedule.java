package com.scf.skyware.mobile.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Schedule {

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getSchCateDiv() {
		return schCateDiv;
	}

	public void setSchCateDiv(String schCateDiv) {
		this.schCateDiv = schCateDiv;
	}

	public String getSchWriterId() {
		return schWriterId;
	}

	public void setSchWriterId(String schWriterId) {
		this.schWriterId = schWriterId;
	}

	public Date getBeforeMonth() {
		return beforeMonth;
	}

	public void setBeforeMonth(Date beforeMonth) {
		this.beforeMonth = beforeMonth;
	}

	public Date getAfterMonth() {
		return afterMonth;
	}

	public void setAfterMonth(Date afterMonth) {
		this.afterMonth = afterMonth;
	}

	public String getSchCate1() {
		return schCate1;
	}

	public void setSchCate1(String schCate1) {
		this.schCate1 = schCate1;
	}

	public String getSchAllDay() {
		return schAllDay;
	}

	public void setSchAllDay(String schAllDay) {
		this.schAllDay = schAllDay;
	}

	public String getSchNo() {
		return schNo;
	}

	public void setSchNo(String schNo) {
		this.schNo = schNo;
	}

	public String getSchCate2() {
		return schCate2;
	}

	public void setSchCate2(String schCate2) {
		this.schCate2 = schCate2;
	}

	public String getSchTitle() {
		return schTitle;
	}

	public void setSchTitle(String schTitle) {
		this.schTitle = schTitle;
	}

	public String getSchBody() {
		return schBody;
	}

	public void setSchBody(String schBody) {
		this.schBody = schBody;
	}

	public String getSchMemo() {
		return schMemo;
	}

	public void setSchMemo(String schMemo) {
		this.schMemo = schMemo;
	}

	public int getSchCompletion() {
		return schCompletion;
	}

	public void setSchCompletion(int schCompletion) {
		this.schCompletion = schCompletion;
	}

	public Date getSchStartDate() {
		return schStartDate;
	}

	public void setSchStartDate(Date schStartDate) {
		this.schStartDate = schStartDate;
	}

	public Date getSchEndDate() {
		return schEndDate;
	}

	public void setSchEndDate(Date schEndDate) {
		this.schEndDate = schEndDate;
	}

	public Date getSchSaveDate() {
		return schSaveDate;
	}

	public void setSchSaveDate(Date schSaveDate) {
		this.schSaveDate = schSaveDate;
	}

	public String getSchIsShow() {
		return schIsShow;
	}

	public void setSchIsShow(String schIsShow) {
		this.schIsShow = schIsShow;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	};

	String schNo;
	String schCateDiv;
	String schCate1;
	String schCate2;
	String schTitle;
	String schBody;
	String schMemo;
	int schCompletion;
	String schWriterId;
	Date schStartDate;
	Date schEndDate;
	Date beforeMonth;
	Date afterMonth;
	Date schSaveDate;
	String schAllDay;
	String schIsShow;
	String userNm;
}
