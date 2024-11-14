import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from "primeng/inputtext";
import { PasswordModule } from "primeng/password";
import { DividerModule } from 'primeng/divider';
import { SelectModule } from 'primeng/select';
import { RegisterDialogTermosDeUsoComponent } from "./register-dialog-termos-de-uso/register-dialog-termos-de-uso.component";

@Component({
    selector: "reactor-register",
    templateUrl: "register.component.html",
    styleUrl: "register.component.css",
    imports: [
        InputTextModule,PasswordModule,ButtonModule,DividerModule,
        SelectModule,RegisterDialogTermosDeUsoComponent
    ],
    standalone: true,
})
export class RegisterRoute {

    public cargos: Array<{nome: string, codigo: string}> = [
        {nome: "Administrador", codigo: "!&DSA" },
        {nome: "Gerente", codigo: "*(DJS" },
        {nome: "Engenheiro de Setor", codigo: ")_MCD" },
    ]

}