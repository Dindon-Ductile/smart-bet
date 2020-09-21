import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './views/home/home.component';
import {LeaguesComponent} from './views/leagues/leagues.component';
import {NewBetComponent} from './views/new-bet/new-bet.component';
import {BetGridsComponent} from './views/bet-grids/bet-grids.component';
import {MyBetsComponent} from './views/my-bets/my-bets.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent, pathMatch: 'full'},
  {path: 'leagues', component: LeaguesComponent, pathMatch: 'full'},
  {path: 'new-bet', component: NewBetComponent, pathMatch: 'full'},
  {path: 'bets', component: MyBetsComponent, pathMatch: 'full'},
  {path: 'bets/:id', component: NewBetComponent, pathMatch: 'full'},
  {path: 'bets/:id/grids', component: BetGridsComponent, pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
