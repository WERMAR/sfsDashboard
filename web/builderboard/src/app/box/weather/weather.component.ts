import {Component, OnInit} from '@angular/core';
import {WeatherEntity} from './WeatherEntity';
import {Constants} from '../../util/Constants';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css']
})
export class WeatherComponent implements OnInit {

  time = new Date();
  public weatherData: WeatherEntity = null;
  tmpWeatherData: any;

  constructor() {
  }

  ngOnInit() {
    this.setupWeather();
    setInterval(() => {
      this.setupWeather();
      console.log('Request was executed');
    }, Constants.WEATHERTIMER);
    this.setupClock();
  }

  setupClock() {
    setInterval(() => {
      this.time = new Date();
    }, 1000);
  }


  setupWeather() {
    this.tmpWeatherData = {
      main: {},
      isDay: true
    };
    this.getWeatherData();
    console.log(this.tmpWeatherData);
  }

  getWeatherData() {
    fetch('https://api.openweathermap.org/data/2.5/weather?q=berlin&appid=ff1bc4683fc7325e9c57e586c20cc03e')
      .then(response => response.json())
      .then(data => {
        this.setWeatherData(data);
      });
  }

  setWeatherData(data) {
    this.tmpWeatherData = data;
    const sunsetTime = new Date(this.tmpWeatherData.sys.sunset * 1000);
    this.tmpWeatherData.sunset_time = sunsetTime.toLocaleTimeString();
    const currentDate = new Date();
    this.tmpWeatherData.isDay = (currentDate.getTime() < sunsetTime.getTime());
    this.tmpWeatherData.temp_celcius = (this.tmpWeatherData.main.temp - 273.15).toFixed(0);
    this.tmpWeatherData.temp_min = (this.tmpWeatherData.main.temp_min - 273.15).toFixed(0);
    this.tmpWeatherData.temp_max = (this.tmpWeatherData.main.temp_max - 273.15).toFixed(0);
    this.tmpWeatherData.temp_feels_like = (this.tmpWeatherData.main.feels_like - 273.15).toFixed(0);
    this.weatherData = new WeatherEntity(this.tmpWeatherData.isDay,
      this.tmpWeatherData.temp_celcius,
      this.tmpWeatherData.temp_min,
      this.tmpWeatherData.temp_max,
      this.tmpWeatherData.temp_feels_like,
      this.tmpWeatherData.sunset_time,
      this.tmpWeatherData.main, this.tmpWeatherData.name)
    ;
  }
}
