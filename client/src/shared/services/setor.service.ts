import { inject, Injectable, signal } from "@angular/core";
import { Setor, SetorComDependencias } from "../models/setor";
import { HttpClient } from "@angular/common/http";
import { ENVIRONMENT } from "../../configs/environment";
import { SetorComFuncionarios } from "../models/setor-com-funcionarios";

@Injectable()
export class SetorService {
    public httpClient = inject(HttpClient);
    private environment = ENVIRONMENT;

    public listarTodosOsSetores() {
        return this.httpClient.get<Array<SetorComDependencias>>(`${this.environment.backendUrl}/setores/buscar-todos`);
    }

    public listarTodosOsSetoresComFuncionarios() {
        return this.httpClient.get<Array<SetorComFuncionarios>>(`${this.environment.backendUrl}/setores/buscar-todos-com-funcionarios`);
    }

    public cadastrarUmSetor(setorForm: any) {
        return this.httpClient.post<void>(`${this.environment.backendUrl}/setores/cadastrar`, setorForm);
    }

    public atualizarUmSetor(setorForm: any, idSetor: number) {
        return this.httpClient.put<void>(`${this.environment.backendUrl}/setores/atualizar/${idSetor}`, setorForm);
    }

    public deletarUmSetor({id}: Setor) {
        return this.httpClient.delete(`${this.environment.backendUrl}/setores/deletar/${id}`);
    }

    public buscarSetorPorCodigo(codigoSetor: string) {
        return this.httpClient.get<SetorComDependencias>(`${this.environment.backendUrl}/setores/buscar-por-codigo-setor/${codigoSetor}`);
    }
}