import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { ENVIRONMENT } from "../../configs/environment";

@Injectable()
export class FuncionarioService {
    private httpClient = inject(HttpClient);
    private environment = ENVIRONMENT;

    public cadastrarUmFuncionario(funcionarioForm: any) {
        return this.httpClient.post<void>(`${this.environment.backendUrl}/funcionarios/cadastrar`, funcionarioForm);
    }

    public atualizarUmFuncionario(funcionarioForm: any) {
        return this.httpClient.put<void>(`${this.environment.backendUrl}/funcionarios/atualizar/${funcionarioForm.id}`, funcionarioForm);
    }

    public deletarUmFuncionario(idFuncionario: number) {
        return this.httpClient.delete<void>(`${this.environment.backendUrl}/funcionarios/deletar/${idFuncionario}`);
    }
}