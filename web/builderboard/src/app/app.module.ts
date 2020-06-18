import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BoxComponent } from './box/box.component';
import { ListProjectComponent } from './box/list-project/list-project.component';
import {ProjectService} from './box/list-project/project.service';
import {HttpClientModule} from '@angular/common/http';
import { ProjectItemComponent } from './box/list-project/project-item/project-item.component';
import { WeatherComponent } from './box/weather/weather.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { AddProjectDialogComponent } from './box/add-project-dialog/add-project-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    BoxComponent,
    ListProjectComponent,
    ProjectItemComponent,
    WeatherComponent,
    AddProjectDialogComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonToggleModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule
  ],
  entryComponents: [AddProjectDialogComponent],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
