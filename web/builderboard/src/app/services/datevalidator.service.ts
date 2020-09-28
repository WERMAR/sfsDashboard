import {FormGroup} from '@angular/forms';

export function DateValidator(
  /*   controlName: string, */
  startDateName: string,
  endDateName: string
) {
  return (formGroup: FormGroup) => {
    let startDate;
    let endDate;
    const today = new Date();
    today.setDate(today.getDate() - 1);
    const controlStartDate = formGroup.controls[startDateName];
    const controlEndDate = formGroup.controls[endDateName];
    // startDate
    // convert string to date for comparison
    if (typeof (controlStartDate.value) === 'object') {
      startDate = controlStartDate.value;
    } else {
      startDate = new Date(controlStartDate.value);
    }
    // endDate
    // convert string to date for comparison
    if (typeof (controlEndDate.value) === 'object') {
      endDate = controlEndDate.value;
    } else {
      endDate = new Date(controlEndDate.value);
    }

    if (controlEndDate.errors && !controlEndDate.errors.dateInvalid) {
      return;
    }

    // check if date is valid or not
    if (startDate >= endDate) {
      controlEndDate.setErrors({dateInvalid: true});
      if (startDate < today) {
        controlStartDate.setErrors({dateInvalid: true});
      }
    } else if (startDate < today) {
      controlStartDate.setErrors({dateInvalid: true});
      if (startDate >= endDate) {
        controlEndDate.setErrors({dateInvalid: true});
      }
    } else {
      controlEndDate.setErrors(null);
    }
  };
}
