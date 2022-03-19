import {Routes} from '@angular/router';
import {MatematicaPlayComponent} from './matematica-play/matematica-play.component';

export const JOGO_ROUTES: Routes = [
  {
    path: 'matematica-play/:partidaId',
    component: MatematicaPlayComponent
  }
];
