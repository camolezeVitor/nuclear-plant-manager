import { inject, Injectable, signal } from "@angular/core";
import { Usuario } from "../models/usuario";
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { ENVIRONMENT } from "../../configs/environment";
import { MessageService } from "primeng/api";

@Injectable()
export class UsuarioService {
    private environment = ENVIRONMENT;
    private httpClient = inject(HttpClient);
    private messageService = inject(MessageService);
    private usuarios = signal<Array<Usuario> | null>(null);

    getUsuarios() {
        return this.usuarios;
    }

    buscarUsuarioPorCadastro(cadastro: string) {
        return this.httpClient.get<Usuario>(`${this.environment.backendUrl}/users/buscar-usuario-por-cadastro/${cadastro}`);
    }

    excluirUmUsuarioPorCadastro(cadastro: string) {
        return this.httpClient.delete(`${this.environment.backendUrl}/users/deletar/${cadastro}`);
    }

    listarUsuarios() {
        this.httpClient.get<Array<Usuario>>(`${this.environment.backendUrl}/users/buscar-todos`).subscribe({
            next: (lista) => {
                this.usuarios.set(lista);
            },
            error: () => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao listar usuários", 
                    detail: "Ocorreu um erro ao listar os usuários"
                });
            }
        })
    }
}