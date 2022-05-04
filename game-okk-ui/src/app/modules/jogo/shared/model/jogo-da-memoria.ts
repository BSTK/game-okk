export type Nivel = {
  nivel: string;
  descricao: string;
  quantidadePares: number;
};

export type Partida = {
  uuid: string;
  dataInicio: Date;
  dataFim: Date;
  status: string;
  tabuleiro: Tabuleiro
};

export type NovaPartida = {
  nivel: string;
};

export type Tabuleiro = {
  nivel: Nivel;
  cartas: Carta[];
};

export type Carta = {
  numero: number;
  status: string;
};
