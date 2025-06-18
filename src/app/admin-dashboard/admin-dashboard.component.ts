import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent  implements OnInit {
  firstName!: string;
  lastName!: string;
  role!: string;

  ngOnInit() {
    this.firstName = localStorage.getItem('firstName') || 'Réceptionniste';
    this.lastName  = localStorage.getItem('lastName')  || '';
    this.role      = localStorage.getItem('userRole')  || '';
  }

}
