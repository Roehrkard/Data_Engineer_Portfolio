import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EquipmentCheckoutComponent } from './equipment-checkout.component';

describe('EquipmentCheckoutComponent', () => {
  let component: EquipmentCheckoutComponent;
  let fixture: ComponentFixture<EquipmentCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EquipmentCheckoutComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EquipmentCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
