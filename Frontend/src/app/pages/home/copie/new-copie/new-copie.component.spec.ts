import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewCopieComponent } from './new-copie.component';

describe('NewCopieComponent', () => {
  let component: NewCopieComponent;
  let fixture: ComponentFixture<NewCopieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewCopieComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewCopieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
