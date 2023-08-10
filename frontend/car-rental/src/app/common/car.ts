import { CarCategory } from "./car-category";

export class Car {


   id: number;
   plate: string;
   make: string;
   model: string;
   fuel: string;
   engine: number;
   year: number;
   capacity: number;
   price: number;
   imageUrl: string;
   category: CarCategory;
   status: boolean;
   image: File;
}

