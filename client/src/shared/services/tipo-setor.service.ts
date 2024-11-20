import { HttpClient } from "@angular/common/http";
import { inject, Injectable, signal } from "@angular/core";
import { ENVIRONMENT } from "../../configs/environment";
import { TipoSetor } from "../models/tipo-setor";
import { MessageService } from "primeng/api";

@Injectable()
export class TipoSetorService {
    private httpClient = inject(HttpClient);
    private environment = ENVIRONMENT;
    private tiposSetores = signal<Array<TipoSetor> | null>(null);
    private messageService = inject(MessageService);

    getTiposSetores() {
        return this.tiposSetores;
    }

    public listarTodosOsTiposSetor() {
        return this.httpClient.get<Array<TipoSetor>>(`${this.environment.backendUrl}/tipo-setor/buscar-todos`)
    }

    public listarTodosOsTiposSetorSilent() {
        this.httpClient.get<Array<TipoSetor>>(`${this.environment.backendUrl}/tipo-setor/buscar-todos`).subscribe({
            next: (lista) => {
                this.tiposSetores.set(lista)
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "Ocorreu um erro inesperado",
                    detail: "Não sei!"
                })
            }
        })
    } 
}