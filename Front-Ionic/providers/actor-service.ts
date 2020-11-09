import { Injectable } from '@angular/core';
import { Headers, Http, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Actor} from '../shared/actor';

@Injectable()
export class ActorService {

    private urlActorService = 'http://provenapps.cat:8080/actorfilmservice/services/actor';
    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
     console.error('An error occurred s', error);
     return Promise.reject(error.message || error);
   }

    getAllActors() : Promise<Actor[]> {
    const url = `${this.urlActorService}/find_all`;
    return this.http.get(url)
           .toPromise()
           .then(response => response.json() as Actor[])
           .catch(this.handleError);
  }

    delete(actor: Actor):Promise<void>{
      const url = `${this.urlActorService}/remove?name=${actor.name}`;
      return this.http.get(url)
       .toPromise()
       .then(() => null)
       .catch(this.handleError);
    }

    insert(actor: Actor): Promise<void> {
      const url = `${this.urlActorService}/insert?name=${actor.name}&year=${actor.year}`;
      return this.http.get(url)
       .toPromise()
       .then(() => null)
       .catch(this.handleError);
    }

    update(actor : Actor, nameOld : string): Promise<void>{
      const url = `${this.urlActorService}/update?oldName=${nameOld}&name=${actor.name}&year=${actor.year}`;
      return this.http.get(url)
       .toPromise()
       .then(() => null)
       .catch(this.handleError);
    }


}
