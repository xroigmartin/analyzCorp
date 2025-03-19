import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthDataPickerComponent } from './month-data-picker.component';

describe('MonthDataPickerComponent', () => {
  let component: MonthDataPickerComponent;
  let fixture: ComponentFixture<MonthDataPickerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MonthDataPickerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MonthDataPickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
