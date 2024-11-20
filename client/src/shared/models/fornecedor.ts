import { Material } from "./material";
import { Medida } from "./medida";

export class Fornecedor {
    constructor(
        public id: number,
        public nome: string,
        public preco: string,
        public quantidadeItensFornecedor: number,
        public material: Material,
        public medida: Medida
    ){}
}