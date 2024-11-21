import { Fornecedor } from "./fornecedor";

export class FornecedoresVaretas {
    constructor(
        public fornecedorAtualVaretasDeCombustivel: Fornecedor,
        public fornecedoresVaretasDeCombustivel: Array<Fornecedor>,
        public fornecedorAtualVaretasDeControle: Fornecedor,
        public fornecedoresVaretasDeControle: Array<Fornecedor>,
        public fornecedorAtualVaretasMediadores: Fornecedor,
        public fornecedoresVaretasMediadores: Array<Fornecedor>
    ) {}
}