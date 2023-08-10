import { TestBed } from '@angular/core/testing';

import { CarValidatorService } from './car-validator';

describe('CarValidatorService', () => {
  let service: CarValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
