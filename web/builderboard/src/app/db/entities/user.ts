export class User {
  id: number;
  firstName: string;
  lastName: string;

  constructor(id: number, firstName: string, lastName: string) {
  }

  public static getNameOfUser(firstName: string, lastName: string): string {
    return firstName + ' ' + lastName;
  }
}
