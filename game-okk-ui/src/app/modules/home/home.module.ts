import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {HOME_ROUTES} from './home.module.routes';
import {HomeComponent} from './pages/home/home.component';
import {CoreModule} from '../../core/core.module';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [HomeComponent],
  imports: [
    CoreModule,
    RouterModule.forChild(HOME_ROUTES),
    CommonModule
  ]
})
export class HomeModule { }
