import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentprojComponent } from './studentproj.component';

describe('StudentprojComponent', () => {
  let component: StudentprojComponent;
  let fixture: ComponentFixture<StudentprojComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentprojComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentprojComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
