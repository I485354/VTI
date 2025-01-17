import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserInfo} from '../model/userInfo';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  @Input() user: UserInfo = { user_id: 0, username: '', role: '' };
  @Input() isEditing = false;
  @Output() userSave = new EventEmitter<UserInfo>();
  @Output() userCancel = new EventEmitter<void>();

  onSave() {
    this.userSave.emit(this.user);
  }

  onCancel() {
    this.userCancel.emit();
  }
}
