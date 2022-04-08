export type Partida = {
  uuid: string;
  alfabeto: string[];
  palavraSecreta: PalavraSecreta;
  letrasCorretas: string[];
  letrasIncorretas: string[];
  totalErros: number;
  terminouPartidaGanhou: boolean;
  terminouPartidaPerdeu: boolean;
}

export type PalavraSecreta = {
  palavra: string[];
  dicas: Dica[]
}

export type Dica = {
  numero: number;
  descricao: string;
}
