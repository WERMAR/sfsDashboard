import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BoxComponent} from './box/box.component';
import {ListProjectComponent} from './box/list-project/list-project.component';
import {ProjectService} from './box/list-project/project.service';
import {HttpClientModule} from '@angular/common/http';
import {ProjectItemComponent} from './box/list-project/project-item/project-item.component';
import {WeatherComponent} from './box/weather/weather.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {AddProjectDialogComponent} from './box/add-project-dialog/add-project-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatSelectModule} from '@angular/material/select';

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
    MatDialogModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSelectModule
  ],
  entryComponents: [AddProjectDialogComponent],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
