import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MealsOverviewComponent } from './view/meals-overview/meals-overview.component';

@NgModule({
  declarations: [
    AppComponent,
    MealsOverviewComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
