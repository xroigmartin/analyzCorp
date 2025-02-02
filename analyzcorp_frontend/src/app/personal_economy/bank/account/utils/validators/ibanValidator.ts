import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export function ibanValidator(): ValidatorFn {
  return(control: AbstractControl): ValidationErrors | null => {
    const ibanRegex: RegExp = new RegExp('^[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}$');
    const invalidIban: boolean = ibanRegex.test(control.value);
    return invalidIban ? {invalidIban: {value: control.value}} : null;
  }
}
