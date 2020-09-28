import {ActivityHandler, MessageFactory} from 'botbuilder';
import * as axios from 'axios';
import {Project} from './data/project';
import {User} from './data/user';

export class EchoBot extends ActivityHandler {

    private messageSent = false;

    constructor() {
        super();
        this.onMembersAdded(async (context, next) => {
            const membersAdded = context.activity.membersAdded;
            const welcomeText = 'Hello and welcome!';
            for (const member of membersAdded) {
                if (member.id !== context.activity.recipient.id) {
                    await context.sendActivity(MessageFactory.text(welcomeText, welcomeText));
                }
            }
            await next();
        });

        this.onMessage(async (context, next) => {
            if (context.activity.text.toLowerCase().includes('projekte')) {
                let projectData: Project[] = [];
                await loadProjects().then(response => {
                    projectData = response;
                });
                await context.sendActivity(MessageFactory.text(setupResponseStringProject(projectData)));
                this.messageSent = true;
            }
            await next();
        });

        this.onMessage(async (context, next) => {
            if (context.activity.text.toLowerCase().includes('benutzer')) {
                let users: User[] = [];
                await loadUser().then(response => {
                    users = response;
                })
                await context.sendActivity(MessageFactory.text(setupResponseStringUser(users)))
                this.messageSent = true;
            }
            await next();
        })

        this.onMessage(async (context, next) => {
            if (!this.messageSent) {
                await context.sendActivity(MessageFactory.text('Ich konnte deine Nachricht nicht verstehen und kann dir nicht weiterhelfen. Try it again. :)'))
            } else {
                this.messageSent = !this.messageSent;
            }
        });

        async function loadProjects(): Promise<Project[]> {
            let projectData: Project[] = [];
            await axios.default.get<Project[]>('http://localhost:8080/project').then(response => {
                projectData = response.data;
            }).catch(error => {
                console.log(error);
            });
            return projectData;
        }

        async function loadUser(): Promise<User[]> {
            let users: User[] = [];
            await axios.default.get<User[]>('http://localhost:8080/user').then(response => {
                users = response.data;
            }).catch(error => {
                console.log(error);
            });
            return users;
        }

        function setupResponseStringProject(projectData: Project[]) {
            let resultString = 'Aktuell stehen folgende Projekte an: \n';
            for (const project of projectData) {
                resultString += '* ' + project.projectDescription + '\n';
            }
            return resultString;
        }

        function setupResponseStringUser(users: User[]) {
            let resultString = 'Aktuell sind folgende Benutzer bei dir eingerichtet \n';
            for (const user of users) {
                resultString += '* ' + user.firstName + ' ' + user.lastName + '\n';
            }
            return resultString;
        }

    }
}
