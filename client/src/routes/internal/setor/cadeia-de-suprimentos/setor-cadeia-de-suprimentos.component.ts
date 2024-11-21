import { Component, inject, OnInit, signal } from "@angular/core";
import { OrganizationChartModule } from 'primeng/organizationchart';

import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from "primeng/inputtext";
import { TagModule } from 'primeng/tag';
import { FieldsetModule } from 'primeng/fieldset';
import { PopoverModule } from "primeng/popover";
import { CommonModule } from "@angular/common";
import { AccordionModule } from 'primeng/accordion';
import { DividerModule } from "primeng/divider";
import { PanelModule } from "primeng/panel";
import { ButtonModule } from "primeng/button";
import { TableModule } from 'primeng/table';
import { DialogModule } from "primeng/dialog";
import { SelecaoDeConexaoDialog } from "./dialog-de-selecao-de-conexao/dialog-de-selecao-de-conexao.component";
import { ConexaoService } from "../../../../shared/services/conexao.service";
import { SetorConexao } from "../../../../shared/models/setor-conexao";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-setor-cadeia-de-suprimentos",
    templateUrl: "setor-cadeia-de-suprimentos.component.html",
    styleUrl: "setor-cadeia-de-suprimentos.component.css",
    standalone: true,
    imports: [
        OrganizationChartModule,
        InputGroupModule,
        InputGroupAddonModule,
        InputTextModule,
        TagModule,
        FieldsetModule,
        DialogModule,
        PopoverModule,
        CommonModule,
        AccordionModule,
        DividerModule,
        TableModule,
        ButtonModule,
        PanelModule,
        SelecaoDeConexaoDialog
    ],
    providers: [
        ConexaoService
    ]
})
export class SetorCadeiaDeSuprimentosRoute implements OnInit {
    public dialogDeSelecaoDeConexao = false;
    public setorConexoes = signal<Array<SetorConexao> | null>(null);
    public conexoesService = inject(ConexaoService);
    private messageService = inject(MessageService);

    ngOnInit(): void {
        this.conexoesService.listarSetorConexoes()
        this.setorConexoes = this.conexoesService.getSetorConexoes();
    }

    removerConexao(idConexao: number) {
        this.conexoesService.removerConexaoComSetor(idConexao).subscribe({
            next: () => {
                this.messageService.add({
                    severity: "success",
                    summary: "Deletou!"
                })
                this.conexoesService.listarSetorConexoes();
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "nao Deletou!"
                })
            }
        })
    }
}