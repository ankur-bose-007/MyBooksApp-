import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations' 
import { MatToolbarModule, MatToolbar } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthGuardService } from './auth-guard.service';
import { BookModule } from './modules/book/book.module';
import { SharedModule } from './modules/shared/shared.module';
const appRoutes:Routes=[
  {
    path:'',
    redirectTo:'/login',
    pathMatch:'full'
  }
]
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BookModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    AuthenticationModule,
    SharedModule
  ],
  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
