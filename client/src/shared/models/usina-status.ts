export class UsinaStatus {
    constructor(
        public dinheiroUsina: number,
        public energiaProd: string,
        public horario: number,
        public dia: number,
        public console: string,
        public temperaturaReator: string,
        public statusEnergiaProd: string
    ) {}
}