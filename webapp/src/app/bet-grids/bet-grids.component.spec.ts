import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetGridsComponent } from './bet-grids.component';

describe('BetGridsComponent', () => {
  let component: BetGridsComponent;
  let fixture: ComponentFixture<BetGridsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetGridsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetGridsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
