import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CombinedBet, UserCombinedBet} from '../model/bet';
import {UserBetGrids} from '../model/grid';

@Injectable({
  providedIn: 'root'
})
export class BetsRepository {

  constructor(private http: HttpClient) {
  }

  createUserBet(bet: CombinedBet): Observable<UserCombinedBet> {
    return this.http.post<UserCombinedBet>('/api/user/bets', bet);
  }

  getBetGrids(betId: string): Observable<UserBetGrids> {
    return this.http.get<UserBetGrids>(`/api/user/bets/${betId}/grids`);
  }
}
