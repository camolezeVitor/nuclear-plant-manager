import { Component } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";

@Component({
    selector: "reactor-alterar-cargo-do-usuario",
    templateUrl: "altear-cargo-usuario.component.html",
    styleUrl: "altear-cargo-usuario.component.css",
    imports: [InputTextModule, ButtonModule, SelectModule],
    standalone: true
})
export class AlterarCargoDoUsuarioDialog {
    public cargos: Array<{nome: string, codigo: string}> = [
        {nome: "Administrador", codigo: "!&DSA" },
        {nome: "Gerente", codigo: "*(DJS" },
        {nome: "Engenheiro de Setor", codigo: ")_MCD" },
    ]
}