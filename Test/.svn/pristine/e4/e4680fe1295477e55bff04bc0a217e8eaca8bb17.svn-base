import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController, App } from 'ionic-angular';
//import { HTTP } from '@ionic-native/http';
//import { DBMeter } from '@ionic-native/db-meter';
//import { mysql } from 'mysql';
import { Http, Headers, RequestOptions } from '@angular/http';
//import { BrowserModule } from '@angular/platform-browser';
//import { NgModule, ErrorHandler } from '@angular/core';
//import { HttpModule } from '@angular/http';
import 'rxjs/add/operator/map';
// import { UserJoinPage } from "../user/userJoin";
import { LoginPage } from '../login/login';
import * as moment from 'moment';
import { CalendarWriteModalPage } from '../calendar-write-modal/calendar-write-modal';
import { CalendarViewModalPage } from '../calendar-view-modal/calendar-view-modal';

@Component({
  selector: 'page-home',
  templateUrl: 'home.html'
})
export class HomePage {
  birthList;
  weekList;
  noWeek : string;
  constructor(public app: App, public navCtrl: NavController, private http: Http, private alertCtrl: AlertController, public navParams: NavParams, private modalCtrl: ModalController) {
    this.getBirthList();
    this.getWeekList();
  }

  getBirthList() {
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });
    var addr = "http://192.168.0.87:8080/mobile/getBirth";

    this.http.post(addr, "", options)
      .map(res => res.json())
      .subscribe((res) => {
        for (var index = 0; index < res.getBirthList.length; index++) {
          res.getBirthList[index].userBirth = moment(res.getBirthList[index].userBirth).format("YYYY/MM/DD");
        }

        this.birthList = res.getBirthList;

      }, (err) => {
        console.log(err);
        let alert = this.alertCtrl.create({
          title: '이번달 생일자를 불러오지 못했습니다. 관리자에게 문의하세요',
          buttons: ['확인']
        });
        alert.present();
      });
  }

  getWeekList() {
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });
    var addr = "http://192.168.0.87:8080/mobile/getWeekList";

    this.http.post(addr, "", options)
      .map(res => res.json())
      .subscribe((res) => {
        console.log("성공!!")
        console.log(res.getWeekList);
        if(res.getWeekList.length == 0){
          this.noWeek = "이번주 일정이 없습니다.";
        } else {
          for(let index = 0; index < res.getWeekList.length; index++){
            res.getWeekList[index].schStartDate = moment(res.getWeekList[index].schStartDate).format('YYYY/MM/DD HH:mm');
          }
          this.weekList = res.getWeekList;
        }
      }, (err) => {
        console.log(err);
        let alert = this.alertCtrl.create({
          title: '이번주 일정을 불러오지 못했습니다. 관리자에게 문의하세요',
          buttons: ['확인']
        });
        alert.present();
      });
  }

  viewModal(week) {
    console.log(week);
    console.log(week.userNm);
    if (week.schAllDay == 'Y') {
      week.schAllDay = true;
    } else {
      week.schAllDay = false;
    }
    let modal = this.modalCtrl.create('CalendarViewModalPage', {
      calendarNo: week.schNo, startTime: week.schStartDate, endTime: week.schEndDate, cate1: week.schCate1, cate2: week.schCate2,
      title: week.schTitle, content: week.schBody, memo: week.schMemo, completion: week.schCompletion, writerId: week.schWriterId, allDay: week.schAllDay, userNm: week.userNm
    })

    modal.present();

    modal.onDidDismiss(data => {
      if (data) {
        console.log("들어옴??");
        console.log(data);
        this.weekList.schNo = data.calendarNo;
        this.weekList.schStartDate = moment(data.startTime).format('YYYY/MM/DD HH:mm');
        this.weekList.schEndDate = moment(data.endTime).format('YYYY/MM/DD HH:mm');
        this.weekList.schMemo = data.memo;
        this.weekList.schCate1 = data.cate1;
        this.weekList.schCate2 = data.cate2;
        this.weekList.schTitle = data.title;
        // this.event.saveTime = new Date().toString;
        this.weekList.schCompletion = data.completion;
        this.weekList.schBody = data.content
        this.weekList.userNm = localStorage.getItem("userNm");
        this.weekList.writerId = localStorage.getItem("userId");
        this.weekList.schAllDay = data.allDay;
        this.weekList.userNm = data.userNm;
      }
    });
  }

  btnLogout() {
    var addr = "http://localhost:8080/mobile/LogoutAction";
    /*let body = {
      userId : userId,
      userPw : userPw
    };*/
    let body = "";
    //let headers = new Headers({"Content-Type": "application/json"});
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });

    this.http.post(addr, body, options)
      // .map(res => res.json())
      .subscribe((res) => {
        //this.navCtrl.setPages;

        this.app.getRootNav().setRoot(LoginPage);
        // this.navCtrl.setRoot(LoginPage);
        // this.navCtrl.setRoot(CalendarWriteModalPage);
        
        //this.navCtrl.goToRoot;
        //this.navCtrl.popToRoot;
        localStorage.removeItem("userId");
        localStorage.removeItem("userNm");
        localStorage.removeItem("userEmail");
        localStorage.removeItem("userPhone");
        localStorage.removeItem("userBirth");
        localStorage.removeItem("userInfo");

      });
  }




}
