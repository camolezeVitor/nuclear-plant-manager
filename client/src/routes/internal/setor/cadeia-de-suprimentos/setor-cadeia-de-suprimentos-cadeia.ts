import { TreeNode } from "primeng/api";

export const CADEIA_DE_SUPRIMENTOS: Array<TreeNode> = [{
    label: "Reator",
    data: {
        tipo: "SETOR",
        descricao: "Unidade central de produção de energia.",
        localizacao: "Planta Principal - Zona Norte",
        requisitos: [
            "1.000 L",
            "200 UNU",
            "100.000 PG"
        ],
        producao: "1.000.000 KWH"
    },
    expanded: true,
    children: [
        {
            label: "Fornecedor Urânio",
            data: {
                tipo: "FORNECEDOR",
                descricao: "Empresa responsável pelo fornecimento de urânio enriquecido.",
                localizacao: "Cidade A - Distrito de Mineração",
                contrato: {
                    valor: "R$ 200.000 por lote",
                    condicoesEntrega: "FOB (Free On Board)",
                    prazoEntrega: "Mensal"
                },
                producao: "400 UNU"
            },
            expanded: true
        },
        {
            label: "Gerador de Água",
            data: {
                tipo: "SETOR",
                descricao: "Sistema de resfriamento do reator.",
                localizacao: "Setor Leste - Planta Principal",
                requisitos: ["200 MHZ"],
                trabalhadores: {
                    alocados: 2,
                    maximo: 6
                },
                producao: "600 L"
            },
            expanded: true,
            children: [
                {
                    label: "Fornecedor de Bomba Hidráulica",
                    data: {
                        tipo: "FORNECEDOR",
                        descricao: "Fornecedor de bombas e equipamentos hidráulicos.",
                        localizacao: "Zona Industrial - Cidade C",
                        contrato: {
                            valor: "R$ 15.000 por unidade",
                            prazoEntrega: "Semestral",
                            condicoesEntrega: "Entrega no local"
                        },
                        producao: "1 U"
                    }
                },
                {
                    label: "Gerador Aux",
                    data: {
                        tipo: "SETOR",
                        descricao: "Sistema auxiliar para suporte ao gerador principal.",
                        localizacao: "Setor Leste - Planta Principal",
                        requisitos: ["100 LG"],
                        trabalhadores: {
                            alocados: 2,
                            maximo: 2
                        },
                        producao: "200 MHZ"
                    },
                    expanded: true,
                    children: [
                        {
                            label: "Fornecedor Gasolina",
                            data: {
                                tipo: "FORNECEDOR",
                                descricao: "Fornecedor local de combustíveis fósseis.",
                                localizacao: "Posto Cidade B",
                                contrato: {
                                    valor: "R$ 250 por tambor de 50 litros",
                                    condicoesEntrega: "Retirada no local",
                                    prazoEntrega: "Sob demanda"
                                },
                                producao: "500 L"
                            }
                        }
                    ]
                }
            ]
        },
        {
            label: "Chaminés",
            data: {
                tipo: "SETOR",
                descricao: "Sistema de exaustão e filtragem de gases.",
                localizacao: "Zona Sul - Planta Principal",
                trabalhadores: {
                    alocados: 4,
                    maximo: 6
                },
                manutencao: {
                    periodicidade: "Trimestral",
                    custoEstimado: "R$ 25.000",
                    ultimaManutencao: "2024-10-01"
                },
                producao: "100.000 PG"
            },
            expanded: true
        },
        {
            label: "Central de Transporte",
            data: {
                tipo: "SETOR",
                descricao: "Responsável pela logística e transporte interno de materiais.",
                localizacao: "Setor Central - Planta Principal",
                requisitos: ["3 caminhões internos operacionais"],
                trabalhadores: {
                    alocados: 10,
                    maximo: 15
                },
                manutencao: {
                    periodicidade: "Mensal",
                    custoEstimado: "R$ 5.000 por veículo"
                }
            },
            expanded: true,
            children: [
                {
                    label: "Fornecedor de Veículos Pesados",
                    data: {
                        tipo: "FORNECEDOR",
                        descricao: "Fornecedor de caminhões e equipamentos de transporte.",
                        localizacao: "Zona Industrial - Cidade D",
                        contrato: {
                            valor: "R$ 150.000 por veículo",
                            prazoEntrega: "Anual",
                            condicoesEntrega: "Entrega FOB"
                        }
                    }
                }
            ]
        }
    ]
}];
