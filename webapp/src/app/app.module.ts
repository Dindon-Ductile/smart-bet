import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {HomeComponent} from './views/home/home.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {AppComponent} from './app.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {LeaguesComponent} from './views/leagues/leagues.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { NewBetComponent } from './views/new-bet/new-bet.component';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {NumericDirective} from './services/decimal';
import { BetGridsComponent } from './views/bet-grids/bet-grids.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatChipsModule} from '@angular/material/chips';
import { DialogComponent } from './views/dialog/dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { MyBetsComponent } from './views/my-bets/my-bets.component';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { CombinedBetComponent } from './views/combined-bet/combined-bet.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LeaguesComponent,
    NewBetComponent,
    NumericDirective,
    BetGridsComponent,
    DialogComponent,
    MyBetsComponent,
    CombinedBetComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatIconModule,
        MatCardModule,
        MatButtonModule,
        MatProgressSpinnerModule,
        MatSidenavModule,
        MatListModule,
        DragDropModule,
        MatFormFieldModule,
        MatSelectModule,
        MatInputModule,
        ReactiveFormsModule,
        MatCheckboxModule,
        FormsModule,
        MatPaginatorModule,
        MatTableModule,
        MatChipsModule,
        MatDialogModule,
        MatSlideToggleModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
