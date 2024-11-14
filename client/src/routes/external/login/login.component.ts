import { Component, Type } from "@angular/core";
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';

@Component({
    selector: "ractor-login",
    templateUrl: "login.component.html",
    styleUrl: "login.component.css",
    standalone: true,
    imports: [InputTextModule,PasswordModule,ButtonModule]
})
export class LoginRoute {}