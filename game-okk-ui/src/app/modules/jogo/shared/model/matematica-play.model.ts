export interface Desafio {
  fatorA: number;
  fatorB: number;
  alternativas: number[];
  operacao: string;
}

export interface DesafioTentativaResposta {
  fatorA: number;
  fatorB: number;
  resultado: number;
  correta: boolean;
  operacao: string;
}

export interface DesafioTentativa {
  fatorA: number;
  fatorB: number;
  resposta: number;
  operacao: string;
}
