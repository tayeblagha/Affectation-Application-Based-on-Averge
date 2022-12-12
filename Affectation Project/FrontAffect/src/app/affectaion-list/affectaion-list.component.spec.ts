import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AffectaionListComponent } from './affectaion-list.component';

describe('AffectaionListComponent', () => {
  let component: AffectaionListComponent;
  let fixture: ComponentFixture<AffectaionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AffectaionListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AffectaionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
