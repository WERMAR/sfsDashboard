export class Converter {

  /**
   * This method build an normal user name
   * e.g.: Max@Mustermann -> Max Mustermann
   */
  public static convertToNormalUserName(userName: string): string {
    const splittedStrings = userName.split('@');
    return splittedStrings[0] + ' ' + splittedStrings[1];
  }

  public static convertToTransferName(userName: string): string {
    const splittedStrings = userName.split(' ');
    return splittedStrings[0] + '@' + splittedStrings[1];
  }
}
