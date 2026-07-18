import { CommonModule } from '@angular/common';
import { booleanAttribute, Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Api } from '../service/api';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {

constructor(private readonly apiService: Api, private router: Router){}

get isAuthenticated(): boolean
  {
    return this.apiService.isAuthenticated();
  }

  handleLogout(): void
  {
    const isLogout = window.confirm("Are you sure you want to logout?");

    if(isLogout)
    {
      this.apiService.logout();
      this.router.navigate(['/login']);
    }
  }

}
