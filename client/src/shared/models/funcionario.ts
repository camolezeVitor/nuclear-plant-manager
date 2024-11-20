import { Setor } from "./setor";

export class Funcionario {
    constructor (
        public id: number,
        public nome: string,
        public salario: number,
        public cargo: string,
        public periodoInicial: number,
        public periodoFinal: number,
        public setor: Setor
    ) {}
}