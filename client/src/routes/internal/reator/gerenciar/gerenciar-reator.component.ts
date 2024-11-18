import { Component } from "@angular/core";
import { PanelModule } from "primeng/panel";
import { SelectModule } from "primeng/select";
import { FORNECEDORES_DO_MATERIAL_DA_BARRAS_MEDIADORAS_DO_REATOR, FORNECEDORES_DO_MATERIAL_DAS_BARRAS_DE_CONTROLE_DO_REATOR, FORNECEDORES_DO_MATERIAL_DO_COMBUSTIVEL_DO_REATOR } from "./fornecedores";
import { DividerModule } from "primeng/divider";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";

@Component({
    selector: "reactor-gerenciar-reator",
    templateUrl: "gerenciar-reator.component.html",
    styleUrl: "gerenciar-reator.component.css",
    imports: [PanelModule, SelectModule, DividerModule, ButtonModule, RippleModule],
    standalone: true
})
export class GerenciarReatorRoute {
    public fornecedoresDoMaterialDoCombustivelDoReator = FORNECEDORES_DO_MATERIAL_DO_COMBUSTIVEL_DO_REATOR;
    public fornecedoresDoMaterialDasBarraDeControleDoReator = FORNECEDORES_DO_MATERIAL_DAS_BARRAS_DE_CONTROLE_DO_REATOR;
    public fornecedoresDoMaterialDasBarrasMediadorasDoReator = FORNECEDORES_DO_MATERIAL_DA_BARRAS_MEDIADORAS_DO_REATOR;
}