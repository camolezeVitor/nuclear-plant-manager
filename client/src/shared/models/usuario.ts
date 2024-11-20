import { UserType } from "../enums/user-type-enum";

export class Usuario {
    constructor(
        public nomeDoUsuario: string,
        public cadastro: string,
        public senha: string,
        public role: UserType
    ) {}
}