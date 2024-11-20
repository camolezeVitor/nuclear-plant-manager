import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { ENVIRONMENT } from "../../configs/environment";
import { UserForm } from "../models/user-form";
import { UserLoginForm } from "../models/user-login-form";
import { LoginService } from "../security/logged.service";
import { MessageService } from "primeng/api";

@Injectable({
    providedIn: "root",
})
export class UserService {
    private httpClient: HttpClient = inject(HttpClient);
    private environment = ENVIRONMENT;
    private loginService: LoginService = inject(LoginService);
    private messageService: MessageService = inject(MessageService);

    public registerUser(userForm: UserForm) {
        return this.httpClient.post<any>(`${this.environment.backendUrl}/users/cadastrar`, userForm);
    }

    public loginUser(userLoginForm: UserLoginForm) {
        this.httpClient.post<string>(`${this.environment.backendUrl}/users/login`, userLoginForm, { responseType: 'text' as 'json'}).subscribe({
            next: (token: string) => {
                console.log(token)
                this.loginService.loginUser(token);
            },
            error: (err: HttpErrorResponse) => {
                console.log("DEU ERRO!!!!", err);
                this.messageService.add({severity: "danger", summary: "Usuário ou senha incorretos!", detail: "abacate"})
            }
        })
    }
}