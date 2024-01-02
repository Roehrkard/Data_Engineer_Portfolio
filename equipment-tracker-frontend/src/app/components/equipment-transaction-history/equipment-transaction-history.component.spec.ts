import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentTransactionHistoryComponent } from './equipment-transaction-history.component';

describe('EquipmentTransactionHistoryComponent', () => {
  let component: EquipmentTransactionHistoryComponent;
  let fixture: ComponentFixture<EquipmentTransactionHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EquipmentTransactionHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipmentTransactionHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
