import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EquipmentCheckoutComponent } from './components/equipment-checkout/equipment-checkout.component';
import { CheckedOutEquipmentComponent } from './components/checked-out-equipment/checked-out-equipment.component';
import { EquipmentTransactionHistoryComponent } from './components/equipment-transaction-history/equipment-transaction-history.component';
import { LoginComponent } from './login/login.component';

// Import your services
import { EquipmentService } from './services/equipment.service'; // Update the path as needed
import { UserService } from './services/user.service'; // Update the path as needed
import { TransactionService } from './services/transaction.service';
import { environment } from '../environments/environment';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,        
    EquipmentCheckoutComponent,
    CheckedOutEquipmentComponent,
    EquipmentTransactionHistoryComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
  ],
  providers: [
    EquipmentService, // Add EquipmentService
    TransactionService, 
    UserService,       // Add UserService
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true } 
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor() {
    console.log('Environment:', environment);
  }
}