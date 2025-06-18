import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgIf} from '@angular/common';
import {TranslatePipe} from '@ngx-translate/core';

@Component({
  selector: 'analyz-corp-card',
  imports: [
    NgIf,
    TranslatePipe
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() text!: string;
  @Input() subText: string = "Payload text";
  @Input() editable: boolean = false;
  @Input() deletable: boolean = false;

  @Output() onEdit: EventEmitter<void> = new EventEmitter<void>();
  @Output() onDelete: EventEmitter<void> = new EventEmitter<void>();

  onEditClick(): void {
    this.onEdit.emit();
  }

  onDeleteClick(): void {
    this.onDelete.emit();
  }
}
