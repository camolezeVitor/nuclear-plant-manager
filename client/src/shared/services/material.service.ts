import { inject, Injectable, signal } from "@angular/core";
import { Material } from "../models/material";
import { HttpClient } from "@angular/common/http";
import { ENVIRONMENT } from "../../configs/environment";
import { MessageService } from "primeng/api";

@Injectable()
export class MaterialService {
    private environment = ENVIRONMENT;
    private messageService = inject(MessageService);
    private httpClient: HttpClient = inject(HttpClient);
    private materiais = signal<Array<Material> | null>(null);

    public getMateriais() {
        return this.materiais;
    }

    public listarMateriais() {
        this.httpClient.get<Array<Material>>(`${this.environment.backendUrl}/materiais/buscar-todos`).subscribe({
            next: (lista) => {
                this.materiais.set(lista);
            },
            error: (error) => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao listar materiais", 
                    detail: "Ocorreu um erro ao listar os materiais"
                });
            } 
        })
    }
    
}