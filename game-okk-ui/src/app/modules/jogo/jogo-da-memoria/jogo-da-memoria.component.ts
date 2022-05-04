import {Component, OnInit} from '@angular/core';
import {Nivel, NovaPartida, Partida} from '../shared/model/jogo-da-memoria';
import {JogoDaMemoriaService} from '../shared/service/jogo-da-memoria.service';

@Component({
  selector: 'app-jogo-da-memoria',
  templateUrl: './jogo-da-memoria.component.html',
  styleUrls: ['./jogo-da-memoria.component.scss']
})
export class JogoDaMemoriaComponent implements OnInit {

  public niveis: Nivel[] = [];
  public nivelSelecionado: string = '';
  public partida: Partida = {} as Partida;

  constructor(private readonly jogoDaMemoriaService: JogoDaMemoriaService) { }

  ngOnInit(): void {
    this.jogoDaMemoriaService
      .niveis()
      .subscribe((niveis: Nivel[]) => {
        if (niveis) {
          this.niveis = niveis;
        }
      });
  }

  iniciar(): void {
    if (this.nivelSelecionado) {
      const novaPartida: NovaPartida = { nivel: this.nivelSelecionado };
      this.jogoDaMemoriaService
        .novaPartida(novaPartida)
        .subscribe((partida: Partida) => {
          if (partida) {
            this.partida = partida;
          }
        });
    }
  }
}
