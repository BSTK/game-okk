import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-jogo-da-forca',
  templateUrl: './jogo-da-forca.component.html',
  styleUrls: ['./jogo-da-forca.component.scss']
})
export class JogoDaForcaComponent implements OnInit {

  private contador = 0;
  public assetForca: string = '/assets/jogo-da-forca/jf-asset-0.png'

  constructor() { }

  ngOnInit(): void { }

  ok() {
    this.assetForca = `/assets/jogo-da-forca/jf-asset-${this.contador}.png`
    this.contador++;

    if (this.contador == 7) {
      this.contador = 0;
    }
  }
}
