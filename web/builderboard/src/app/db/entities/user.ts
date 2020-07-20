export class User {
  id: number;
  firstName: string;
  lastName: string;

  constructor(id: number, firstName: string, lastName: string) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public static getNameOfUser(firstName: string, lastName: string): string {
    return firstName + ' ' + lastName;
  }
}
