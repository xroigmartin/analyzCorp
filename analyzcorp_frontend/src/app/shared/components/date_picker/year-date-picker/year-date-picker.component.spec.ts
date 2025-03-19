import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YearDatePickerComponent } from './year-date-picker.component';

describe('YearDatePickerComponent', () => {
  let component: YearDatePickerComponent;
  let fixture: ComponentFixture<YearDatePickerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [YearDatePickerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(YearDatePickerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
