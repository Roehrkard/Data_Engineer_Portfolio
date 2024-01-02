import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard'; // Import the AuthGuard

import { EquipmentCheckoutComponent } from './components/equipment-checkout/equipment-checkout.component';
import { CheckedOutEquipmentComponent } from './components/checked-out-equipment/checked-out-equipment.component';
import { EquipmentTransactionHistoryComponent } from './components/equipment-transaction-history/equipment-transaction-history.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'equipment-checkout',
    component: EquipmentCheckoutComponent,
    canActivate: [AuthGuard] // Protect this route with AuthGuard
  },
  {
    path: 'checked-out-equipment',
    component: CheckedOutEquipmentComponent,
    canActivate: [AuthGuard] // Protect this route with AuthGuard
  },
  {
    path: 'equipment-transaction-history',
    component: EquipmentTransactionHistoryComponent,
    canActivate: [AuthGuard] // Protect this route with AuthGuard
  },
  {
    path: '**',
    redirectTo: '/login'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
