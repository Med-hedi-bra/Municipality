import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CopieComponent } from './copie.component';

describe('CopieComponent', () => {
  let component: CopieComponent;
  let fixture: ComponentFixture<CopieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CopieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CopieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
