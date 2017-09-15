import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
//import { HTTP } from '@ionic-native/http';
//import { DBMeter } from '@ionic-native/db-meter';
//import { mysql } from 'mysql';
import { Http, Headers, RequestOptions } from '@angular/http';
//import { BrowserModule } from '@angular/platform-browser';
//import { NgModule, ErrorHandler } from '@angular/core';
//import { HttpModule } from '@angular/http';
import 'rxjs/add/operator/map';
//import { AlertController } from 'ionic-angular'
import { ModalController } from 'ionic-angular';

import { AddressPage } from '../address/address';

//import { JusoPopupPage } from '../popup/jusoPopup';

@Component({
  selector: 'user-join',
  templateUrl: 'userJoin.html'
})
export class UserJoinPage
{
  userInfo: {
    userId: string,
    userPw: string,
    userPw2: string,
    userNm: string,
    userEmail: string,
    userPhone: string,
    userGender: string,
    userSL: string,
    userBirth: string,
    userAddrFullRoad: string,
    userAddrFull: string,
    userAddrDetail: string,
    userAddrSido: string,
    userAddrSigungu: string,
    userAddrEMD: string,
    userAddrJibun: string,
    userAddrRoad: string,
    userAddrBuldNo: string,
    userZipCode: string,
    idChk: string
  } = {
    userId: "",
    userPw: "",
    userPw2: "",
    userNm: "",
    userEmail: "",
    userPhone: "",
    userGender: "",
    userSL: "",
    userBirth: "",
    userAddrFullRoad: "",
    userAddrFull: "",
    userAddrDetail: "",
    userAddrSido: "",
    userAddrSigungu: "",
    userAddrEMD: "",
    userAddrJibun: "",
    userAddrRoad: "",
    userAddrBuldNo: "",
    userZipCode: "",
    idChk: "N"
  }

  constructor(public navCtrl: NavController, private http: Http, private modalCtrl: ModalController)
  {

  }

  //주소검색
  btnJusoSearch()
  {    
    if (this.userInfo.userAddrFull == "")
    {
      alert("검색어를 입력해주세요!");
      return false;
    }
    
    //modal창 띄우기
    let addrPage = this.modalCtrl.create( AddressPage, { keyword: this.userInfo.userAddrFull } );
    //modal창에서 데이터 전달
    addrPage.onDidDismiss(data =>{
      this.userInfo.userAddrFull = data.userAddrFull,
      this.userInfo.userAddrFullRoad = data.userAddrFullRoad,
      this.userInfo.userAddrSido = data.userAddrSido,
      this.userInfo.userAddrSigungu = data.userAddrSigungu,
      this.userInfo.userAddrEMD = data.userAddrEMD,
      this.userInfo.userAddrJibun = data.userAddrJibun,
      this.userInfo.userAddrRoad = data.userAddrRoad,
      this.userInfo.userAddrBuldNo = data.userAddrBuld,
      this.userInfo.userZipCode = data.userZipcode
    });
    addrPage.present();
  }

  //가입
  btnJoin()
  {
    // var userId = (<HTMLInputElement> document.getElementById("userId"));

    if (this.userInfo.userId.length == 0)
		{
			alert("아이디가 입력되지 않았습니다!");
			// this.userInfo.userId.focus();
			return false;
		}
		if (this.userInfo.userPw.length == 0)
		{
			alert("비밀번호가 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userPw2.length == 0)
		{
			alert("비밀번호 확인이 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userNm.length == 0)
		{
			alert("이름이 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userEmail.length == 0)
		{
			alert("이메일이 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userPhone.length == 0)
		{
			alert("전화번호가 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userBirth.length == 0)
		{
			alert("생년월일이 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userAddrFull.length == 0)
		{
			alert("주소가 입력되지 않았습니다!");
			return false;
		}
		if (this.userInfo.userPw != this.userInfo.userPw2)
		{
			alert("비밀번호가 일치하지않습니다.");
			return false;
		}
		if (this.userInfo.idChk == 'N')
		{
			alert("아이디 중복확인을 해주세요.");
      return false;
    }
    
    var addr = "http://localhost:8080/mobile/regUserAction";
    let body = "userId=" + this.userInfo.userId
      + "&userPw=" + this.userInfo.userPw
      + "&userNm=" + this.userInfo.userNm
      + "&userEmail=" + this.userInfo.userEmail
      + "&userPhone=" + this.userInfo.userPhone
      + "&userGender=" + this.userInfo.userGender
      + "&userAddrFull=" + this.userInfo.userAddrFull
      + "&userAddrFullRoad=" + this.userInfo.userAddrFullRoad
      + "&userAddrSido=" + this.userInfo.userAddrSido
      + "&userAddrSigungu=" + this.userInfo.userAddrSigungu
      + "&userAddrEMD=" + this.userInfo.userAddrEMD
      + "&userAddrJibun=" + this.userInfo.userAddrJibun
      + "&userAddrRoad=" + this.userInfo.userAddrRoad
      + "&userAddrBuldNo=" + this.userInfo.userAddrBuldNo
      + "&userAddrDetail=" + this.userInfo.userAddrDetail
      + "&userZipcode=" + this.userInfo.userZipCode
      + "&userBirth=" + this.userInfo.userBirth
      + "&userSL=" + this.userInfo.userSL
      ;
    //let headers = new Headers({"Content-Type": "application/json"});
    let headers = new Headers({"Content-Type": "application/x-www-form-urlencoded"});
    let options = new RequestOptions({ headers: headers });
    var result;

    this.http.post(addr, body, options)
      .map(res=>res.json())
      .subscribe((res)=>{
        result = res.result;

        if (result == "S00000")
        {
          alert("가입이 완료되었습니다.");
          this.navCtrl.pop();
        }
        else
        {
          alert("가입 실패!\n관리자에게 문의하세요.");
          //this.navCtrl.pop();
        }
      });
  }
  
  //가입취소
  btnCancel()
  {
    this.navCtrl.pop();
  }
  
  //아이디 중복체크
  btnUserIdChk()
  {

    if (this.userInfo.userId == "")
    {
      alert("Id를 입력해주세요.");
      return false;
    }

    var addr = "http://localhost:8080/mobile/overlapIdCheck";
    let body = "userId=" + this.userInfo.userId;
    //let headers = new Headers({"Content-Type": "application/json"});
    let headers = new Headers({"Content-Type": "application/x-www-form-urlencoded"});
    let options = new RequestOptions({ headers: headers });
    var result;

    this.http.post(addr, body, options)
      .map(res=>res.json())
      .subscribe((res)=>{
        result = res.result;

        if (result)
        {
          alert("이미 가입되어 있는 아이디입니다!");
          this.userInfo.idChk = "N";
          this.userInfo.userId = "";
          return false;
        }
        else
        {
          alert("가입 가능한 아이디 입니다.");
          this.userInfo.idChk = "Y";
        }
      });
  }
}
