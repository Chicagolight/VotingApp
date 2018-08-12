import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Game} from './game';
import {MessageService} from './message.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Http, Headers, Response} from '@angular/http';
import {Router} from '@angular/router';
import {catchError, map, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gamesUrl = 'api/games';  // URL to web api
  private voteUrl = 'api/vote';

  constructor(private router: Router, private http: HttpClient, private messageService: MessageService) {}

  /**
   * Get games from the server
   */
  getGames(): Observable<Game[]> {
    return this.http.get<Game[]>(this.gamesUrl).pipe(
      tap(games => this.log('Retrieved poll data')),
      catchError(this.handleError('getGames', [])));
  }


  /**
   * Update votes on the server
   */
  updateGame(game: Game): Observable<any> {
    this.log('Note: Votes are processed once per user per 24 hour period.');
    this.router.navigate(['/results']);
    const params = new HttpParams().set('voteChoice', game.id.toString());
    return this.http.get(this.voteUrl, {params}).pipe(
      tap(_ => this.log(`Updated votes`)),
      catchError(this.handleError<any>('updateGame'))
    );
  }

  /**
  * Handle Http operation that failed.
  * Let the app continue.
  */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a message with the MessageService */
  private log(message: string) {
    this.messageService.add(`${message}`);
  }
}
