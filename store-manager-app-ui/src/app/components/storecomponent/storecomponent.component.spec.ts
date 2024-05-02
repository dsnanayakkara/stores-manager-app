import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StorecomponentComponent } from './storecomponent.component';

describe('StorecomponentComponent', () => {
  let component: StorecomponentComponent;
  let fixture: ComponentFixture<StorecomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StorecomponentComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StorecomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
