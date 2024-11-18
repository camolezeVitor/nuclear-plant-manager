import { Component } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputGroupModule } from "primeng/inputgroup";
import { InputTextModule } from "primeng/inputtext";
import { FormularioSetorComponent } from "../shared/formulario-setor/formulario-adicionar-setor.component";
import { UNIDADES_DE_MEDIDAS } from "../../../../rules/unidades_de_medida";
import { FUNCIONARIOS } from "../shared/mock/funcionarios";

@Component({
    selector: "reactor-alterar-setor",
    templateUrl: "alterar-setor.component.html",
    styleUrl: "alterar-setor.component.css",
    imports: [InputTextModule, ButtonModule, InputGroupModule, DialogModule, FormularioSetorComponent],
    standalone: true,
})
export class AlterarSetorRoute {
    dialogVisivel = false;
    funcionarios = FUNCIONARIOS;
    medidas = UNIDADES_DE_MEDIDAS;

    abrirDialogEMostrarItemSelecionado() {
        this.dialogVisivel = true;
    }
}