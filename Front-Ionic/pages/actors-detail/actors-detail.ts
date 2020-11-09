import { Component } from '@angular/core';
import {  NavController, NavParams } from 'ionic-angular';
import { ActorsPage } from '../actors/actors';
import { Actor } from '../../shared/actor';
import { AlertController } from 'ionic-angular';
import { ActorService } from '../../providers/actor-service';
/**
 * Generated class for the FilmsDetailPage page.
 *
 * See http://ionicframework.com/docs/components/#navigation for more info
 * on Ionic pages and navigation.
 */
@Component({
  selector: 'page-actors-detail',
  templateUrl: 'actors-detail.html',
})
export class ActorsDetailPage {

  private actor: Actor;
  private nameOld : string;
  private error: string;
  private nameActor : string;
  private yearActor : string;
  private nActor : Actor;

  constructor(public navCtrl: NavController, public navParams: NavParams,private actorService: ActorService,public alertCtrl: AlertController) {
    this.actor = navParams.get('paramActor');
    this.nameOld = navParams.get('nameOld');

  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ActorsDetailPage');
  }

  goBack(): void {
      this.navCtrl.push(ActorsPage);
    }

    save(): void {
      if(this.nameActor == null || this.yearActor == null) this.showError();
      else{

        this.nActor = new Actor(this.nameActor,this.yearActor);
        this.actorService.insert(this.nActor)
          .then(() => {this.goBack()}).catch(error => this.error = error);
          this.showAlert(this.nActor);
          this.goBack();
      }

      this.nameActor = null;
      this.yearActor = null;
    }

    update(actor : Actor): void {
      this.actorService.update(this.actor,this.nameOld)
        .then(() => {this.goBack()}).catch(error => this.error = error);
    }

    showError(){
      let alert = this.alertCtrl.create({
        title:"Error, enter data",
        subTitle: "You produce an error because you have to enter a value data",
        buttons: ['OK']
      });
      alert.present();
    }

    showAlert(actor : Actor) {
      let alert = this.alertCtrl.create({
        title:"Successfully added",
        subTitle: "You added an actor named "+actor.name,
        buttons: ['OK']
      });
      alert.present();
    }

    _keyPress(event: any) {
      const pattern = /[0-9\+\-\ ]/;
      let inputChar = String.fromCharCode(event.charCode);

      if (!pattern.test(inputChar)) {
        event.preventDefault();
      }
    }

}
