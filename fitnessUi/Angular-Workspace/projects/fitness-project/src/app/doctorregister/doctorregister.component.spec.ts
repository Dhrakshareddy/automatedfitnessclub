import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorregisterComponent } from './doctorregister.component';

describe('DoctorregisterComponent', () => {
  let component: DoctorregisterComponent;
  let fixture: ComponentFixture<DoctorregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DoctorregisterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DoctorregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
