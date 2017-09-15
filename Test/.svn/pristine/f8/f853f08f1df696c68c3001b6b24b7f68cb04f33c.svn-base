import { Component, ViewChild } from '@angular/core';
import { Nav, Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

import { TabsPage } from '../pages/tabs/tabs';
import { LoginPage } from '../pages/login/login';
//push
//import { Push, PushToken } from '@ionic/cloud-angular';
import { HomePage } from '../pages/home/home';
import { CalendarPage } from '../pages/calendar/calendar';
import { NoticeListPage } from '../pages/notice/noticeList';
import { MyPage } from '../pages/myPage/myPage';


@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;
  
  // rootPage:any = TabsPage;
  rootPage : any = LoginPage;

  pages: Array<{title: string, name: string, component: any, tabComponent?: any, index? : number;}>;
  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen) {

    this.pages = [
      { title: 'Home', name: 'TabsPage', component: TabsPage, tabComponent: HomePage, index : 0 },
      { title: 'Notice', name: 'TabsPage', component: TabsPage, tabComponent: NoticeListPage, index : 1 },
      { title: 'Calendar', name: 'TabsPage', component: TabsPage, tabComponent: CalendarPage, index : 2 },
      { title: 'MyPage', name: 'TabsPage', component: TabsPage, tabComponent: MyPage, index : 3}
    ];
    this.intializeApp();

    // platform.ready().then(() => {
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      // statusBar.styleDefault();
      // splashScreen.hide();
      
      /*
      //push
      this.push.register().then((t: PushToken) => {
        return this.push.saveToken(t);
      }).then((t: PushToken) => {
        console.log('Token saved:', t.token);
      });
      this.push.rx.notification()
        .subscribe((msg) => {
          alert(msg.title + ': ' + msg.text);
      });//*/
    // });
  }

  intializeApp(){
    this.platform.ready().then(() => {    
      this.statusBar.styleDefault();
      this.splashScreen.hide();
    });
  }

  openPage(page) {
    // Reset the content nav to have just this page
    // we wouldn't want the back button to show in this scenario
    let params = {};

    if(page.index){
      params = {tabIndex: page.index};
    }

    this.nav.setRoot(page.component, params);
    // this.nav.push(page.component);
    // this.nav.setRoot(TabsPage.prototype.tab4Root);
  }

  logout(){
    this.nav.setRoot(LoginPage);
  }
}
