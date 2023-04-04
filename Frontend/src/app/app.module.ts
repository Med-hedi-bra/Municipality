import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';


import { HomeComponent } from './pages/home/home.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { Section1Component } from './pages/home/components/section1/section1.component';
import { Section2Component } from './pages/home/components/section2/section2.component';
import { Section3Component } from './pages/home/components/section3/section3.component';
import { Section4Component } from './pages/home/components/section4/section4.component';
import { StatsComponent } from './pages/home/components/stats/stats.component';
import { Section5Component } from './pages/home/components/section5/section5.component';
import { Section6Component } from './pages/home/components/section6/section6.component';
import { FooterComponent } from './components/footer/footer.component';



import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AdminComponent } from './pages/admin/admin.component';
import { TokenInterceptorService } from './services/tokenInterceptor/token-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    Section1Component,
    Section2Component,
    Section3Component,
    Section4Component,
    StatsComponent,
    Section5Component,
    Section6Component,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    AdminComponent
   
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:TokenInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
