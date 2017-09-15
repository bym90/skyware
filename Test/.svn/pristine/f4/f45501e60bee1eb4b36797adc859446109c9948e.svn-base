import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'page-myPage',
  templateUrl: 'myPage.html'
})
export class MyPage {
  userInfo = [];
  myWritingList;
  constructor(public navCtrl: NavController, private http: Http, private alertCtrl: AlertController, public navParams: NavParams, private modalCtrl: ModalController) {
    this.userInfo.push("이름 : " + localStorage.getItem("userNm"), "이메일 : " + localStorage.getItem("userEmail"), "연락처 : " + localStorage.getItem("userPhone"))
    this.getWriteMySchedule();
  }


  getWriteMySchedule() {
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });
    var addr = "http://192.168.0.87:8080/mobile/getWriteMySch";
    var data = "writerId=" + localStorage.getItem("userId");

    this.http.post(addr, data, options)
      .map(res => res.json())
      .subscribe((res) => {

        this.myWritingList = res.getWriteMySch

      }, (err) => {
        console.log(err);
        let alert = this.alertCtrl.create({
          title: '내가 작성한 일정을 불러오지 못했습니다. 관리자에게 문의하세요',
          buttons: ['확인']
        });
        alert.present();
      });
  }

  viewModal(wList) {
    console.log(wList);
    console.log(wList.userNm);
    if (wList.schAllDay == 'Y') {
      wList.schAllDay = true;
    } else {
      wList.schAllDay = false;
    }
    let modal = this.modalCtrl.create('CalendarViewModalPage', {
      calendarNo: wList.schNo, startTime: wList.schStartDate, endTime: wList.schEndDate, cate1: wList.schCate1, cate2: wList.schCate2,
      title: wList.schTitle, content: wList.schBody, memo: wList.schMemo, completion: wList.schCompletion, writerId: wList.schWriterId, allDay: wList.schAllDay, userNm: wList.userNm
    })

    modal.present();

  }
}
