import {Component, OnInit} from '@angular/core';
import {Partida} from '../shared/model/jogo-da-forca';
import {JogoDaForcaService} from '../shared/service/jogo-da-forca.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-jogo-da-forca',
  templateUrl: './jogo-da-forca.component.html',
  styleUrls: ['./jogo-da-forca.component.scss']
})
export class JogoDaForcaComponent implements OnInit {

  public partida: Partida = {} as Partida;
  public assetForca: string = '/assets/jogo-da-forca/jf-asset-0.png'

  constructor(private readonly activatedRoute: ActivatedRoute,
              private readonly jogoDaForcaService: JogoDaForcaService) { }

  ngOnInit(): void {
    const partidaId = this.activatedRoute.snapshot.paramMap.get('partidaId');

    console.log('partidaId = ', partidaId);

    if (partidaId) {
      this.jogoDaForcaService
        .partida(partidaId)
        .subscribe((partida: Partida) => {
          this.partida = partida;
        });
    }

    /// TODO: CASO A PARTIDA NÃO SEJA ENCONTRADA! VOLTAR PARA TELA ANTERIOR!!
  }

  jogar(letra: string) {
    this
      .jogoDaForcaService
      .jogar('1', letra)
      .subscribe((partida: Partida) => {
        this.partida = partida;
      });
  }
}
