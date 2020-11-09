import { Component } from '@angular/core';
import { NavController, NavParams} from 'ionic-angular';
import { ActorService } from '../../providers/actor-service';
import { Actor } from '../../shared/actor';
import { AlertController } from 'ionic-angular';
import {ActorsDetailPage } from '../actors-detail/actors-detail';

/**
 * Generated class for the ActorsPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
  selector: 'page-actors',
  templateUrl: 'actors.html',
})
export class ActorsPage {

  private actors: Actor[];
  private selectedActor: Actor;
  private error: string;

  constructor(public navCtrl: NavController,public navParams : NavParams,private actorService: ActorService,public alertCtrl: AlertController) {
  }

  ngOnInit() {
    this.getActors();
  }

  private handleError(error: any): Promise<any> {
   console.error('An error occurred s', error);
   return Promise.reject(error.message || error);
  }

  getActors(): void {
    this.actorService.getAllActors().then(actors => this.actors = actors)
    .catch(error => this.error = error  );
  }

  gotoDetail(actor : Actor):void{
    if(actor != null) this.navCtrl.push(ActorsDetailPage, { paramActor: actor , nameOld : actor.name});
    else this.navCtrl.push(ActorsDetailPage);
  }

  onSelect(actor: Actor): void {
    this.selectedActor = actor;
  }


  delete(actor: Actor): void {
    this.actorService
        .delete(actor).then(() => {
          this.actors = this.actors.filter(e => e !== actor);
          if (this.selectedActor === actor) { this.selectedActor = null;}
        }).catch(error => this.error = error);
  }

  showAlert(actor: Actor) {
    let alert = this.alertCtrl.create({
      title: "Successfully deleted",
      subTitle: "You deleted this actor : "+ actor.name,
      buttons: ['OK']
    });
    alert.present();
  }

  presentConfirm(actor : Actor) {
    let alert = this.alertCtrl.create({
      title: 'Confirm delete',
      message: 'Do you want to delete this actor?',
      buttons: [
        {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
          }
        },
        {
          text: 'Confirm',
          handler: () => {
              this.delete(actor);
              this.showAlert(actor);
          }
        }
      ]
    });
    alert.present();
  }


}
