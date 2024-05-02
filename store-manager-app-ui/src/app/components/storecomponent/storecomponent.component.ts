import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { NgFor } from '@angular/common';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { StoreserviceService } from '../../services/storeservice.service';

interface Store {
  name: string;
  description: string;
  address: string;
  email: string;
  image: string;
  category: {
    name: string;
  };
}

interface StoresResponse {
  content: Store[];
  first: boolean;
  last: boolean;
}

@Component({
  selector: 'app-storecomponent',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './storecomponent.component.html',
  styleUrl: './storecomponent.component.css'
})
export class StorecomponentComponent {
  stores: StoresResponse = { content: [], first: false, last: false };
  page: number = 0;
  size: number = 10;
  isLoading: boolean = false;

  constructor(private storeService: StoreserviceService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.getStores();
  }

  getStores(): void {
    this.isLoading = true;
    this.storeService.getStores(this.page, this.size).subscribe(data => {
      this.stores = data;
      this.isLoading = false;
    });
  }

  nextPage(): void {
    if (!this.stores.last) {
      this.page++;
      this.getStores();
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.getStores();
    }
  }

  getSafeUrl(imageName: string): SafeUrl {
    const imageUrl = `assets/${imageName}`;
    return this.sanitizer.bypassSecurityTrustUrl(imageUrl);
  }

}
