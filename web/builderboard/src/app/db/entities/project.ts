import {User} from './user';

export class Project {

  id: number;
  orderNumber: number;
  projectDescription: string;
  start: number;
  end: number;
  reminder: boolean;
  startReminder: number;
  endReminder: number;
  responsiblePerson: User;

  constructor(id: number, orderNumber: number, projectDescription: string, start: number,
              end: number, reminder: boolean, startReminder: number, endReminder: number, responsiblePerson: User) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.projectDescription = projectDescription;
    this.start = start;
    this.end = end;
    this.reminder = reminder;
    this.startReminder = startReminder;
    this.endReminder = endReminder;
    this.responsiblePerson = responsiblePerson;
  }
}
