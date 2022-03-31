import {Jogo} from './jogo';
import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../service/home.service';
import {Partida} from '../../../jogo/shared/model/jogo-da-forca';
import {JogoDaForcaService} from '../../../jogo/shared/service/jogo-da-forca.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  private readonly JOGO_DA_FORCA = 'jogo-da-forca';

  public jogos: Jogo[] = [];

  constructor(private readonly router: Router,
              private readonly homeService: HomeService,
              private readonly jogoDaForcaService: JogoDaForcaService) { }

  ngOnInit(): void {
    this.homeService
      .jogos()
      .subscribe((value: Jogo[]) => {
        if (value) {
          this.jogos = value;
        }
      });
  }

  public jogarPartida(contexto: string) {
    if (contexto === this.JOGO_DA_FORCA) {
      this.jogoDaForcaService
        .novaPartida()
        .subscribe((partida: Partida) => {
          this.router.navigateByUrl(`/jogo-da-forca/${partida.uuid}`);
        });
    }
  }
}
