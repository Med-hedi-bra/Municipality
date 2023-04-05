import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { role } from './model/role';
import { AdminComponent } from './pages/admin/admin.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthenticationGuard, IsLogedIn } from './services/guards/authentication.guard';


const routes: Routes = [

  { path: "home", component: HomeComponent },
  { path: "login", component: LoginComponent, canActivate: [IsLogedIn] },
  { path: "register", component: RegisterComponent, canActivate: [IsLogedIn] },
  { path: "admin", component: AdminComponent, canActivate: [AuthenticationGuard] , data:{roles:[role.ADMIN]} },
  { path: "**", redirectTo: "/home", pathMatch: "full" }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }