import { FormControl, FormGroup } from "@angular/forms";
import { Funcionario } from "../../../../shared/models/funcionario";

export const ADICIONAR_FUNCIONARIO_FORM = (funcionario?: Funcionario) => (new FormGroup({
    id: new FormControl(funcionario?.id || null),
    nome: new FormControl(funcionario?.nome || null),
    salario: new FormControl(funcionario?.salario || null),
    cargo: new FormControl(funcionario?.cargo || null),
    periodoInicial: new FormControl(funcionario?.periodoInicial || null),
    periodoFinal: new FormControl(funcionario?.periodoFinal || null),
    codigoSetor: new FormControl(funcionario?.setor.codigoSetor || null)
}))