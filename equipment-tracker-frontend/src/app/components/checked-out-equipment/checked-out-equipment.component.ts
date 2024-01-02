import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../models/transaction.model';
import { TransactionService } from '../../services/transaction.service';
import { UserService } from '../../services/user.service';
import { CheckInOutService } from '../../services/check-in-out.service';
import { forkJoin, Observable } from 'rxjs';

@Component({
  selector: 'app-checked-out-equipment',
  templateUrl: './checked-out-equipment.component.html',
  styleUrls: ['./checked-out-equipment.component.css']
})
export class CheckedOutEquipmentComponent implements OnInit {
  transactions: Transaction[] = [];
  selectedTransactions: Set<number> = new Set();

  constructor(
    private transactionService: TransactionService,
    private userService: UserService,
    private checkInOutService: CheckInOutService // Inject CheckInOutService
  ) {}

  ngOnInit(): void {
    this.loadUserTransactions();
  }

  loadUserTransactions(): void {
    this.userService.getUsername().subscribe((username) => {
      if (username) {
        this.transactionService.getTransactionsByUsername(username).subscribe({
          next: (data) => {
            this.transactions = data.filter(t => t.status === 'Checked Out' && t.username === username);
          },
          error: (error) => {
            console.error('Error fetching transactions:', error);
          }
        });
      }
    });
  }

  toggleTransactionSelection(transactionId: number): void {
    if (this.selectedTransactions.has(transactionId)) {
      this.selectedTransactions.delete(transactionId);
    } else {
      this.selectedTransactions.add(transactionId);
    }
  }

  checkIn(): void {
    const checkInObservables: Observable<Transaction>[] = [];
    this.selectedTransactions.forEach(transactionId => {
      const transaction = this.transactions.find(t => t.transactionId === transactionId);
      if (transaction) {
        console.log(`Checking in transaction ID ${transaction.transactionId} with quantity ${transaction.quantity}`);
        checkInObservables.push(
          this.checkInOutService.checkinEquipment(
            transaction.userId,
            transaction.equipmentId,
            transaction.quantity
          )
        );
      }
    });

    forkJoin(checkInObservables).subscribe({
      next: (checkedInTransactions) => {
        checkedInTransactions.forEach(trans => {
          console.log(`Transaction ${trans.transactionId} checked in successfully.`);
        });

        // Remove checked-in transactions from the 'transactions' array
        this.transactions = this.transactions.filter(t => !this.selectedTransactions.has(t.transactionId));

        this.selectedTransactions.clear();
      },
      error: (error) => {
        console.error('Error checking in equipment:', error);
      }
    });
  }
}
