import {Component, OnInit} from '@angular/core';
import {FixturesRepository} from '../../services/fixtures-repository.service';
import {CdkDragDrop, moveItemInArray} from '@angular/cdk/drag-drop';
import {Fixture, FixturesAtDate, UserLeaguesFixtures} from '../../model/fixture';
import {CombinedBet, SimpleBet} from '../../model/bet';
import {MatSelectChange} from '@angular/material/select';
import {SimpleLeague} from '../../model/league';
import {BetsRepository} from '../../services/bet-repository.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-new-bet',
  templateUrl: './new-bet.component.html',
  styleUrls: ['./new-bet.component.scss']
})
export class NewBetComponent implements OnInit {
  bets: SimpleBet[] = [];
  fixtures: UserLeaguesFixtures;
  fixturesByDate: FixturesAtDate;
  betMoney: number;
  selectedDate: string;
  dates: string[];
  betId: string;


  constructor(private fixturesRepository: FixturesRepository,
              private betRepository: BetsRepository,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.betId = params.id;
      if (!!this.betId) {
        this.betRepository.getBet(this.betId).subscribe(userBet => {
          this.bets = userBet.entries;
          this.betMoney = userBet.betMoney;
          this.fixturesRepository.getUserLeaguesFixtures().subscribe(fixtures => {
            this.fixtures = fixtures;
            this.fixtures.fixturesByDate = this.fixtures.fixturesByDate.map(fixtureByDate => {
                return {
                  date: fixtureByDate.date,
                  fixtures: fixtureByDate.fixtures.filter(fixture =>
                    !this.bets.some(bet =>
                      bet.fixture.fixtureId === fixture.fixtureId)
                  )
                };
              }
            );
            this.fixturesByDate = fixtures.fixturesByDate[0];
            this.selectedDate = this.fixturesByDate.date;
            this.dates = fixtures.fixturesByDate.map(f => f.date);
          });
        });
      } else {
        this.fixturesRepository.getUserLeaguesFixtures().subscribe(fixtures => {
          this.fixtures = fixtures;
          this.fixturesByDate = fixtures.fixturesByDate[0];
          this.selectedDate = this.fixturesByDate.date;
          this.dates = fixtures.fixturesByDate.map(f => f.date);
        });
      }
    });
  }

  dropBetableFixture(event: CdkDragDrop<SimpleBet[], any>): any {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      const fixture: Fixture = event.previousContainer.data[event.previousIndex];
      const bet: SimpleBet = this.buildEmptyBet(fixture);
      event.previousContainer.data.splice(event.previousIndex, 1);
      event.container.data.splice(event.currentIndex, 0, bet);
    }
  }

  dropFixture(event: CdkDragDrop<Fixture[], any>): any {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      const bet: SimpleBet = event.previousContainer.data[event.previousIndex];
      event.previousContainer.data.splice(event.previousIndex, 1);
      this.fixtures.fixturesByDate.find(fbd => fbd.date === bet.fixture.startsAtDay)
        .fixtures.splice(event.currentIndex, 0, bet.fixture);
    }
  }

  private buildEmptyBet(previousFixture: Fixture): SimpleBet {
    return {
      fixture: previousFixture,
      outcomes: [
        {
          odds: 1.00,
          outcome: 'HOME',
          selected: true
        },
        {
          odds: 1.00,
          outcome: 'NULL',
          selected: true
        },
        {
          odds: 1.00,
          outcome: 'AWAY',
          selected: true
        }
      ],
      active: true
    };
  }

  doFilter($event: MatSelectChange): any {
    const value: string = $event.value;
    this.filterFixturesByDate(value);
  }

  filterFixturesByDate(date: string): any {
    this.fixturesByDate = this.fixtures.fixturesByDate.find(fixtures => fixtures.date === date);
  }

  getLeague(item: Fixture): SimpleLeague {
    return this.fixtures.leagues.find(l => l.id === item.leagueId);
  }

  computeFixtureName(item: Fixture): string {
    return item.homeTeam.name + ' - ' + item.awayTeam.name;
  }

  computeFixtureDate(item: Fixture): string {
    return item.startsAtDay + ' à ' + item.startsAtTime;
  }

  registerBetAndGenerateGrids(): any {
    const combinedBet: CombinedBet = {
      entries: this.bets,
      betMoney: this.betMoney
    };
    if (!!this.betId) {
      this.betRepository.updateUserBet(combinedBet, this.betId).subscribe(userBet => {
        this.router.navigate(['/bets', this.betId, 'grids']);
      });
    } else {
      this.betRepository.createUserBet(combinedBet).subscribe(userBet => {
        this.router.navigate(['/bets', userBet.id, 'grids']);
      });
    }
  }
}
