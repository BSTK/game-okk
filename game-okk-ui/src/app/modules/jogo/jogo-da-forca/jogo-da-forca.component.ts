import {Component, OnInit} from '@angular/core';
import {Partida} from '../shared/model/jogo-da-forca';
import {JogoDaForcaService} from '../shared/service/jogo-da-forca.service';

@Component({
  selector: 'app-jogo-da-forca',
  templateUrl: './jogo-da-forca.component.html',
  styleUrls: ['./jogo-da-forca.component.scss']
})
export class JogoDaForcaComponent implements OnInit {

  private contador = 0;
  public assetForca: string = '/assets/jogo-da-forca/jf-asset-0.png'
  public partida: Partida = {} as Partida;

  constructor(private readonly jogoDaForcaService: JogoDaForcaService) { }

  ngOnInit(): void {
    this.jogoDaForcaService
      .partida()
      .subscribe((partida: Partida) => {
        this.partida = partida;
      });
  }

  ok() {
    this.assetForca = `/assets/jogo-da-forca/jf-asset-${this.contador}.png`
    this.contador++;

    if (this.contador == 7) {
      this.contador = 0;
    }
  }

  jogar(letra: string) {
    console.log('Tem a letra?: ', letra);
  }
}
