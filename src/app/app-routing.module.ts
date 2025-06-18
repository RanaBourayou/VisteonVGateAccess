import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SigninComponent } from './signin/signin.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { HeaderDashboardComponent } from './header-dashboard/header-dashboard.component';
import { SidebarDashboardComponent } from './sidebar-dashboard/sidebar-dashboard.component';
import { ReceptionistDashboardComponent } from './receptionist-dashboard/receptionist-dashboard.component';
import { RequesterComponent } from './requester/requester.component';
import { NotfoundComponent } from './notfound/notfound.component';
  
const routes: Routes = [
{  path: "header",component:HeaderComponent },
{  path: "home",component:HomeComponent },
{  path: "signin",component:SigninComponent},
{  path: "dashboard",component:AdminDashboardComponent },
{  path: "headerdashboard",component:HeaderDashboardComponent },
{path:"sidebardashboard",component:SidebarDashboardComponent},
{path:"recep",component:ReceptionistDashboardComponent},
{path:"request",component:RequesterComponent},
{path:"n", component:NotfoundComponent},
{path:"h", component:HomeComponent}

];
 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
