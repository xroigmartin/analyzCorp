import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {DatePicker} from 'primeng/datepicker';

@Component({
  selector: 'year-date-picker',
  imports: [
    FormsModule,
    DatePicker
  ],
  templateUrl: './year-date-picker.component.html',
  standalone: true,
  styleUrl: './year-date-picker.component.css'
})
export class YearDatePickerComponent {

  @Input() value: Date = new Date();
  @Output() dateChange: EventEmitter<Date> = new EventEmitter<Date>();

  onDateChange(){
    this.dateChange.emit(this.value);
  }
}
