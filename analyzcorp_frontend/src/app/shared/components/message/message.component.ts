import {Component, EventEmitter, inject, Output} from '@angular/core';
import {Toast} from 'primeng/toast';
import {MessageService, ToastMessageOptions} from 'primeng/api';
import {Button} from 'primeng/button';

@Component({
  standalone: true,
  selector: 'shared-message',
  imports: [Toast, Button],
  templateUrl: './message.component.html',
  styleUrl: './message.component.css',
  providers: [MessageService]
})
export class MessageComponent {

  private messageService: MessageService = inject(MessageService)
  public visible: boolean = false;

  @Output()
  public confirm: EventEmitter<void> = new EventEmitter<void>();

  @Output()
  public reject: EventEmitter<void> = new EventEmitter<void>();

  addMessage(message: ToastMessageOptions): void{
    this.messageService.add(message);
    this.visible = true;
  }

  onConfirm(): void{
    this.messageService.clear('message');
    this.visible = false;
    this.confirm.emit();
  }

  onReject(): void {
    this.messageService.clear('message');
    this.visible = false;
    this.reject.emit();
  }
}
