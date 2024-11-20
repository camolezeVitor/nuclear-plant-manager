import { Component, inject } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { configurePrimengTheme } from '../configs/theme.config';
import { RouterModule } from '@angular/router';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, ToastModule],
  template: `
  <p-toast />
  <router-outlet />
  `
})
export class AppComponent {
  config: PrimeNGConfig = inject(PrimeNGConfig);
  
  constructor() {
    configurePrimengTheme(this.config);
  }

}
