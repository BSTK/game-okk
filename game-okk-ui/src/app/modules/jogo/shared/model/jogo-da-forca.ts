export type Partida = {
  uuid: string;
  alfabeto: string[];
  palavraSecreta: PalavraSecreta;
  letrasCorretas: string[];
  letrasIncorretas: string[];
  totalErros: number;
}

export type PalavraSecreta = {
  palavra: string[];
  dicas: Dica[]
}

export type Dica = {
  numero: number;
  descricao: string;
}
