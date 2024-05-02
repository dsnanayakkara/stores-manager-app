import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StorecomponentComponent } from "./components/storecomponent/storecomponent.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [RouterOutlet, StorecomponentComponent]
})
export class AppComponent {
  title = 'store-manager-app-ui';
}
