import { Car } from "./car";

export class Reservate {
  constructor(
    public dateFrom: String,
    public dateTo: String,
    public car : Car
  ){}
}
