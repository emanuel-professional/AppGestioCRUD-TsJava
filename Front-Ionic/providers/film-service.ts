import { Injectable } from '@angular/core';
import { Headers, Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import {Film} from '../shared/film';

@Injectable()
export class FilmService {

    private urlFilmService = 'http://provenapps.cat:8080/actorfilmservice/services/film';
    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
     console.error('An error occurred s', error);
     return Promise.reject(error.message || error);
    }

    getAllFilms() : Promise<Film[]> {
    const url = `${this.urlFilmService}/find_all`;
    return this.http.get(url)
           .toPromise()
           .then(response => response.json() as Film[])
           .catch(this.handleError);
   }

    delete(film : Film):Promise<void>{
      const url = `${this.urlFilmService}/remove?title=${film.title}`;
      return this.http.get(url)
       .toPromise()
       .then(() => null)
       .catch(this.handleError);
    }

    insert(film: Film): Promise<void> {
    const url = `${this.urlFilmService}/insert?title=${film.title}&director=${film.director}&descripcio=${film.description}&any=${film.year}`;
    return this.http.get(url)
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
  }

  update(film : Film, titleOld : string): Promise<void>{
    const url = `${this.urlFilmService}/update?oldTitle=${titleOld}&title=${film.title}&director=${film.director}&descripcio=${film.description}&any=${film.year}`;
    return this.http.get(url)
     .toPromise()
     .then(() => null)
     .catch(this.handleError);
  }

}
