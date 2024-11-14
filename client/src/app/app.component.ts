import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PrimeNGConfig } from 'primeng/api';
import { configurePrimengTheme } from '../configs/theme.config';
import { ButtonModule } from "primeng/button";



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ButtonModule],
  template: `
  <router-outlet />
  <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Et numquam veniam distinctio voluptatibus blanditiis quis nesciunt error nostrum hic officiis at iste ipsa, pariatur voluptatum! Iste consectetur cupiditate beatae mollitia.</p>
  <p-button label="carai lek :)" />
  `,
  styleUrl: './app.component.css'
})
export class AppComponent {
  config: PrimeNGConfig = inject(PrimeNGConfig);
  
  constructor() {
    configurePrimengTheme(this.config);
  }

}
