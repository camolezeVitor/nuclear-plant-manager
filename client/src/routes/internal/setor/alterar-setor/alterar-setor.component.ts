import { Component } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputGroupModule } from "primeng/inputgroup";
import { InputTextModule } from "primeng/inputtext";
import { FormularioSetorComponent } from "../shared/formulario-setor/formulario-adicionar-setor.component";
import { FUNCIONARIOS } from "../shared/mock/funcionarios";
import { UNIDADES_DE_MEDIDAS } from "../shared/mock/medidas";

@Component({
    selector: "reactor-alterar-setor",
    templateUrl: "alterar-setor.component.html",
    styleUrl: "alterar-setor.component.css",
    imports: [InputTextModule, ButtonModule, InputGroupModule, DialogModule, FormularioSetorComponent],
    standalone: true,
})
export class AlterarSetorRoute {
    dialogVisivel = true;
    funcionarios = FUNCIONARIOS;
    medidas = UNIDADES_DE_MEDIDAS;
}