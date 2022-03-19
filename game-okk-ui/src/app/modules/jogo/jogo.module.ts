import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {CommonModule} from '@angular/common';
import {JOGO_ROUTES} from './jogo.module.routes';
import {MatematicaPlayComponent} from './matematica-play/matematica-play.component';

@NgModule({
  declarations: [
    MatematicaPlayComponent
  ],
  imports: [
    FormsModule,
    CommonModule,
    RouterModule.forChild(JOGO_ROUTES)
  ]
})
export class JogoModule { }
