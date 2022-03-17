import {Component, OnInit} from '@angular/core';
import {HomeService} from '../../service/home.service';
import {Jogo} from './jogo';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public jogos: Jogo[] = [];

  constructor(private readonly homeService: HomeService) { }

  ngOnInit(): void {
    this.homeService
      .jogos()
      .subscribe((value: Jogo[]) => {
        if (value) {
          this.jogos = value;
        }
      });
  }

}
