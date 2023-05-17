import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLegalistaionComponent } from './new-legalistaion.component';

describe('NewLegalistaionComponent', () => {
  let component: NewLegalistaionComponent;
  let fixture: ComponentFixture<NewLegalistaionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewLegalistaionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewLegalistaionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
