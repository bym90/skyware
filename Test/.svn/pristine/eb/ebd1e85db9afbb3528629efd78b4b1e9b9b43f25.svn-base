import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController, AlertController} from 'ionic-angular';
import * as moment from 'moment';
import { Http, Headers, RequestOptions } from '@angular/http';
import { CalendarViewModalPage } from '../calendar-view-modal/calendar-view-modal';

/**
 * Generated class for the CalendarModifyModalPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@IonicPage()
@Component({
  selector: 'page-calendar-modify-modal',
  templateUrl: 'calendar-modify-modal.html',
})
export class CalendarModifyModalPage{
  event = {calendarNo: String(), startTime: String(), endTime: String(), cate1 : String(), cate2 : String(), title : String(), content : String(), memo : String(), completion : Number(), writerId : String(), allDay : Boolean}
  cate1 = [];
  cate2 = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, private viewCtrl: ViewController, private alertCtrl: AlertController,  private http: Http) {
    
    this.event.calendarNo = this.navParams.get('calendarNo');
    this.event.startTime = moment(this.navParams.get('startTime')).format();
    this.event.endTime =  moment(this.navParams.get('endTime')).format();
    this.event.title = this.navParams.get('title');
    this.event.cate1 = this.navParams.get('cate1');
    this.event.cate2 = this.navParams.get('cate2');
    this.event.content = this.navParams.get('content');
    this.event.memo = this.navParams.get('memo');
    this.event.completion = this.navParams.get('completion');
    this.event.writerId = this.navParams.get('writerId');
    this.event.allDay = this.navParams.get('allDay');

    this.cate1 = this.navParams.get('temp1');
    this.cate2 = this.navParams.get('temp2');
  }

  cancel() {
    this.viewCtrl.dismiss();
  }

  modify(event){
     if(event.cate1 == "" || event.cate1 == null){
      let alert = this.alertCtrl.create({
        title: '카테고리를 선택하세요',
        buttons : ['확인']
      });
      alert.present();

    } else if(event.cate2 == "" || event.cate2 == null){
       let alert = this.alertCtrl.create({
         title: '세부카테고리를 선택하세요',
         buttons : ['확인']
       });
       alert.present();
     
    } else if(event.title == "" || event.title == null){
       let alert = this.alertCtrl.create({
          title: '일정제목을 입력하세요',
         buttons : ['확인']
       });
        alert.present();
     
    } else if(event.content == "" || event.content == null){
       let alert = this.alertCtrl.create({
         title: '일정내용을 입력하세요',
         buttons : ['확인']
        });
       alert.present();
      
    } else if(event.startTime > event.endTime){
       let alert = this.alertCtrl.create({
       title: '날짜를 다시 선택해주세요',
       subTitle: '일정마감날짜가 일정시작날짜 이전입니다.',
       buttons: ['확인']       
      });
      alert.present();

    } else if (isNaN(event.completion)){
        let alert = this.alertCtrl.create({
         title: '숫자만 입력하세요',
          buttons : ['확인']
        });
        alert.present();

    } else {
      var addr = "http://localhost:8080/mobile/scheduleModify";
      let headers = new Headers({"Content-Type": "application/x-www-form-urlencoded"});
      let options = new RequestOptions({headers: headers});
    
      if(event.memo == undefined){   
        event.memo = "";
      }
      let data = 'calendarNo='+ event.calendarNo
                +'&cate1=' + event.cate1
                +'&cate2=' + event.cate2
                +'&title=' + event.title
                +'&content=' + event.content
                +'&memo=' + event.memo
                +'&completion=' + event.completion
                +'&writerId=' + localStorage.getItem("userId")
                +'&startTime=' + event.startTime 
                +'&endTime=' + event.endTime
                +'&allDay=' + event.allDay;

      this.http.post(addr, data, options)
      // .map(res=>res.json())
      .subscribe((res)=>{
                  let alert = this.alertCtrl.create({
                      title: '일정이 수정되었습니다.',
                      buttons: ['확인']
                    });
                  alert.present();

                  event.userNm = localStorage.getItem("userNm");

                  this.viewCtrl.dismiss(this.event);
                }, (err) => {
                  console.log(err);
                    let alert = this.alertCtrl.create({
                      title: '일정이 수정되지 않습니다. 관리자에게 문의하세요',
                      buttons: ['확인']
                    });
                  alert.present();
                   
                });
      
    }
  }

}
