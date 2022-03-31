import {Routes} from '@angular/router';
import {JogoDaForcaComponent} from './jogo-da-forca/jogo-da-forca.component';
import {MatematicaPlayComponent} from './matematica-play/matematica-play.component';

export const JOGO_ROUTES: Routes = [
  {
    path: 'matematica-play/:partidaId',
    component: MatematicaPlayComponent
  },
  {
    path: 'jogo-da-forca/:partidaId',
    component: JogoDaForcaComponent
  }
];
