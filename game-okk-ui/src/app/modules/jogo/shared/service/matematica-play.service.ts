import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {Api} from '../../../../app.api';
import {HttpClient} from '@angular/common/http';
import {Desafio, DesafioTentativa, DesafioTentativaResposta} from '../matematica-play/matematica-play.model';

@Injectable({
  providedIn: 'root'
})
export class MatematicaPlayService {

  constructor(private readonly httpClient: HttpClient) { }

  public desafioAleatorio(): Observable<Desafio> {
    return this
      .httpClient
      .get<Desafio>(Api.URLS.matematicaPlay.desafios.aleatorio);
  }

  public verificarResposta(tentativa: DesafioTentativa): Observable<DesafioTentativaResposta> {
    return this
      .httpClient
      .post<DesafioTentativaResposta>(Api.URLS.matematicaPlay.desafios.verificarResposta, tentativa);
  }
}
