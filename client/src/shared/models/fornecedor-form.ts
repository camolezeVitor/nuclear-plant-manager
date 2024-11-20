export class FornecedorForm {
    constructor(
        public nome: string,
        public preco: string,
        public quantidadeItensFornecedor: number,
        public idMaterial: number,
        public idMedida: number
    ) {}
}