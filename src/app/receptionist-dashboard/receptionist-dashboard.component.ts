import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-receptionist-dashboard',
  templateUrl: './receptionist-dashboard.component.html',
  styleUrls: ['./receptionist-dashboard.component.css']
})
export class ReceptionistDashboardComponent  implements OnInit {
  firstName!: string;
  lastName!: string;
  role!: string;
  menuOpen = false;

  constructor(private router: Router) {}


  ngOnInit() {
    this.firstName = localStorage.getItem('firstName') || 'Réceptionniste';
    this.lastName  = localStorage.getItem('lastName')  || '';
    this.role      = localStorage.getItem('userRole')  || '';
  }
 
toggleMenu(event: MouseEvent) {
  event.stopPropagation();
  this.menuOpen = !this.menuOpen;
}
goToAccount() { /* navigate to profile */ }
logout() {
  localStorage.clear();
  this.router.navigate(['/signin']);
}
@HostListener('document:click')
closeMenu() { this.menuOpen = false; }
}