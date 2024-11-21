import { Conexao } from "./conexao";
import { Fornecedor } from "./fornecedor";
import { Setor, SetorComDependencias } from "./setor";
import { SetorInfosBasicas } from "./setor-infos-basicas";

export class SetorConexao {
    constructor(
        public informacoesSetor: SetorComDependencias,
        public realProducaoDoSetor: number,
        public setoresDisponiveisParaFornecer: Array<SetorInfosBasicas>,
        public fornecedoresDisponiveisParaFornecer: Array<Fornecedor>,
        public setoresQueJaSeLigam: Array<Conexao>
    ) {}
}