import {Component, OnInit} from '@angular/core';
import {BetsRepository} from '../../services/bet-repository.service';
import {SimpleBet, UserCombinedBet} from '../../model/bet';
import {Fixture} from '../../model/fixture';

@Component({
  selector: 'app-my-bets',
  templateUrl: './my-bets.component.html',
  styleUrls: ['./my-bets.component.scss']
})
export class MyBetsComponent implements OnInit {
  userBets: UserCombinedBet[];

  constructor(private betRepository: BetsRepository) {
  }

  ngOnInit(): void {
    this.betRepository.getBets().subscribe(userBets => {
      this.userBets = userBets;
    });
  }

  displayDouble(odds: number): string {
    return (Math.round(odds * 100) / 100).toFixed(2);
  }

  computeFixtureName(item: Fixture): string {
    return item.homeTeam.name + ' - ' + item.awayTeam.name;
  }

  displayTimeStamp(timestamp: number): string {
    const date = new Date(timestamp * 1000);
    const ye = new Intl.DateTimeFormat('fr', {year: 'numeric'}).format(date);
    const mo = new Intl.DateTimeFormat('fr', {month: 'short'}).format(date);
    const da = new Intl.DateTimeFormat('fr', {day: '2-digit'}).format(date);
    return `${da} ${mo} ${ye}`;
  }

  firstFixture(entries: SimpleBet[]): SimpleBet[] {
    return entries.slice(0, 1);
  }

  deleteBet(id: string): any {
    this.betRepository.delete(id).subscribe(response => {
      const index = this.userBets.findIndex(bet => bet.id = id);
      this.userBets.splice(index, 1);
    });
  }
}
