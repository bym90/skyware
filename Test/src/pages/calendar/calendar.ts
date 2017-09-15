import { Component, OnInit, OnChanges, OnDestroy, DoCheck, AfterContentInit, AfterContentChecked, AfterViewInit } from '@angular/core';
import { NavController, ModalController, AlertController } from 'ionic-angular';
import { Http, Headers, RequestOptions } from '@angular/http';
import * as moment from 'moment';
import 'rxjs/add/operator/map';

@Component({
  selector: 'page-calendar',
  templateUrl: 'calendar.html'
})
export class CalendarPage {
  eventSource = [];
  cate1 = [];
  isToday: boolean;
  viewTitle: string;
  selectedDay = new Date();
  calendarList: object;

  calendar = {
    mode: 'month',
    currentDate: new Date()

  }

  constructor(public navCtrl: NavController, private modalCtrl: ModalController, private alertCtrl: AlertController, private http: Http) {

  }

  // 오늘 날짜
  today() {
    this.calendar.currentDate = new Date();
  }

  // 월, 주 변경
  changeMode(mode) {
    this.calendar.mode = mode;
  }

  // 일정 불러오기
  loadEvents(viewTitle) {
    var addr = "http://localhost:8080/mobile/scheduleList";
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });
    var current = viewTitle;

    var month = current.getUTCMonth();
    var year = current.getUTCFullYear();
    var date = current.getUTCDate();

    var tempBefore = new Date(Date.UTC(year, month - 2, date));
    var tempAfter = new Date(Date.UTC(year, month + 2, date));

    var beforeMonth = moment(tempBefore).format('YYYY-MM-DD');
    var afterMonth = moment(tempAfter).format('YYYY-MM-DD');

    console.log(current + "현재");
    console.log(tempBefore + "임시이전");
    console.log(tempAfter + "임시이후");
    console.log(beforeMonth + "이전");
    console.log(afterMonth + "이후");

    var now = "beforeMonth=" + beforeMonth + "&afterMonth=" + afterMonth;

    this.http.post(addr, now, options)
      .map(res => res.json())
      .subscribe((res) => {
        this.calendarList = res.calendarList;
        this.eventSource = [];
        let events = this.eventSource;

        for (var index = 0; index < res.calendarList.length; index++) {
          let eventData = res.calendarList[index];

          eventData.no = res.calendarList[index].schNo;
          eventData.startTime = new Date(res.calendarList[index].schStartDate);
          eventData.endTime = new Date(res.calendarList[index].schEndDate);;
          eventData.saveTime = new Date(res.calendarList[index].schSaveDate);
          eventData.title = res.calendarList[index].schTitle;
          eventData.content = res.calendarList[index].schBody;
          eventData.memo = res.calendarList[index].schMemo;
          eventData.cate1 = res.calendarList[index].schCate1;
          eventData.cate2 = res.calendarList[index].schCate2;
          eventData.completion = res.calendarList[index].schCompletion;
          eventData.writerId = res.calendarList[index].schWriterId;
          eventData.userNm = res.calendarList[index].userNm;

          if (res.calendarList[index].schAllDay == 'Y') {
            eventData.allDay = true
          } else {
            eventData.allDay = false
          }
          events.push(eventData);
        }

        setTimeout(() => {
          this.eventSource = events;
        });
      }, (err) => {
        console.log(err);
        let alert = this.alertCtrl.create({
          title: '일정이 조회되지 않습니다. 관리자에게 문의하세요',
          buttons: ['확인']
        });
        alert.present();
      });
  }

  // 일정 추가
  addEvent() {
    var addr = "http://localhost:8080/mobile/scheduleGetCate";
    let headers = new Headers({ "Content-Type": "application/x-www-form-urlencoded" });
    let options = new RequestOptions({ headers: headers });
    let data = "CATE=" + 'CATE';

    let cate1 = [];
    let cate2 = [];

    this.http.post(addr, data, options)
      .map(res => res.json())
      .subscribe((res) => {
        for (var index = 0; index < res.scheduleCateList.length; index++) {
          let temp1 = res.scheduleCateList[index];
          let temp2 = res.scheduleCateList[index];

          if (res.scheduleCateList[index].codeDiv == "CATE01") {
            temp1.codeId = res.scheduleCateList[index].codeId;
            temp1.codeNm = res.scheduleCateList[index].codeNm;
            cate1.push(temp1);
          } else {
            temp2.codeId = res.scheduleCateList[index].codeId;
            temp2.codeNm = res.scheduleCateList[index].codeNm;
            cate2.push(temp2);
          }

        }

      }, (err) => {
        console.log(err);
        let alert = this.alertCtrl.create({
          title: '카테고리가 없습니다. 관리자에게 문의하세요',
          buttons: ['확인']
        });
        alert.present();
      });


    let modal = this.modalCtrl.create('CalendarWriteModalPage', { selectedDay: this.selectedDay, temp1: cate1, temp2: cate2 });
    modal.present();

    // 추가후 데이터 양방향 바인딩
    modal.onDidDismiss(data => {
      if (data) {
        let eventData = data;

        eventData.no = data.calendarNo;
        eventData.cate1 = data.cate1;
        eventData.cate2 = data.cate2;
        eventData.title = data.title;
        eventData.content = data.content;
        eventData.memo = data.memo;
        eventData.startTime = new Date(data.startTime);
        eventData.endTime = new Date(data.endTime);
        eventData.saveTime = new Date();
        eventData.completion = data.completion;
        eventData.allDay = data.allDay;
        eventData.userNm = localStorage.getItem("userNm");
        eventData.writerId = localStorage.getItem("userId");

        let events = this.eventSource;
        events.push(eventData);

        this.eventSource = [];
        setTimeout(() => {
          this.eventSource = events;
        });

      }
    });
  }

  // 월 바뀔때 제목 변경
  onViewTitleChanged(title) {
    this.viewTitle = title;
  }

  // 현재 날짜 바뀔때 날짜 기준으로 앞뒤간격 두달 일정 불러오기
  onCurrentDateChanged(temp) {
    var changeDate = temp;
    this.loadEvents(changeDate);
  }

  // 날짜 클릭시 클릭된 날짜 변경
  onTimeSelected(ev) {
    this.selectedDay = ev.selectedTime;
  }

  // 데이터 풀다운 리프레쉬
  doRefresh(refresher) {
    setTimeout(() => {
      console.log(this.selectedDay)
      this.onCurrentDateChanged(this.selectedDay);
      refresher.complete();
    }, 1000);
  }

  // 이벤트 선택시 상세보기 모달창 띄우기
  onEventSelected(event) {
    console.log(event.no + "::::::::::::::::::::::::::::::::::::::::::::");
    console.log(event.writerId + "::::::::::::::::::::::::::::::::::::::::::::");
    console.log(event.userNm + "::::::::::::::::::::::::::::::::::::::::::::");
    let start = moment(event.startTime).format('YYYY/MM/DD HH:mm');
    let end = moment(event.endTime).format('YYYY/MM/DD HH:mm');
    let modal = this.modalCtrl.create('CalendarViewModalPage', {
      calendarNo: event.no, startTime: start.toString(), endTime: end.toString(), cate1: event.cate1, cate2: event.cate2,
      title: event.title, content: event.content, memo: event.memo, completion: event.completion, writerId: event.writerId, allDay: event.allDay, userNm: event.userNm
    })

    modal.present();

    // 상세보기 모달창 종료후 양방향 데이터 바인딩
    modal.onDidDismiss(data => {
      if (data) {
        this.onCurrentDateChanged(this.selectedDay);
      }
    });
  }
}



