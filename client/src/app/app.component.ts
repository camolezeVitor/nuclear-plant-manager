import { Component, inject } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { configurePrimengTheme } from '../configs/theme.config';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
  <router-outlet />
  `
})
export class AppComponent {
  config: PrimeNGConfig = inject(PrimeNGConfig);
  
  constructor() {
    configurePrimengTheme(this.config);
  }

}
