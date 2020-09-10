import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {League, LeagueId, Leagues} from '../model/league';

@Injectable({
  providedIn: 'root'
})
export class LeaguesRepository {

  constructor(private http: HttpClient) {
  }

  getLeagues(): Observable<Leagues> {
    return this.http.get<Leagues>('/api/leagues/available');
  }

  getUserLeagues(): Observable<League[]> {
    return this.http.get<League[]>('/api/user/leagues');
  }

  registerLeague(league: LeagueId): Observable<any> {
    return this.http.post('/api/user/leagues', league);
  }

  deleteLeague(leagueId: string): Observable<any> {
    return this.http.delete(`/api/user/leagues/${leagueId}`);
  }
}
