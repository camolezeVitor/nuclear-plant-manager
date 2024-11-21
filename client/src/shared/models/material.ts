export class TipoMaterial {
    constructor(
        public id: number,
        public codigo: string,
        public descricao: string
    ) {}
}

export class Material {
    constructor(
        public id: number,
        public nome: string,
        public multiplicador: number,
        public tipoMaterial: TipoMaterial
    ) {}
}