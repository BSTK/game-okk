import {Routes} from '@angular/router';
import {JogoDaForcaComponent} from './jogo-da-forca/jogo-da-forca.component';
import {JogoDaMemoriaComponent} from './jogo-da-memoria/jogo-da-memoria.component';
import {MatematicaPlayComponent} from './matematica-play/matematica-play.component';

export const JOGO_ROUTES: Routes = [
  {
    path: 'matematica-play/:partidaId',
    component: MatematicaPlayComponent
  },
  {
    path: 'jogo-da-forca/:partidaId',
    component: JogoDaForcaComponent
  },
  {
    path: 'jogo-da-memoria',
    component: JogoDaMemoriaComponent
  },
  {
    path: 'jogo-da-memoria/:partidaId',
    component: JogoDaMemoriaComponent
  }
];
