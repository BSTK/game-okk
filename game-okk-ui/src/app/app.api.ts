export class Api {

  static readonly URLS = Object.freeze({
    plataforma: {
      jogos: 'http://localhost:8080/game-okk-plataforma/api/v1/jogos'
    },
    matematicaPlay: {
      desafios: {
        aleatorio: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/desafios',
        verificarResposta: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/verificar-resposta'
      }
    },
    jogoDaForca: {
      partida: 'http://localhost:8082/game-okk-jogo-forca/api/v1/forca/partida',
      novaPartida: 'http://localhost:8082/game-okk-jogo-forca/api/v1/forca/nova-partida',
      jogar: 'http://localhost:8082/game-okk-jogo-forca/api/v1/forca/jogar',
    }
  });

  /**
  private static urlBackend(path: string | string[]) {
    return environment.baseUrl
      .concat(Api.API_V1)
      .concat(...path);
  }
  **/
}
