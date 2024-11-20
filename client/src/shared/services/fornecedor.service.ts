import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { inject, Injectable, signal } from "@angular/core";
import { FornecedorForm } from "../models/fornecedor-form";
import { ENVIRONMENT } from "../../configs/environment";
import { MessageService } from "primeng/api";
import { Fornecedor } from "../models/fornecedor";

@Injectable()
export class FornecedorService {
    private messageService = inject(MessageService);
    private environment = ENVIRONMENT;
    private httpClient = inject(HttpClient);
    private fornecedores = signal<Array<Fornecedor> | null>(null);

    public getFornecedores$() {
        return this.fornecedores;
    }

    public listarTodosOsFornecedores() {
        this.httpClient.get<Array<Fornecedor>>(`${this.environment.backendUrl}/fornecedor/buscar-todos`).subscribe({
            next: (lista) => {
                this.fornecedores.set(lista);
            },
            error: (err: HttpErrorResponse) => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao listar fornecedores", 
                    detail: "Ocorreu um erro ao listar os fornecedores"
                });
            }
        })
    }

    public cadastarUmFornecedor(fornecedorForm: FornecedorForm) {
        this.httpClient.post<void>(`${this.environment.backendUrl}/fornecedor/cadastrar`, fornecedorForm).subscribe({
            next: () => {
                this.messageService.add({
                    severity: 'success', 
                    summary: "Fornecedor cadastrado com sucesso!", 
                    detail: "O fornecedor foi cadastrado com sucesso!"
                })
            },
            error: () => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao cadastrar fornecedor", 
                    detail: "Ocorreu um erro ao cadastrar um fornecedor"
                })
            }
        })
    }

    public excluirUmFornecedor({id}: Fornecedor) {
        this.httpClient.delete(`${this.environment.backendUrl}/fornecedor/deletar/${id}`).subscribe({
            next: () => {
                this.listarTodosOsFornecedores()
                this.messageService.add({
                    severity: 'success', 
                    summary: "Fornecedor deletado com sucesso!", 
                    detail: "O fornecedor foi deletado com sucesso!"
                })
            },
            error: () => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao deletar fornecedor", 
                    detail: "Ocorreu um erro ao deletar um fornecedor"
                })
            }
        })
    }
}