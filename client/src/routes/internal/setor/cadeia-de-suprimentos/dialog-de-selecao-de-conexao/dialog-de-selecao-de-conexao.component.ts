import { Component, inject, input } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { Setor } from "../../../../../shared/models/setor";
import { TableModule } from "primeng/table";
import { DividerModule } from "primeng/divider";
import { PopoverModule } from "primeng/popover";
import { InputTextModule } from "primeng/inputtext";
import { SetorInfosBasicas } from "../../../../../shared/models/setor-infos-basicas";
import { Fornecedor } from "../../../../../shared/models/fornecedor";
import { ConexaoService } from "../../../../../shared/services/conexao.service";
import { FormsModule } from "@angular/forms";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-selecao-de-conexao",
    templateUrl: "dialog-de-selecao-de-conexao.component.html",
    styleUrl: "dialog-de-selecao-de-conexao.component.css",
    imports: [DialogModule, ButtonModule, TableModule, DividerModule, FormsModule, PopoverModule, InputTextModule],
    standalone: true,
})
export class SelecaoDeConexaoDialog {
    public dialogAberto = false;
    public possiveisSetores = input<Array<SetorInfosBasicas> | null>();
    public possiveisFornecedores = input<Array<Fornecedor> | null>();
    public service = input<ConexaoService>();
    public setor = input<SetorInfosBasicas>();

    private messageService = inject(MessageService);

    public setorFornecedorSelecionado: any | null = null;
    public tipoConexao: "SETOR" | "FORNECEDOR" | null = null;
    public prioriodade: number | null = null;

    limparCampos() {
        this.setorFornecedorSelecionado = null;
        this.tipoConexao = null;
        this.prioriodade = null;
    }

    cadastrarConexao() {
        if (this.setorFornecedorSelecionado.id && this.setor()?.id && this.prioriodade && this.tipoConexao) {
            const formulario = {
                idProvedor: this.setorFornecedorSelecionado.id,
                idDependente: this.setor()?.id,
                prioridade: this.prioriodade,
                tipoConexao: this.tipoConexao,
            }
            this.service()?.cadastrarConexaoComSetorOuFornecedor(formulario).subscribe({
                next: () => {
                    this.messageService.add({
                        severity: "success",
                        summary: "é 2"
                    })
                    this.service()?.listarSetorConexoes();
                }, 
                error: () => {
                    this.messageService.add({
                        severity: "error",
                        summary: "é"
                    })
                }
            })
        } else {

        }
    }
}