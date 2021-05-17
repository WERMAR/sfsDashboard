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
    }
}
