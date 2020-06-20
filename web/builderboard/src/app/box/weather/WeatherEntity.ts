export class WeatherEntity {
  isDay: boolean;
  tempCelcius: any;
  tempMin: any;
  tempMax: any;
  tempFeeling: any;
  sunsetTime: any;
  main: any;
  name: string;
  cloud = false;

  constructor(isDay: boolean, tempCelcius: any, tempMin: any, tempMax: any, tempFeeling: any, sunsetTime: any, main: any,
              name: string, cloud: boolean) {
    this.isDay = isDay;
    this.tempCelcius = tempCelcius;
    this.tempMin = tempMin;
    this.tempMax = tempMax;
    this.tempFeeling = tempFeeling;
    this.sunsetTime = sunsetTime;
    this.main = main;
    this.name = name;
    this.cloud = cloud;
  }
}
