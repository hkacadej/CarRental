import { Car } from "./car";

export class Reservation {
  constructor(
    public dateFrom: Date,
    public dateTo: Date,
    public car : Car
  ){}
}
