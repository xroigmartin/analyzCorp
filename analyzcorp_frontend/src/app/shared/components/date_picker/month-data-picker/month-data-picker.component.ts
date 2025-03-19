import {Component, EventEmitter, Input, Output} from '@angular/core';
import {DatePicker} from 'primeng/datepicker';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'month-data-picker',
  imports: [
    DatePicker,
    FormsModule
  ],
  templateUrl: './month-data-picker.component.html',
  standalone: true,
  styleUrl: './month-data-picker.component.css'
})
export class MonthDataPickerComponent {
  @Input() value: Date = new Date();
  @Output() dateChange: EventEmitter<Date> = new EventEmitter<Date>();

  onDateChange(){
    this.dateChange.emit(this.value);
  }
}
