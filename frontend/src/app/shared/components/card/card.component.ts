import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgIf} from '@angular/common';

@Component({
  selector: 'analyz-corp-card',
  imports: [
    NgIf
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() text!: string;
  @Input() subText: string = "Esto es un subtexto";
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
