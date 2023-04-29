import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarsCateogryComponent } from './cars-cateogry.component';

describe('CarsCateogryComponent', () => {
  let component: CarsCateogryComponent;
  let fixture: ComponentFixture<CarsCateogryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarsCateogryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarsCateogryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
