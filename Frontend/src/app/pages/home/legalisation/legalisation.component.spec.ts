import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LegalisationComponent } from './legalisation.component';

describe('DemandeComponent', () => {
  let component: LegalisationComponent;
  let fixture: ComponentFixture<LegalisationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LegalisationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LegalisationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
