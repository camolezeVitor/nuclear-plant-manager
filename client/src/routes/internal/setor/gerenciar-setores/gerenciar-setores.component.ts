import { Component } from "@angular/core";
import { SETORES } from "./gerenciar-setores-setores";
import { CommonModule } from "@angular/common";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";

@Component({
    selector: "reactor-gerenciar-setores",
    templateUrl: "gerenciar-setores.component.html",
    styleUrl: "gerenciar-setores.component.css",
    imports: [CommonModule, TableModule, ButtonModule, RippleModule],
    standalone: true
})
export class GerenciarSetoresRoute {
    public setores = SETORES;

    getStatusClass(status: string): string {
        switch (status) {
            case 'Operacional':
                return 'status-operacional';
            case 'Inspeção':
                return 'status-inspecao';
            case 'Revisão':
                return 'status-revisao';
            case 'Inativo':
                return 'status-inativo';
            default:
                return '';
        }
    }
    
    getRiscoClass(risco: string): string {
        switch (risco) {
            case 'Baixo':
                return 'risco-baixo';
            case 'Moderado':
                return 'risco-moderado';
            case 'Alto':
                return 'risco-alto';
            case 'Nenhum':
                return 'risco-nenhum';
            default:
                return '';
        }
    }
}