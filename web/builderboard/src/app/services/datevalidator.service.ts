import { FormGroup, AbstractControl } from "@angular/forms";

export function DateValidator(
    /*   controlName: string, */
      startDateName: string,
      endDateName: string
    ) {
      return (formGroup: FormGroup) => {
        let today = new Date();
        today.setDate(today.getDate() - 1);
        const control_startDate = formGroup.controls[startDateName];
        const control_endDate = formGroup.controls[endDateName];
    
        if (control_endDate.errors && !control_endDate.errors.dateInvalid) {
          return;
        }
    
        if (control_startDate.value >= control_endDate.value){
            control_endDate.setErrors({ dateInvalid: true });
        } 
        else if(control_startDate.value < today){
            control_startDate.setErrors({ dateInvalid: true});
        }
        else{
            control_endDate.setErrors(null);
        }
      };
    }