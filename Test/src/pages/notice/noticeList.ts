import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import { ModalController } from 'ionic-angular';
import { NoticeDetailPage } from '../notice/noticeDetail'
import { NoticeWritePage } from '../notice/noticeWrite';
import $ from 'jquery/dist/jquery';

@Component({
  selector: 'page-noticeList',
  templateUrl: 'noticeList.html'
})
export class NoticeListPage
{
  noticeList;

  notice : {
    sPage: string,
    ePage: string,
    curPage: string,
    totalCnt: string
  } = {
    sPage: "",
    ePage: "",
    curPage: "",
    totalCnt: ""
  }

  constructor(public navCtrl: NavController, private http: Http, private modalCtrl: ModalController)
  {
    this.getNoticeList();
  }

  btnWrite()
  {
    this.navCtrl.push(NoticeWritePage);
  }

  btnListPlus()
  {
    //alert(Number(this.notice.curPage) + 1);
    $.ajax({
			url : "http://localhost:8080/mobile/board/noticeList",
			type : "post",
      data : {curPage : Number(this.notice.curPage) + 1},
      dataType : "json",
			success : function(responseData, statusText, xhr)
			{
        console.log(responseData);
        console.log(responseData.sPage);


        this.noticeList = responseData.noticeList
        let html = "";
        html += '<ion-row class="noticeList" (click)="noticeDetail(notice.noticeNo, notice.rownum);" *ngFor = "let notice of noticeList; let i = index">'
          +'<ion-col col-1 id="text">{{ notice.rownum }}</ion-col>'
          +'<ion-col col-6>{{ notice.noticeTitle }}</ion-col>'
          +'<ion-col col-2>{{ notice.noticeWriter }}</ion-col>'
          +'<ion-col col-3>{{ notice.noticeDate }}</ion-col>'
          +'</ion-row>'

        $("#noticeList").append(html);
        
        this.noticeList = responseData.noticeList
        //console.log(this.noticeList);
        //$("#noticeList").html(this.noticeList);
			},
			error : function(xhr, statusText, error)
			{
				alert("공지사항 조회 실패! 관리자에게 문의하세요.");
				console.log("error : " + statusText + ", " + xhr.status + ", " + error);
			}
		});
  }

  noticeDetail(noticeNo, rownum)
  {
    //alert(noticeNo);

    /*//modal창 띄우기
    let addrPage = this.modalCtrl.create( NoticeDetailPage, { noticeNo: noticeNo } );
    //modal창에서 데이터 전달
    addrPage.onDidDismiss(data =>{
      userAddrFull.value = data.userAddrFull,
      userAddrFullRoad.value = data.userAddrFullRoad,
      userAddrSido.value = data.userAddrSido,
      userAddrSigungu.value = data.userAddrSigungu,
      userAddrEMD.value = data.userAddrEMD,
      userAddrJibun.value = data.userAddrJibun,
      userAddrRoad.value = data.userAddrRoad,
      userAddrBuldNo.value = data.userAddrBuld,
      userZipCode.value = data.userZipcode
    });
    addrPage.present();*/

    this.navCtrl.push(NoticeDetailPage, { noticeNo : noticeNo, rownum : rownum });
  }

  doRefresh(refresher)
  {
    console.log('Begin async operation', refresher);

    setTimeout(() => {
      console.log('Async operation has ended');
      this.getNoticeList();
      refresher.complete();
    }, 2000);
  }

  getNoticeList()
  {
    var totCnt;
    //var addrList: any[];
    var addr = "http://localhost:8080/mobile/board/noticeList";
    let body = "";
    //let headers = new Headers({"Content-Type": "application/json"});
    let headers = new Headers({"Content-Type": "application/x-www-form-urlencoded"});
    let options = new RequestOptions({ headers: headers });

    this.http.post(addr, body, options)
      .map(res=>res.json())
      .subscribe((res)=>{
        totCnt = res.totalCnt;
        this.noticeList = res.noticeList;
        this.notice.sPage = res.sPage;
        this.notice.ePage = res.ePage;
        this.notice.curPage = res.curPage;
        this.notice.totalCnt = res.totalCnt;

        //console.log("totCnt : " + totCnt);
        //console.log("noticeList : " + this.noticeList[0].noticeTitle);
      });
  }
}
