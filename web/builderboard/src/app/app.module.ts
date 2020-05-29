import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BoxComponent } from './box/box.component';
import { ListProjectComponent } from './box/list-project/list-project.component';
import {ProjectService} from './box/list-project/project.service';
import {HttpClientModule} from '@angular/common/http';
import { ProjectItemComponent } from './box/list-project/project-item/project-item.component';

@NgModule({
  declarations: [
    AppComponent,
    BoxComponent,
    ListProjectComponent,
    ProjectItemComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
