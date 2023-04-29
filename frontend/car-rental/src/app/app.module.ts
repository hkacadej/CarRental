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

const routes: Routes = [
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
    SearchComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  exports:[
    RouterModule
  ],
  providers: [CarsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
