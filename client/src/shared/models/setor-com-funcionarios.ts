import { Funcionario } from "./funcionario";
import { Material } from "./material";
import { Medida } from "./medida";
import { SetorComDependencias } from "./setor";
import { TipoSetor } from "./tipo-setor";

export class SetorComFuncionarios {
    constructor(
        public setor: SetorComDependencias,
        public funcionarios: Array<Funcionario>
    ) {}
}