import { Component, inject, OnInit } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { UsuarioService } from "../../../../../shared/services/usuarios.service";
import { Usuario } from "../../../../../shared/models/usuario";
import { FormsModule } from "@angular/forms";
import { CommonModule } from "@angular/common";
import { MessageService } from "primeng/api";
import { HttpErrorResponse } from "@angular/common/http";

@Component({
    selector: "reactor-remover-usuarios",
    templateUrl: "remover-usuarios.component.html",
    styleUrl: "remover-usuarios.component.css",
    imports: [InputTextModule, ButtonModule, FormsModule, CommonModule],
    providers: [UsuarioService],
    standalone: true
})
export class RemoverUsuariosDialog implements OnInit {
    public usuarioService = inject(UsuarioService);
    public codigoDoUsuarioSelecionado: string = "";
    private messageService = inject(MessageService);
    public usuarioSelecionado: Usuario | null = null;

    ngOnInit(): void {
        this.usuarioSelecionado = null;
        this.codigoDoUsuarioSelecionado = "";
    }

    buscarUmUsuario() {
        this.usuarioService.buscarUsuarioPorCadastro(this.codigoDoUsuarioSelecionado).subscribe({
            next: (usuario: Usuario) => {
                this.usuarioSelecionado = usuario;
            }, 
            error: (err: HttpErrorResponse) => {
                this.messageService.add({
                    severity: 'error', 
                    summary: "Erro ao buscar usuário", 
                    detail: "Ocorreu um erro ao buscar o usuário"
                });
            }
        })
    }

    deletarUmUsuario() {
        if (this.usuarioSelecionado) {

            this.usuarioService.excluirUmUsuarioPorCadastro(this.usuarioSelecionado.cadastro).subscribe({
                next: () => {
                    this.messageService.add({
                        severity: 'error', 
                        summary: "Usuário deletado com Sucesso!", 
                        detail: "oba :)"
                    });
                },
                error: () => {
                    this.messageService.add({
                        severity: 'error', 
                        summary: "Erro ao deletar usuário", 
                        detail: "Ocorreu um erro ao deletar o usuário"
                    });
                }
            })
        }
    }
}