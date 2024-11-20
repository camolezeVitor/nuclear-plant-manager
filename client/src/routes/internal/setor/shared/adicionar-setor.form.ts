import { FormGroup, FormControl, Validators } from "@angular/forms";
import { Setor } from "../../../../shared/models/setor";

export const ADICIONAR_SETOR_FORM = (setor?: Setor) => (
    new FormGroup({
        codigoSetor: new FormControl(setor?.codigoSetor || '', [
          Validators.required,
          Validators.pattern('^[A-Z]{2}\\d{2}$')
        ]),
        nome: new FormControl(setor?.nome || '', [
          Validators.required,
          Validators.maxLength(100)
        ]),
        quantidadeItensProduzidos: new FormControl(setor?.quantidadeItensProduzidos || '', [
          Validators.required,
          Validators.min(1)
        ]),
        maximoFuncionarios: new FormControl(setor?.maximoFuncionarios || '', [
          Validators.required,
          Validators.min(1)
        ]),
        medida: new FormControl(setor?.medida || null, [
          Validators.required,
          Validators.maxLength(50)
        ]),
        tipoSetor: new FormControl(setor?.tipoSetor || null, Validators.required),
        material: new FormControl(setor?.material || null, Validators.required),
    })
)