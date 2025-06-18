import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
 import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SigninComponent } from './signin/signin.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HeaderDashboardComponent } from './header-dashboard/header-dashboard.component';
import { SidebarDashboardComponent } from './sidebar-dashboard/sidebar-dashboard.component';
import { ReceptionistDashboardComponent } from './receptionist-dashboard/receptionist-dashboard.component';
import { RequesterComponent } from './requester/requester.component';
import { NotfoundComponent } from './notfound/notfound.component';
  

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    SigninComponent,
    AdminDashboardComponent,
    HeaderDashboardComponent,
    SidebarDashboardComponent,
    ReceptionistDashboardComponent,
    RequesterComponent,
    NotfoundComponent,
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
