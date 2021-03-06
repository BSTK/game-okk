import {APP_BASE_HREF} from '@angular/common';
import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

const ROUTES: Routes = [
  {
    path: '',
    loadChildren: () => import('./modules/home/home.module')
      .then(module => module.HomeModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./modules/home/home.module')
      .then(module => module.HomeModule)
  },
  {
    path: '',
    loadChildren: () => import('./modules/jogo/jogo.module')
      .then(module => module.JogoModule)
  },
];

export const APP_ROUTING_PROVIDER: any[] = [
  {provide: APP_BASE_HREF, useValue: '/game-okk'}
];

export const ROUTING: ModuleWithProviders<any> = RouterModule.forRoot(ROUTES, {useHash: true});
