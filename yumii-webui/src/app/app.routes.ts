import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'posts' },
  {
    path: 'posts',
    loadComponent: () => import('./features/posts/posts-page').then((m) => m.PostsPage),
  },
  { path: '**', redirectTo: 'posts' },
];
