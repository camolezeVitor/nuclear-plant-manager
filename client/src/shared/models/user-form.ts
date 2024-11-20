import { UserType } from "../enums/user-type-enum";

export class UserForm {
    constructor(
        public nome: string,
        public cadastro: string,
        public senha: string,
        public rulesEnum: UserType
    ) {}
}