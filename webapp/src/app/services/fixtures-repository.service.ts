import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserLeaguesFixtures} from '../model/fixture';

@Injectable({
  providedIn: 'root'
})
export class FixturesRepository {

  constructor(private http: HttpClient) {
  }

  getUserLeaguesFixtures(): Observable<UserLeaguesFixtures> {
    return this.http.get<UserLeaguesFixtures>('/api/user/leagues/fixtures');
  }

  getUserFakeFixtures(): UserLeaguesFixtures {
    return  {
      fixturesByDate: [
        {
          date: 'Samedi 12 Sept.',
          fixtures: [
            {
              fixtureId: 1,
              leagueId: 1,
              startsAt: 1600606800,
              startsAtDay: 'Samedi 12 Sept.',
              startsAtTime: '17:00',
              venue: 'Stade de France',
              homeTeam: {
                id: 1,
                name: 'Perpignan',
                logoUrl: 'a',
              },
              awayTeam: {
                id: 2,
                name: 'Carcassonne',
                logoUrl: 'a',
              }
            },
            {
              fixtureId: 2,
              leagueId: 1,
              startsAt: 1600606800,
              startsAtDay: 'Samedi 12 Sept.',
              startsAtTime: '21:00',
              venue: 'Parc des Princes',
              homeTeam: {
                id: 31,
                name: 'PSG',
                logoUrl: 'a',
              },
              awayTeam: {
                id: 43,
                name: 'Marseille',
                logoUrl: 'a',
              }
            },
          ]
        },
        {
          date: 'Samedi 13 Sept.',
          fixtures: [
            {
              fixtureId: 3,
              leagueId: 1,
              startsAt: 1600606800,
              startsAtDay: 'Samedi 13 Sept.',
              startsAtTime: '14:00',
              venue: 'Vélodrome',
              homeTeam: {
                id: 89,
                name: 'Nice',
                logoUrl: 'a',
              },
              awayTeam: {
                id: 90,
                name: 'Lyon',
                logoUrl: 'a',
              }
            },
          ]
        }
      ],
      leagues: [
        {
          id: 1,
          name: 'Ligue 1',
          country: 'France',
          logoUrl: 'lol',
          flagUrl: 'lil',
        }
      ]
    };
  }
}
