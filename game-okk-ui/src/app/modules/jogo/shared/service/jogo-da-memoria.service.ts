import {Observable} from 'rxjs';
import {Api} from '../../../../app.api';
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Nivel, NovaPartida, Partida} from '../model/jogo-da-memoria';

@Injectable({
  providedIn: 'root'
})
export class JogoDaMemoriaService {

  constructor(private readonly httpClient: HttpClient) { }

  public novaPartida(novaPartida: NovaPartida): Observable<Partida> {
    return this
      .httpClient
      .post<Partida>(Api.URLS.jogoDaMemoria.novaPartida, novaPartida);
  }

  public niveis(): Observable<Nivel[]> {
    return this
      .httpClient
      .get<Nivel[]>(Api.URLS.jogoDaMemoria.niveis);
  }

  public partida(partidaId: string): Observable<Partida> {
    const url = `${Api.URLS.jogoDaForca.partida}/${partidaId}`;
    return this
      .httpClient
      .get<Partida>(url);
  }

  public jogar(partidaId: string, letra: string): Observable<Partida> {
    const url = `${Api.URLS.jogoDaForca.jogar}/${partidaId}/${letra}`;
    return this
      .httpClient
      .post<Partida>(url, {});
  }
}
