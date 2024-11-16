import { FormGroup, FormControl, Validators } from "@angular/forms";

export const ADICIONAR_SETOR_FORM = () => (
    new FormGroup({
        codigoSetor: new FormControl('', [
          Validators.required,
          Validators.pattern('^[A-Z]{2}\\d{2}$')
        ]),
        nome: new FormControl('', [
          Validators.required,
          Validators.maxLength(100)
        ]),
        qtdItmProdMax: new FormControl('', [
          Validators.required,
          Validators.min(1)
        ]),
        maxFuncionarios: new FormControl('', [
          Validators.required,
          Validators.min(1)
        ]),
        medidaDeProd: new FormControl('', [
          Validators.required,
          Validators.maxLength(50)
        ]),
        desativado: new FormControl(false),
        tipoSetorId: new FormControl('', Validators.required)
    })
)