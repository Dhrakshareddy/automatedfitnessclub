import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerselfregComponent } from './trainerselfreg.component';

describe('TrainerselfregComponent', () => {
  let component: TrainerselfregComponent;
  let fixture: ComponentFixture<TrainerselfregComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TrainerselfregComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TrainerselfregComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
