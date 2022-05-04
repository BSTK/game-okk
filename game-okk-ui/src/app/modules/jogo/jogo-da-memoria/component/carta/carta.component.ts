import {Component, Input, OnInit} from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import {Carta} from '../../../shared/model/jogo-da-memoria';

@Component({
  selector: 'app-carta',
  templateUrl: './carta.component.html',
  styleUrls: ['./carta.component.scss'],
  animations: [
    trigger('flipState', [
      state('active', style({
        transform: 'rotateY(179deg)'
      })),
      state('inactive', style({
        transform: 'rotateY(0)'
      })),
      transition('active => inactive', animate('500ms ease-out')),
      transition('inactive => active', animate('500ms ease-in'))
    ])
  ]
})
export class CartaComponent implements OnInit {

  @Input("carta") public carta: Carta = {} as Carta;

  constructor() { }

  ngOnInit() { }

  flip: string = 'inactive';

  toggleFlip() {
    this.flip = (this.flip == 'inactive') ? 'active' : 'inactive';
  }
}
