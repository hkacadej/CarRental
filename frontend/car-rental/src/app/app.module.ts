import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsComponent } from './components/cars/cars.component';
import { RouterModule, Routes } from '@angular/router';
import { CarsService } from './service/cars.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
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
import { LoginComponent } from './authentication/components/login/login.component';
import { InterceptorService } from './authentication/services/interceptor.service';
import { AuthGuard } from './authentication/guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },

  // Protected route that requires authentication and 'admin' role
  //{ path: 'protected', component: ProtectedComponent, canActivate: [AuthGuard], data: { requiredRoles: ['admin'] } },

  {path : 'reserve/:id', component: DatePickerComponent, canActivate: [AuthGuard]},
  {path : 'cars/:id/:name', component: CarsDetailComponent, canActivate: [AuthGuard]},
  {path : 'search/:keyword', component: CarsComponent, canActivate: [AuthGuard]},
  {path : 'category/:id/:name', component: CarsComponent, canActivate: [AuthGuard]},
  {path : 'category', component: CarsComponent, canActivate: [AuthGuard]},
  {path : 'cars', component: CarsComponent, canActivate: [AuthGuard]},
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
    DatePickerComponent,
    LoginComponent

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
  providers: [
    CarsService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true,
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
