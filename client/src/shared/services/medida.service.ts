import { inject, Injectable, signal, Signal } from "@angular/core";
import { Medida } from "../models/medida";
import { HttpClient } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { ENVIRONMENT } from "../../configs/environment";

@Injectable()
export class MedidaService {
    private environment = ENVIRONMENT;
    private messageService = inject(MessageService);
    private httpClient: HttpClient = inject(HttpClient);
    private medidas = signal<Array<Medida> | null>(null);

    public getMedidas() {
        return this.medidas;
    }

    public listarMedidas() {
        this.httpClient.get<Array<Medida>>(`${this.environment.backendUrl}/medidas/buscar-todas`).subscribe({
            next: (lista) => {
                this.medidas.set(lista);
            },
            error: () => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao listar materiais", 
                    detail: "Ocorreu um erro ao listar os materiais"
                });
            }
        })
    }
}