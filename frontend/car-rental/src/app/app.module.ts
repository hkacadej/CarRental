import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsComponent } from './components/cars/cars.component';
import { RouterModule, Routes } from '@angular/router';
import { CarsService } from './service/cars.service';
import { HttpClientModule } from '@angular/common/http';
import { CarsCateogryComponent } from './components/cars-cateogry/cars-cateogry.component';
import { CarsDetailComponent } from './components/cars-detail/cars-detail.component';
import { SearchComponent } from './search/search/search.component';
import { DatePickerComponent } from './component/date-picker/date-picker.component';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const routes: Routes = [
  {path : 'reserve/:id', component: DatePickerComponent},
  {path : 'cars/:id/:name', component: CarsDetailComponent},
  {path : 'search/:keyword', component: CarsComponent},
  {path : 'category/:id/:name', component: CarsComponent},
  {path : 'category', component: CarsComponent},
  {path : 'cars', component: CarsComponent},
  {path : '', redirectTo: '/cars' , pathMatch: 'full'},
  {path : '**', redirectTo: '/cars' , pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    CarsComponent,
    CarsCateogryComponent,
    CarsDetailComponent,
    SearchComponent,
    DatePickerComponent

  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatFormFieldModule ,
    MatSelectModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,

  ],
  exports:[
    RouterModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatIconModule,
    DatePickerComponent
  ],
  providers: [CarsService,],
  bootstrap: [AppComponent]
})
export class AppModule { }
