import { Component, inject } from "@angular/core";
import { ButtonModule } from "primeng/button";
import { InputTextModule } from "primeng/inputtext";
import { SelectModule } from "primeng/select";
import { UsuarioService } from "../../../../../shared/services/usuarios.service";
import { HttpErrorResponse } from "@angular/common/http";
import { MessageService } from "primeng/api";
import { Usuario } from "../../../../../shared/models/usuario";

@Component({
    selector: "reactor-alterar-cargo-do-usuario",
    templateUrl: "altear-cargo-usuario.component.html",
    styleUrl: "altear-cargo-usuario.component.css",
    imports: [InputTextModule, ButtonModule, SelectModule],
    providers: [UsuarioService],
    standalone: true
})
export class AlterarCargoDoUsuarioDialog {
    public cargos: Array<{nome: string, codigo: string}> = [
        {nome: "Administrador", codigo: "ADMINISTRADOR" },
        {nome: "Gerente", codigo: "GERENTE" },
        {nome: "Engenheiro de Setor", codigo: "ENGENHEIRO_DE_SETOR" },
    ]

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