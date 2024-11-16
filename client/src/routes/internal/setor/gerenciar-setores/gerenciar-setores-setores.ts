export const SETORES = [
    {
        codigo: "AD53",
        nome: 'Reator Principal',
        detalhes: {
            producao: '250 MW',
            funcionarios: { presentes: 120, maximo: 150 },
            temperatura: '300 °C'
        },
        status: 'Operacional',
        risco: 'Baixo',
        ativo: true
    },
    {
        codigo: "BC23",
        nome: 'Armazenamento de Resíduos',
        detalhes: {
            producao: 'N/A',
            funcionarios: { presentes: 40, maximo: 50 },
            temperatura: '20 °C'
        },
        status: 'Inspeção',
        risco: 'Alto',
        ativo: true
    },
    {
        codigo: "GF03",
        nome: 'Controle de Radiação',
        detalhes: {
            producao: 'Monitoramento',
            funcionarios: { presentes: 25, maximo: 30 },
            temperatura: '70 °C'
        },
        status: 'Operacional',
        risco: 'Moderado',
        ativo: true
    },
    {
        codigo: "BM43",
        nome: 'Manutenção',
        detalhes: {
            producao: 'Suporte',
            funcionarios: { presentes: 60, maximo: 75 },
            temperatura: '50 °C'
        },
        status: 'Revisão',
        risco: 'Baixo',
        ativo: false
    },
    {
        codigo: "AP13",
        nome: 'Laboratório de Pesquisa',
        detalhes: {
            producao: 'Pesquisa',
            funcionarios: { presentes: 0, maximo: 10 },
            temperatura: 'N/A'
        },
        status: 'Inativo',
        risco: 'Nenhum',
        ativo: false
    }
];