import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckedOutEquipmentComponent } from './checked-out-equipment.component';

describe('CheckedOutEquipmentComponent', () => {
  let component: CheckedOutEquipmentComponent;
  let fixture: ComponentFixture<CheckedOutEquipmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckedOutEquipmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckedOutEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
