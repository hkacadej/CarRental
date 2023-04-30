import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

const today = new Date();
const month = today.getMonth();
const year = today.getFullYear();

@Component({
  selector: 'app-date-picker',
  templateUrl: './date-picker.component.html',
  styles: [`
    .example-form-field {
      margin: 0 8px 16px 0;
    }
  `]
})


export class DatePickerComponent {
  campaignOne = new FormGroup({
    start: new FormControl(new Date(year, month, 13)),
    end: new FormControl(new Date(year, month, 16)),
  });

  campaignTwo = new FormGroup({
    start: new FormControl(new Date(year, month, 15)),
    end: new FormControl(new Date(year, month, 19)),
  });

  disableRange(date: Date ): boolean {
    const april1st = new Date(2023, 3, 1); // Note that the month is zero-indexed (0 = January, 11 = December)
    const april5th = new Date(2023, 3, 5);
    return date && date >= april1st && date <= april5th;
  }
}
