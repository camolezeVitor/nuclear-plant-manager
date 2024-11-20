import { Component, inject } from "@angular/core";
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { LOGIN_FORM } from "./login-form";
import { ReactiveFormsModule } from "@angular/forms";
import { MessageService } from "primeng/api";
import { UserService } from "../../../shared/services/user.service";
import { UserLoginForm } from "../../../shared/models/user-login-form";

@Component({
    selector: "ractor-login",
    templateUrl: "login.component.html",
    styleUrl: "login.component.css",
    standalone: true,
    imports: [InputTextModule,PasswordModule,ButtonModule, ReactiveFormsModule]
})
export class LoginRoute {
    public formularioLogin = LOGIN_FORM;
    private messageService = inject(MessageService);
    private userService = inject(UserService);

    realizarLogin() {
        if (!this.formularioLogin.valid) {
            this.messageService.add({severity: "danger", summary: "Formulário inválido!", detail: "Você não colocou todas as informações necessárias"})
            return;
        }

        this.userService.loginUser(this.formularioLogin.value as UserLoginForm);
    }
}