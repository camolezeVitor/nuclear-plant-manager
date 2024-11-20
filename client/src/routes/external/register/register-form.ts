import { FormControl, FormGroup } from "@angular/forms";

export const REGISTER_FORM = new FormGroup({
    nome: new FormControl<string>(""),
    cadastro: new FormControl<string>(""),
    senha: new FormControl<string>(""),
    rulesEnum: new FormControl<string>("")
})