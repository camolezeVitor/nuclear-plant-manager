import { Component, inject } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from "primeng/inputtext";
import { PasswordModule } from "primeng/password";
import { DividerModule } from 'primeng/divider';
import { SelectModule } from 'primeng/select';
import { InputOtpModule } from 'primeng/inputotp';
import { RegisterDialogTermosDeUsoComponent } from "./register-dialog-termos-de-uso/register-dialog-termos-de-uso.component";
import { CondicaoDeAceitacaoDosTermos } from "./enums/condicao-termos";
import { ReactiveFormsModule } from "@angular/forms";
import { REGISTER_FORM } from "./register-form";
import { UserType } from "../../../shared/enums/user-type-enum";
import { UserService } from "../../../shared/services/user.service";
import { UserForm } from "../../../shared/models/user-form";
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-register",
    templateUrl: "register.component.html",
    styleUrl: "register.component.css",
    imports: [
        InputTextModule,PasswordModule,ButtonModule,DividerModule,
        SelectModule,RegisterDialogTermosDeUsoComponent, InputOtpModule,
        ReactiveFormsModule
    ],
    standalone: true,
})
export class RegisterRoute {
    formulario = REGISTER_FORM;

    public usuarioAceitouOsTermos: boolean = false;
    public messageService = inject(MessageService);
    public userService = inject(UserService);
    public router = inject(Router);

    public cargos: Array<{nome: string, codigo: UserType}> = [
        {nome: "Administrador", codigo: "ADMINISTRADOR" },
        {nome: "Gerente", codigo: "GERENTE" },
        {nome: "Engenheiro de Setor", codigo: "ENGENHEIRO_DE_SETOR" },
    ]

    public alterarEstadoDaCondicaoDeAceitacaoDeTermosPeloUsuario(cond: CondicaoDeAceitacaoDosTermos) {
        this.usuarioAceitouOsTermos = (cond == "SIM");
    }

    public enviarFormularioDeRegistroEIrParaATelaDeLogin() {
        this.userService.registerUser(this.formulario.value as UserForm).subscribe({
            next: (resp: any) => {
                this.router.navigateByUrl("login");
            },
            error: (err: HttpErrorResponse) => {
                this.messageService.add({severity: "error", summary: "ERRO!", detail: "ERRÃO LEGAL !"})
            }
        })
    }

}