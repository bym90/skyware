import { Component, EventEmitter, Output } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController, AlertController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import * as moment from 'moment';
import 'rxjs/add/operator/map';
import { CalendarPage } from '../calendar/calendar';

@IonicPage()
@Component({
  selector: 'page-calendar-write-modal',
  templateUrl: 'calendar-write-modal.html',
})
export class CalendarWriteModalPage {
  event = { startTime: new Date().toISOString(), endTime: new Date().toISOString() }
  minDate = new Date().toISOString();
  cate1 = [];
  cate2 = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, private viewCtrl: ViewController, private alertCtrl: AlertController, private http: Http) {
    let preselectedDate = moment(this.navParams.get('selectedDay')).format();
    let cate1 = this.navParams.get('temp1');
    let cate2 = this.navParams.get('temp2');

    this.cate1 = cate1;
    this.cate2 = cate2;

    this.event.startTime = preselectedDate;
    this.event.endTime = preselectedDate;

  }

  cancel() {
    this.viewCtrl.dismiss();
  }

  save(event) {
    if (event.cate1 == "" || event.cate1 == null) {
      let alert = this.alertCtrl.create({
        title: '카테고리를 선택하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.cate2 == "" || event.cate2 == null) {
      let alert = this.alertCtrl.create({
        title: '세부카테고리를 선택하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.title == "" || event.title == null) {
      let alert = this.alertCtrl.create({
        title: '일정제목을 입력하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.content == "" || event.content == null) {
      let alert = this.alertCtrl.create({
        title: '일정내용을 입력하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.startTime > event.endTime) {
      let alert = this.alertCtrl.create({
        title: '날짜를 다시 선택해주세요',
        subTitle: '일정마감날짜가 일정시작날짜 이전입니다.',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.completion == "" || event.completion == null) {
      let alert = this.alertCtrl.create({
        title: '진행률을 입력하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (isNaN(event.completion)) {
      let alert = this.alertCtrl.create({
        title: '숫자만 입력하세요',
        buttons: ['확인']
      });
      alert.present();

    } else if (event.completion > 100) {
      let alert = this.alertCtrl.create({
        title: '100이하의 숫자만 입력하세요',
        buttons: ['확인']
      });
      alert.present();


    } else {
      var addr = "http://localhost:8080/mobile/scheduleWrite";
      let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
      let options = new RequestOptions({ headers: headers });

      if (event.memo == undefined) {
        event.memo = "";
      }
      let data = 'cate1=' + event.cate1
        + '&cate2=' + event.cate2
        + '&title=' + event.title
        + '&content=' + event.content
        + '&memo=' + event.memo
        + '&startTime=' + event.startTime
        + '&endTime=' + event.endTime
        + '&completion=' + event.completion
        + '&allDay=' + event.allDay
        + '&writerId=' + localStorage.getItem("userId")  
        + '&userNm=' + localStorage.getItem("userNm");
        console.log(event.startTime+"확인해보자 스타트타임");
      this.http.post(addr, data, options)
        .map(res => res.json())
        .subscribe((res) => {
          let alert = this.alertCtrl.create({
            title: '일정이 등록되었습니다.',
            buttons: ['확인']
          });
          alert.present();

          event.calendarNo = res.schNo;
          
          console.log(data);
          console.log("======================================================");
          console.log(this.event);
          this.viewCtrl.dismiss(this.event);
          
        }, (err) => {
          console.log(err);
          let alert = this.alertCtrl.create({
            title: '일정이 등록되지 않습니다. 관리자에게 문의하세요',
            buttons: ['확인']
          });
          alert.present();

        });

    }
  }
}
