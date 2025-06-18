import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-requester',
  templateUrl: './requester.component.html',
  styleUrls: ['./requester.component.css']
})
export class RequesterComponent implements OnInit {
  firstName!: string;
  lastName!: string;
  role!: string;
    menuOpen = false;
  constructor(private router: Router) {}

  ngOnInit() {
    this.firstName = localStorage.getItem('firstName') || 'Utilisateur';
    this.lastName  = localStorage.getItem('lastName')  || '';
    this.role      = localStorage.getItem('userRole')  || '';
  }
   toggleMenu(event: Event) {
    event.stopPropagation();
    this.menuOpen = !this.menuOpen;
  }
    goToAccount() {
    this.menuOpen = false;
    this.router.navigate(['/account']);
  }

  logout() {
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
  
  currentDate: Date = new Date();
  currentView: 'month' | 'day' = 'month';
  selectedDate: Date | null = null;
  
  // Calendar navigation - fixed logic
  navigate(direction: 'prev' | 'next' | 'today') {
    if (direction === 'today') {
      this.currentDate = new Date();
      this.selectedDate = new Date();
      return;
    }

    const newDate = new Date(this.currentDate);
    
    if (this.currentView === 'month') {
      // Fixed: Proper month navigation with year rollover
      const newMonth = direction === 'prev' 
        ? this.currentDate.getMonth() - 1 
        : this.currentDate.getMonth() + 1;
      
      newDate.setMonth(newMonth);
      // Adjust year if crossing boundaries
      if (newDate.getMonth() !== (newMonth + 12) % 12) {
        newDate.setFullYear(this.currentDate.getFullYear() + (direction === 'prev' ? -1 : 1));
      }
    } else if (this.selectedDate) {
      // Fixed: Day navigation uses selectedDate instead of currentDate
      const dayOffset = direction === 'prev' ? -1 : 1;
      newDate.setDate(this.selectedDate.getDate() + dayOffset);
    }
    
    this.currentDate = newDate;
    if (this.currentView === 'day') {
      this.selectedDate = new Date(newDate);
    }
  }

  // Switch between views
  switchView(view: 'month' | 'day') {
    this.currentView = view;
    if (view === 'day' && !this.selectedDate) {
      this.selectedDate = new Date(this.currentDate);
    }
  }

  // Select a specific date
  selectDate(date: Date) {
    this.selectedDate = date;
    this.currentView = 'day';
  }

  // Generate days for the month view - optimized
  getDaysInMonth(): Date[] {
    const year = this.currentDate.getFullYear();
    const month = this.currentDate.getMonth();
    
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    
    // Calculate days from previous month
    const daysFromPrevMonth = firstDay.getDay(); // 0 = Sunday
    // Calculate days from next month (42 days total for 6x7 grid)
    const totalDaysNeeded = 42; // 6 weeks * 7 days
    const daysFromNextMonth = totalDaysNeeded - (daysFromPrevMonth + lastDay.getDate());
    
    const days: Date[] = [];
    
    // Previous month days
    const prevMonthLastDay = new Date(year, month, 0).getDate();
    for (let i = daysFromPrevMonth - 1; i >= 0; i--) {
      days.push(new Date(year, month - 1, prevMonthLastDay - i));
    }
    
    // Current month days
    for (let i = 1; i <= lastDay.getDate(); i++) {
      days.push(new Date(year, month, i));
    }
    
    // Next month days
    for (let i = 1; i <= daysFromNextMonth; i++) {
      days.push(new Date(year, month + 1, i));
    }
    
    return days;
  }

  // Check if a date is today
  isToday(date: Date): boolean {
    return this.isSameDate(date, new Date());
  }

  // Check if a date is selected
  isSelected(date: Date): boolean {
    return this.selectedDate ? this.isSameDate(date, this.selectedDate) : false;
  }

  // Check if a date is in the current month
  isCurrentMonth(date: Date): boolean {
    return date.getMonth() === this.currentDate.getMonth() &&
           date.getFullYear() === this.currentDate.getFullYear();
  }

  // Utility to compare dates (ignoring time)
  private isSameDate(date1: Date, date2: Date): boolean {
    return date1.getDate() === date2.getDate() &&
           date1.getMonth() === date2.getMonth() &&
           date1.getFullYear() === date2.getFullYear();
  }

  // Get month name and year for display
  getMonthYear(): string {
    return this.currentDate.toLocaleString('default', { month: 'long', year: 'numeric' });
  }


  // In your component class
events = [
  {
    date: new Date(2025, 5, 15), // June 15, 2025
    items: [
      { time: '9:30 AM', title: 'Product Strategy Review' },
      { time: '2:00 PM', title: 'Client Presentation' }
    ]
  },
  {
    date: new Date(2025, 5, 16), // June 16, 2025
    items: [
      { time: '4:15 PM', title: 'Team Sync Meeting' }
    ]
  }
];

getEventsForDay(day: Date) {
  const event = this.events.find(e => 
    e.date.getDate() === day.getDate() &&
    e.date.getMonth() === day.getMonth() &&
    e.date.getFullYear() === day.getFullYear()
  );
  return event ? event.items : [];
}
}