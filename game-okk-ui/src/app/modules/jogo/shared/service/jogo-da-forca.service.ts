import {Observable} from 'rxjs';
import {Api} from '../../../../app.api';
import {Injectable} from '@angular/core';
import {Partida} from '../model/jogo-da-forca';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JogoDaForcaService {

  constructor(private readonly httpClient: HttpClient) { }

  public partida(): Observable<Partida> {
    return this
      .httpClient
      .get<Partida>(Api.URLS.jogoDaForca.partida);
  }
}
