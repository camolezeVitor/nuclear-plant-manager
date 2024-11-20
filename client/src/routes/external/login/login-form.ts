import { FormControl, FormGroup, Validators } from "@angular/forms";

export const LOGIN_FORM = new FormGroup({
    cadastro: new FormControl<string>("",[
        Validators.required
    ]),
    senha: new FormControl<string>("",[
        Validators.required,
    ]),
})