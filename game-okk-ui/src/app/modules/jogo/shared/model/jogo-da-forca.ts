export interface Partida {
  alfabeto: string[];
  palavraSecreta: PalavraSecreta;
  letrasCorretas: string[];
  letrasIncorretas: string[];
}

export interface PalavraSecreta {
  palavra: string[];
  dicas: Dica[]
}

export interface Dica {
  numero: number;
  descricao: string;
}
