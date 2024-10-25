import {Component, EventEmitter, Output} from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { faTachometerAlt, faFileInvoice, faFileAlt, faUsers, faMoneyCheckAlt, faCog } from '@fortawesome/free-solid-svg-icons';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterModule, FontAwesomeModule, FormsModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  collapsed = false;

  faTachometerAlt = faTachometerAlt;
  faFileInvoice = faFileInvoice;
  faFileAlt = faFileAlt;
  faUsers = faUsers;
  faMoneyCheckAlt = faMoneyCheckAlt;
  faCog = faCog;
  @Output() collapsedChange = new EventEmitter<boolean>();

  toggleSidebar() {
    this.collapsed = !this.collapsed;
    this.collapsedChange.emit(this.collapsed);
  }
}
