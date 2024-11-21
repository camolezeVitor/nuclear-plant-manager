import { Fornecedor } from "./fornecedor";
import { Setor } from "./setor";
import { SetorInfosBasicas } from "./setor-infos-basicas";

export class Conexao {
    constructor (
        public id: number,
        public idProvedor: number,
        public prioridade: number,
        public tipoConexao: string,
        public provedorCasoTipoConexaoSejaSetor: SetorInfosBasicas,
        public provedorCasoTipoConexaoSejaFornecedor: Fornecedor
    ) {}
}