import { AfterViewInit, ChangeDetectorRef, Component, inject, OnInit, signal } from "@angular/core";
import { PanelModule } from "primeng/panel";
import { SelectModule } from "primeng/select";
import { FORNECEDORES_DO_MATERIAL_DA_BARRAS_MEDIADORAS_DO_REATOR, FORNECEDORES_DO_MATERIAL_DAS_BARRAS_DE_CONTROLE_DO_REATOR, FORNECEDORES_DO_MATERIAL_DO_COMBUSTIVEL_DO_REATOR } from "./fornecedores";
import { DividerModule } from "primeng/divider";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";
import { ReatorAndUsinaService } from "../../../../shared/services/reator.service";
import { FornecedoresVaretas } from "../../../../shared/models/fornecedor-varetas";
import { Fornecedor } from "../../../../shared/models/fornecedor";
import { FormsModule } from "@angular/forms";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-gerenciar-reator",
    templateUrl: "gerenciar-reator.component.html",
    styleUrl: "gerenciar-reator.component.css",
    imports: [PanelModule, SelectModule, DividerModule, ButtonModule, RippleModule, FormsModule],
    providers: [
        ReatorAndUsinaService
    ],
    standalone: true
})
export class GerenciarReatorRoute implements AfterViewInit {

    private reatorAndUsinaService = inject(ReatorAndUsinaService);
    public fornecedoresDeMateriais = signal<FornecedoresVaretas | null>(null);
    private cdr = inject(ChangeDetectorRef);
    private messageService = inject(MessageService);

    fornecedorDeCombustivelSelecionado: Fornecedor | null | undefined = null;
    fornecedorDeMediadorSelecionado: Fornecedor | null | undefined = null;
    fornecedorDeControleSelecionado: Fornecedor | null | undefined = null;

    listarTudo() {
        this.reatorAndUsinaService.listarFornecedoresDasVaretasDeControle();
        this.fornecedoresDeMateriais = this.reatorAndUsinaService.fornecedoresDeVaretas;
        setTimeout(() => {     
            this.fornecedorDeCombustivelSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasDeCombustivel;
            this.fornecedorDeMediadorSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasMediadores;
            this.fornecedorDeControleSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasDeControle;
        }, 1000);
    }

    alterarFornecedor(tipoVareta: string, {id}: Fornecedor) {
        this.reatorAndUsinaService.mudarFornecedorDoTipoVareta(tipoVareta, id).subscribe({
            next: () => {
                this.messageService.add({
                    severity: "success",
                    summary: "Alterado com Sucesso!"
                })
                this.listarTudo()
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "Erro ao alterar fornecedor"
                })
            }
        })
    }


    ngAfterViewInit(): void {
        this.listarTudo();
        this.cdr.detectChanges();
        setTimeout(() => {     
            this.fornecedorDeCombustivelSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasDeCombustivel;
            this.fornecedorDeMediadorSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasMediadores;
            this.fornecedorDeControleSelecionado = this.fornecedoresDeMateriais()?.fornecedorAtualVaretasDeControle;
            console.log(this.fornecedorDeCombustivelSelecionado);
        }, 1000);
    }
}