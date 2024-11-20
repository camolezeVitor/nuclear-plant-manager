import { Material } from "./material";
import { Medida } from "./medida";
import { TipoSetor } from "./tipo-setor";

export class Setor {
    constructor(
        public id: number,
        public codigoSetor: string,
        public nome: string,
        public quantidadeItensProduzidos: number,
        public maximoFuncionarios: number,
        public medida: Medida,
        public material: Material,
        public tipoSetor: TipoSetor
    ) {}
}

export class SetorComDependencias {
    constructor(
        public id: number,
        public codigoSetor: string,
        public nome: string,
        public quantidadeItensProduzidos: number,
        public maximoFuncionarios: number,
        public medida: Medida,
        public material: Material,
        public tipoSetor: TipoSetor,
        public dependencias: Array<{medida: Medida | null, material: Material | null, qtd: number}>
    ) {}
}