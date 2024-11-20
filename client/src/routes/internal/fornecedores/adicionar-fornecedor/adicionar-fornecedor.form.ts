import { FormControl, FormGroup } from "@angular/forms";

export const FORMULARIO_FORNECEDOR = new FormGroup({
    nome: new FormControl<string>(""),
    preco: new FormControl<string>(""),
    quantidadeItensFornecedor: new FormControl<number | null>(null),
    idMaterial: new FormControl<number | null>(null),
    idMedida: new FormControl<number | null>(null),
})