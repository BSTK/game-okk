import {environment} from '../environments/environment';

export class Api {

  private static readonly API_V1 = '/game-okk-plataforma/api/v1';
  private static readonly API_JOGOS = '/jogos';

  static readonly URLS = Object.freeze({
    plataforma: {
      jogos: Api.urlBackend(Api.API_JOGOS)
    },
    matematicaPlay: {
      desafios: {
        aleatorio: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/desafios',
        verificarResposta: 'http://localhost:8081/game-okk-matematica-play/api/v1/matematica/verificar-resposta'
      }
    }
  });

  private static urlBackend(path: string | string[]) {
    return environment.baseUrl
      .concat(Api.API_V1)
      .concat(...path);
  }
}
