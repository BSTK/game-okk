import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../service/home.service';
import {Jogo} from './jogo';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public jogos: Jogo[] = [];

  constructor(private readonly homeService: HomeService, private readonly router: Router) { }

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
    console.log('contexto = ', contexto);

    /// TODO: CHAMA SERVIÇO DO JOGO PARA CRIAR UMA NOVA PARTIDA
    /// TODO: this.router.navigate(['contexto/ID_DA_PARTIDA']);

    /// TODO: MATEMATICA PLAY
    /// this.router.navigateByUrl('/matematica-play/1234');

    /// TODO: JOGO DA VELHA
    /// this.router.navigateByUrl('/jogo-da-velha/1234');

    /// TODO: JOGO DA FORCA
    this.router.navigateByUrl('/jogo-da-forca/1234');
  }

}
