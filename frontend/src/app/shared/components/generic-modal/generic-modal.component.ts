import {Component, EventEmitter, HostListener, Input, Output} from '@angular/core';
import {NgIf, NgStyle} from '@angular/common';
import {TranslatePipe} from '@ngx-translate/core';

@Component({
  selector: 'analayz-corp-generic-modal',
  imports: [
    NgStyle,
    NgIf,
    TranslatePipe
  ],
  templateUrl: './generic-modal.component.html',
  styleUrl: './generic-modal.component.scss'
})
export class GenericModalComponent {
  @Input() visible: boolean = false;
  @Input() header: string = "";
  @Input() style: {[key: string]: any} = {};
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter();
  @Output() confirm : EventEmitter<void> = new EventEmitter();

  onClose() {
    this.visible = false;
    this.visibleChange.emit(this.visible);
  }

  onOverlayClick(event: MouseEvent){
    if((<HTMLElement>event.target).classList.contains('modal-overlay')){
      this.onClose();
    }
  }

  @HostListener('document:keydown.escape', ['$event'])
  onEsc(event: KeyboardEvent){
    if(this.visible){
      this.onClose();
    }
  }
}
