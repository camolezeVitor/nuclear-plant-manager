export class Setor {
    constructor(
        public id: number,
        public codigoSetor: string,
        public nome: string,
        public quantidadeItensProduzidos: number,
        public maximoFuncionarios: number,
        public idMedida: number,
        public idMaterial: number
    ) {}
}