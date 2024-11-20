import { Component, inject, OnInit, signal } from "@angular/core";
import { SETORES } from "./gerenciar-setores-setores";
import { CommonModule } from "@angular/common";
import { TableModule } from "primeng/table";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";
import { SetorService } from "../../../../shared/services/setor.service";
import { Setor, SetorComDependencias } from "../../../../shared/models/setor";
import { MessageService } from "primeng/api";

@Component({
    selector: "reactor-gerenciar-setores",
    templateUrl: "gerenciar-setores.component.html",
    styleUrl: "gerenciar-setores.component.css",
    imports: [CommonModule, TableModule, ButtonModule, RippleModule],
    providers: [
        SetorService
    ],
    standalone: true
})
export class GerenciarSetoresRoute implements OnInit {
    public setorService = inject(SetorService);
    public messageService = inject(MessageService);
    public setores = signal<Array<SetorComDependencias> | null>(null);

    public listarSetores() {
        this.setorService.listarTodosOsSetores().subscribe({
            next: (lista) => {
                this.setores.set(lista);
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    detail: "Ocorre um erro ao lista o setor",
                    summary: "ERRO!"
                })
            }
        })
    }

    public deletarSetor(setor: SetorComDependencias) {
        this.setorService.deletarUmSetor(setor).subscribe({
            next: () => {
                this.listarSetores();
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    detail: "Ocorre um erro ao deletar o setor",
                    summary: "ERRO!"
                })
            }
        })
    }
    
    ngOnInit(): void {
        this.listarSetores();
    }
}