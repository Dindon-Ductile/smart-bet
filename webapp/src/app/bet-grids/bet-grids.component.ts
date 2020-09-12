import {Component, OnInit} from '@angular/core';
import {BetsRepository} from '../services/bet-repository.service';
import {ActivatedRoute} from '@angular/router';
import {AdvancedBetGrid, BetGridEntry, UserBetGrids} from '../model/grid';
import {Fixture} from '../model/fixture';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {SimpleBet} from '../model/bet';
import {PageEvent} from '@angular/material/paginator';
import {DialogComponent} from "../dialog/dialog.component";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";

@Component({
  selector: 'app-bet-grids',
  templateUrl: './bet-grids.component.html',
  styleUrls: ['./bet-grids.component.scss']
})
export class BetGridsComponent implements OnInit {
  grids: UserBetGrids;
  betId: string;
  pageIndex = 0;
  currentGrid: AdvancedBetGrid;
  displayedColumns: string[] = ['fixture', 'outcome', 'odds'];

  constructor(private betsRepository: BetsRepository,
              private route: ActivatedRoute,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const betId = params.id;
      this.betId = betId;
      this.betsRepository.getBetGrids(betId).subscribe(grids => {
        this.grids = grids;
        this.currentGrid = this.grids.combinedBetResult.playableGrids[this.pageIndex];
      });
    });
  }

  computeFixtureName(item: Fixture): string {
    return item.homeTeam.name + ' - ' + item.awayTeam.name;
  }

  computeFixtureDate(item: Fixture): string {
    return item.startsAtDay + ' à ' + item.startsAtTime;
  }

  updateBetAndGenerateGrid(): any {
    this.betsRepository.updateUserBet(this.grids.combinedBetResult.bet, this.betId).subscribe(updatedBet => {
      this.betsRepository.getBetGrids(updatedBet.id).subscribe(updatedGrids => {
        this.grids = updatedGrids;
        this.currentGrid = this.grids.combinedBetResult.playableGrids[this.pageIndex];
      });
    } , error => {
      const matDialogConfig = new MatDialogConfig();
      matDialogConfig.data = {
        message: 'Il y a une erreur dans ton pari: une côte à zéro, un match sans résultat ou une mise de 0',
        title: 'Impossible de modifier ton pari',
        type: 'warn'
      };
      this.dialog.open(DialogComponent, matDialogConfig);
    });
  }

  drop(event: CdkDragDrop<SimpleBet[], any>): any {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    }
  }

  switchGrid(event: PageEvent): any {
    this.currentGrid = this.grids.combinedBetResult.playableGrids[event.pageIndex];
    this.pageIndex = event.pageIndex;
  }

  displayDouble(odds: number): string {
    return (Math.round(odds * 100) / 100).toFixed(2);
  }

  findFixtureName(fixtureId: number): string {
    return this.computeFixtureName(this.grids.fixtures.find(f => f.fixtureId === fixtureId));
  }

  computeOutcomeName(outcome: string): string {
    if (outcome === 'HOME') {
      return '1';
    } else if (outcome === 'NULL') {
      return 'N';
    } else if (outcome === 'AWAY') {
      return '2';
    }
  }
}
