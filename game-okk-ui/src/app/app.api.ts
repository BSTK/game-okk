export class Api {

  /// private static readonly API_V1_MATEMATICA = '/game-okk-plataforma/api/v1';
  /// private static readonly API_V1_JOGO_FORCA = '/game-okk-jogo-forca/api/v1';

  static readonly URLS = Object.freeze({
    plataforma: {
      jogos: ''
    },
    matematicaPlay: {
      desafios: {
        aleatorio: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/desafios',
        verificarResposta: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/verificar-resposta'
      }
    },
    jogoDaForca: {
      partida: 'http://localhost:8082/game-okk-jogo-forca/api/v1/forca/partida'
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
