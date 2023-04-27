import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsComponent } from './components/cars/cars.component';
import { RouterModule, Routes } from '@angular/router';
import { CarsService } from './service/cars.service';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  {path : 'cars', component: CarsComponent},
  {path : '', redirectTo: '/cars' , pathMatch: 'full'},
  {path : '**', redirectTo: '/cars' , pathMatch: 'full'}
]

@NgModule({
  declarations: [
    CarsComponent,
    AppComponent,
    CarsComponent
  ],
  imports: [
    RouterModule.forRoot(routes),
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [CarsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
