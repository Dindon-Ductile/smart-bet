import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CombinedBetComponent } from './combined-bet.component';

describe('CombinedBetComponent', () => {
  let component: CombinedBetComponent;
  let fixture: ComponentFixture<CombinedBetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CombinedBetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CombinedBetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
