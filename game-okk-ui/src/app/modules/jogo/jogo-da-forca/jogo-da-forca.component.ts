import {Location} from '@angular/common';
import {Partida} from '../shared/model/jogo-da-forca';
import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit, ViewChild} from '@angular/core';
import {AlertComponent} from '../../../core/alert/alert.component';
import {JogoDaForcaService} from '../shared/service/jogo-da-forca.service';
import {alertGanhou, alertPerdeu, AlertTipo} from '../../../core/alert/alert-tipo';

@Component({
  selector: 'app-jogo-da-forca',
  templateUrl: './jogo-da-forca.component.html',
  styleUrls: ['./jogo-da-forca.component.scss']
})
export class JogoDaForcaComponent implements OnInit {

  public partida: Partida = {} as Partida;
  public alertTipo: AlertTipo = {} as AlertTipo;
  public assetForca: string = '/assets/jogo-da-forca/jf-asset-0.png';

  @ViewChild('alertCompent')
  public alertCompent?: AlertComponent;
  public alertCompentHidden: boolean = true;

  private readonly cssErrouJogada = 'botao-jogado botao-jogado-errou';
  private readonly cssAcertouJogada = 'botao-jogado botao-jogado-acertou';

  constructor(private readonly router: Router,
              private readonly location: Location,
              private readonly activatedRoute: ActivatedRoute,
              private readonly jogoDaForcaService: JogoDaForcaService) { }

  ngOnInit(): void {
    const partidaId = this.activatedRoute.snapshot.paramMap.get('partidaId');

    if (partidaId) {
      this.jogoDaForcaService
        .partida(partidaId)
        .subscribe((partida: Partida) => {
          this.atualizaPartida(partida);
        });
    }

    /// TODO: CASO A PARTIDA NÃO SEJA ENCONTRADA! VOLTAR PARA TELA ANTERIOR!!
  }

  jogar(letra: string) {
    if (this.partida) {
      this
        .jogoDaForcaService
        .jogar(this.partida.uuid, letra)
        .subscribe((partida: Partida) => {
          this.atualizaPartida(partida);
          this.atualizarGanhouPerdeu(partida);
        });
    }
  }

  cssJogada(letra: string): string {
    if (this.partida.letrasCorretas.includes(letra)) { return this.cssAcertouJogada; }
    if (this.partida.letrasIncorretas.includes(letra)) { return this.cssErrouJogada; }
    return '';
  }

  private atualizaPartida(partida: Partida) {
    this.partida = partida;
    this.assetForca = `/assets/jogo-da-forca/jf-asset-${partida.totalErros}.png`;
  }

  private atualizarGanhouPerdeu(partida: Partida) {
    this.alertCompentHidden = true;
    if (partida.terminouPartidaGanhou
      && this.alertCompent
      && this.alertCompent.hrefModal) {
      this.alertTipo = alertGanhou;
      this.alertCompent.hrefModal?.nativeElement.click();
      this.iniciarNovaPartida();
    }

    if (partida.terminouPartidaPerdeu
      && this.alertCompent
      && this.alertCompent.hrefModal) {
      this.alertTipo = alertPerdeu;
      this.alertCompent.hrefModal?.nativeElement.click();
      this.iniciarNovaPartida();
    }
  }

  private iniciarNovaPartida() {
    this.alertCompentHidden = false;
    this.jogoDaForcaService
      .novaPartida()
      .subscribe((partida: Partida) => {
        this.atualizaPartida(partida);
        this.location.replaceState(`/jogo-da-forca/${partida.uuid}`);
      });
  }
}
