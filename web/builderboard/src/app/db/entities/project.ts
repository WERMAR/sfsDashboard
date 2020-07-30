export class Project {

  orderNumber: string;
  projectDescription: string;
  start: string;
  end: string;
  reminder: boolean;
  startReminder: string;
  endReminder: string;
  responsiblePersonName: string;

  constructor(orderNumber: string,
              projectDescription: string,
              start: string,
              end: string,
              reminder: boolean,
              startReminder: string,
              endReminder: string,
              responsiblePersonName: string) {
    this.orderNumber = orderNumber;
    this.projectDescription = projectDescription;
    this.start = start;
    this.end = end;
    this.reminder = reminder;
    this.startReminder = startReminder;
    this.endReminder = endReminder;
    this.responsiblePersonName = responsiblePersonName;
  }
}
