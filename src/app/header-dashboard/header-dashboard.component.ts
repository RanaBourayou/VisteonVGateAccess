import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-dashboard',
  templateUrl: './header-dashboard.component.html',
  styleUrls: ['./header-dashboard.component.css']
})
export class HeaderDashboardComponent  implements OnInit {
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

 toggleMenu(event: Event) {
    event.stopPropagation();
    this.menuOpen = !this.menuOpen;
  }

  goToAccount() {
    this.menuOpen = false;
    this.router.navigate(['/account']);  // adjust to your actual account route
  }

  logout() {
    // clear storage and redirect
    localStorage.clear();
    this.router.navigate(['/signin']);
  }
  // Close menu when clicking anywhere else
  @HostListener('document:click', ['$event'])
  onClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
    if (!target.closest('.user-profile')) {
      this.menuOpen = false;
    }
  }

}
