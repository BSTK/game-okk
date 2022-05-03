import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {JOGO_ROUTES} from './jogo.module.routes';
import {MatematicaPlayComponent} from './matematica-play/matematica-play.component';
import {JogoDaForcaComponent} from './jogo-da-forca/jogo-da-forca.component';
import {CoreModule} from '../../core/core.module';
import { JogoDaMemoriaComponent } from './jogo-da-memoria/jogo-da-memoria.component';

@NgModule({
  declarations: [
    JogoDaForcaComponent,
    MatematicaPlayComponent,
    JogoDaMemoriaComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    RouterModule.forChild(JOGO_ROUTES),
    CoreModule
  ]
})
export class JogoModule { }
