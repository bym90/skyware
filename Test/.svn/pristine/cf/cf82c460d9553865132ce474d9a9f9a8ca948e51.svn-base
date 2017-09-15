import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';

import { AboutPage } from '../pages/about/about';
import { MyPage } from '../pages/myPage/myPage';
import { HomePage } from '../pages/home/home';
import { TabsPage } from '../pages/tabs/tabs';
import { CalendarPage } from '../pages/calendar/calendar';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { HttpModule } from '@angular/http';
import { NgCalendarModule } from 'ionic2-calendar';

import { UserJoinPage } from '../pages/user/userJoin';
import { AddressPage } from '../pages/address/address';
import { NoticeListPage } from '../pages/notice/noticeList';
import { NoticeDetailPage } from '../pages/notice/noticeDetail';
import { NoticeWritePage } from '../pages/notice/noticeWrite';
import { NoticeUpdatePage } from '../pages/notice/noticeUpdate';
import { LoginPage } from '../pages/login/login';

//push알림
import { CloudSettings, CloudModule } from '@ionic/cloud-angular';

//push알림
const cloudSettings: CloudSettings = {
  'core': {
    'app_id': '36f7379e',
  },
  'push': {
    'sender_id': '1041661104929',
    'pluginConfig': {
      'ios':{
        'badge':true,
        'sound':true
      },
      'android': {
        'iconColor': '#343434'
      }
    }
  }
};

@NgModule({
  declarations: [
    MyApp,
    AboutPage,
    MyPage,
    CalendarPage,
    HomePage,
    TabsPage,
    UserJoinPage,
    AddressPage,
    NoticeListPage,
    NoticeDetailPage,
    NoticeWritePage,
    NoticeUpdatePage,
    LoginPage
  ],
  imports: [
    NgCalendarModule,
    BrowserModule,
    HttpModule,
    IonicModule.forRoot(MyApp),
    CloudModule.forRoot(cloudSettings)//push알림
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    AboutPage,
    MyPage,
    CalendarPage,
    HomePage,
    TabsPage,
    UserJoinPage,
    AddressPage,
    NoticeListPage,
    NoticeDetailPage,
    NoticeWritePage,
    NoticeUpdatePage,
    LoginPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler}
  ]
})
export class AppModule {}
