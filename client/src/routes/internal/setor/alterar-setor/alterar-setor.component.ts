import { Component, inject, OnInit } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { DialogModule } from "primeng/dialog";
import { InputGroupModule } from "primeng/inputgroup";
import { InputTextModule } from "primeng/inputtext";
import { FormularioSetorComponent } from "../shared/formulario-setor/formulario-adicionar-setor.component";
import { UNIDADES_DE_MEDIDAS } from "../../../../rules/unidades_de_medida";
import { FUNCIONARIOS } from "../shared/mock/funcionarios";
import { SetorService } from "../../../../shared/services/setor.service";
import { FormsModule } from "@angular/forms";
import { Setor, SetorComDependencias } from "../../../../shared/models/setor";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-alterar-setor",
    templateUrl: "alterar-setor.component.html",
    styleUrl: "alterar-setor.component.css",
    imports: [InputTextModule, ButtonModule, InputGroupModule, DialogModule,FormsModule, FormularioSetorComponent],
    providers: [
        SetorService
    ],
    standalone: true,
})
export class AlterarSetorRoute implements OnInit{
    dialogVisivel = false;
    funcionarios = FUNCIONARIOS;
    medidas = UNIDADES_DE_MEDIDAS;
    public setorService = inject(SetorService);
    public setorSelecionado: SetorComDependencias | null = null;
    public codigoSetor: string = "";
    private messageService = inject(MessageService);

    ngOnInit(): void {
        this.codigoSetor = "";
        this.setorSelecionado = null;
    }

    buscarSetorETalvezAbrirDialog() {
        this.setorService.buscarSetorPorCodigo(this.codigoSetor).subscribe({
            next: (setorEncontrado: SetorComDependencias) => {
                this.setorSelecionado = setorEncontrado;
                this.abrirDialogEMostrarItemSelecionado()
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "ERRO!",
                    detail: "Não foi encontrado o setor"
                })
            }
        });
    }

    atualizarSetor(event: any) {
        
        this.setorService.atualizarUmSetor(event, this.setorSelecionado!.id).subscribe({
            next: () => {
                this.messageService.add({
                    severity: "success",
                    summary: "Parabéns!!",
                    detail: "Setor atualizado com sucesso!"
                })
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "ERRO!",
                    detail: "Não foi possível atualizar o setor"
                })
            }
        })
    }

    abrirDialogEMostrarItemSelecionado() {
        this.dialogVisivel = true;
    }
}