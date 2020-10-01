import { FormGroup, AbstractControl } from "@angular/forms";

export function DateValidator(
    /*   controlName: string, */
      startDateName: string,
      endDateName: string,
      project_dialog: string,
    ) {
      return (formGroup: FormGroup) => {
        debugger;
        let startDate, endDate;
        let today = new Date();
        today.setDate(today.getDate() - 1);
        const control_startDate = formGroup.controls[startDateName];
        const control_endDate   = formGroup.controls[endDateName];
        // startDate
        // convert string to date for comparison
        if(typeof(control_startDate.value) === "object"){
             startDate = control_startDate.value;
        }else{
             startDate = new Date(control_startDate.value);
        }
        // endDate
        // convert string to date for comparison
        if(typeof(control_endDate.value) === "object"){
             endDate = control_endDate.value;
        }else{
             endDate = new Date(control_endDate.value);
        }

        if (control_endDate.errors && !control_endDate.errors.dateInvalid) {
          return;
        }

        // check if date is valid or not 
        if (startDate >= endDate){
            control_endDate.setErrors({ dateInvalid: true });
            if(startDate < today && project_dialog === "add"){
              control_startDate.setErrors({ dateInvalid: true});
            }
        } 
        else if(startDate < today && project_dialog === "add"){
            control_startDate.setErrors({ dateInvalid: true});
            if (startDate >= endDate){
              control_endDate.setErrors({ dateInvalid: true });
            }
        }
        else{
            control_endDate.setErrors(null);
        }
      };
    }