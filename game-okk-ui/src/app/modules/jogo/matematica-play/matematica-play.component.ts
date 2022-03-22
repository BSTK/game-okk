import {Component, OnInit} from '@angular/core';
import {Desafio, DesafioTentativaResposta} from '../shared/model/matematica-play.model';
import {MatematicaPlayService} from '../shared/service/matematica-play.service';

@Component({
  selector: 'app-matematica-play',
  templateUrl: './matematica-play.component.html',
  styleUrls: ['./matematica-play.component.scss']
})
export class MatematicaPlayComponent implements OnInit {

  public desafio!: Desafio;
  public desafioTentativas: DesafioTentativaResposta[] = [];

  public classAcertoErro: string = '';
  public menssagemAcertoErro: string = '';

  constructor(private readonly matematicaPlayService: MatematicaPlayService) { }

  ngOnInit(): void {
    this.novoDesafio();
  }

  jogar(resposta: number) {
    const tentativa = {
      fatorA: this.desafio.fatorA,
      fatorB: this.desafio.fatorB,
      operacao: this.desafio.operacao,
      resposta: resposta
    };

    this.matematicaPlayService
      .verificarResposta(tentativa)
      .subscribe((resposta: DesafioTentativaResposta) => {
        if (resposta && resposta.correta) {
          this.acertouResposta();
        } else {
          this.errouResposta();
        }

        setTimeout(() => {
          this.resetar();
          this.novoDesafio();
        }, 1000);
      });
  }

  private novoDesafio() {
    this.matematicaPlayService
      .desafioAleatorio()
      .subscribe((desafio: Desafio) => {
        if (desafio) {
          this.desafio = desafio;
        }
      });
  }

  private acertouResposta() {
    this.classAcertoErro = 'acertou';
    this.menssagemAcertoErro = 'Acertou !!';
  }

  private errouResposta() {
    this.classAcertoErro = 'errou';
    this.menssagemAcertoErro = 'Errou !!';
  }

  private resetar() {
    this.classAcertoErro = '';
    this.menssagemAcertoErro = '';
  }
}
