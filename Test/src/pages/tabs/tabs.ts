import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
//import { AboutPage } from '../about/about';
import { MyPage } from '../myPage/myPage';
import { HomePage } from '../home/home';
import { CalendarPage } from '../calendar/calendar';
import { NoticeListPage } from '../notice/noticeList';
// import { NavController, AlertController } from 'ionic-angular';
// import { Http, Headers, RequestOptions } from '@angular/http';

@Component({
  selector: 'page-tabs',
  templateUrl: 'tabs.html'
})
export class TabsPage {

  tab1Root: any = HomePage;
  tab2Root: any = NoticeListPage;
  tab3Root: any = CalendarPage;
  tab4Root: any = MyPage;
  myIndex: number;

  constructor(navParams: NavParams) {
    this.myIndex = navParams.data.tabIndex || 0;
  }
}
