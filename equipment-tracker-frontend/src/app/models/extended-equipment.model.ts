// extended-equipment.model.ts
import { Equipment } from './equipment.model';

export interface ExtendedEquipment extends Equipment {
  selectedQty: number;
}
