import { HttpClient } from "@angular/common/http";
import { inject, Injectable, signal } from "@angular/core";
import { MessageService } from "primeng/api";
import { SetorConexao } from "../models/setor-conexao";
import { ENVIRONMENT } from "../../configs/environment";

@Injectable()
export class ConexaoService {
    private httpClient = inject(HttpClient);
    private messageService = inject(MessageService);
    private environment = ENVIRONMENT;

    private setorConexoes = signal<Array<SetorConexao> | null>(null);

    public getSetorConexoes() {
        return this.setorConexoes;
    }

    public listarSetorConexoes() {
        this.httpClient.get<Array<SetorConexao>>(`${this.environment.backendUrl}/setores/buscar-informacoes-para-conexao`).subscribe({
            next: (lista) => {
                this.setorConexoes.set(lista);
            },
            error: () => {
                this.messageService.add({
                    severity: "error",
                    summary: "caria",
                    detail: "N,NN,N,N,N,N,N,N,N,"
                })
            }
        })
    }
    
    public cadastrarConexaoComSetorOuFornecedor(formulario: any) {
        return this.httpClient.post<void>(`${this.environment.backendUrl}/conexao/criar-conexao`, formulario);
    }

    public removerConexaoComSetor(idConexao: number) {
        return this.httpClient.delete<void>(`${this.environment.backendUrl}/conexao/deletar/${idConexao}`);
    }
}