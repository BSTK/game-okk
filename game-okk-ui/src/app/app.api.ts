import {environment} from '../environments/environment';

export class Api {

  private static readonly API_V1 = '/api/v1';
  private static readonly API_LIDERES = '/lideres';
  private static readonly API_DESAFIOS = '/desafios';
  private static readonly API_USUARIOS = '/usuarios/';
  private static readonly API_TENTATIVAS = '/tentativas';

  static readonly URLS = Object.freeze({
    desafios: {
      aleatorio: Api.urlBackend(Api.API_DESAFIOS)
    },

    tentativas: {
      verificarResposta: Api.urlBackend(Api.API_TENTATIVAS),
      tentativasPorUsuario: Api.urlBackend(Api.API_TENTATIVAS),
    },

    usuarios: {
      usuariosPoIds: Api.urlBackend(Api.API_USUARIOS)
    },

    lideres: {
      lideres: Api.urlBackend(Api.API_LIDERES)
    }
  });

  private static urlBackend(path: string | string[]) {
    return environment.baseUrl
      .concat(Api.API_V1)
      .concat(...path);
  }

}
