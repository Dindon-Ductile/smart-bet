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

  updateUserBet(bet: CombinedBet, betId: string): Observable<UserCombinedBet> {
    return this.http.put<UserCombinedBet>(`/api/user/bets/${betId}`, bet);
  }

  getBet(betId: string): Observable<UserCombinedBet> {
    return this.http.get<UserCombinedBet>(`/api/user/bets/${betId}`);
  }

  getBets(): Observable<UserCombinedBet[]> {
    return this.http.get<UserCombinedBet[]>(`/api/user/bets`);
  }

  delete(betId: string): Observable<any> {
    return this.http.delete<any>(`/api/user/bets/${betId}`);
  }

  getBetGrids(betId: string): Observable<UserBetGrids> {
    return this.http.get<UserBetGrids>(`/api/user/bets/${betId}/grids`);
  }
}
