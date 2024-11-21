import { Material } from "./material";
import { Medida } from "./medida";
import { TipoSetor } from "./tipo-setor";

export class SetorInfosBasicas {
    constructor(
        public id: number,
        public nome: string,
        public quantidadeItensProduzidos: number,
        public maximoFuncionarios: number,
        public medida: Medida,
        public tipoSetor: TipoSetor,
        public material: Material
    ) {}
}