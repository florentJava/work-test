import { Injectable, OnInit } from '@angular/core';
import { Tache } from '../modele/tache.model';
import { HttpClient } from '@angular/common/http';
import { Observable, map, switchMap } from 'rxjs';
import { NetworkService } from './network.service';


@Injectable({
    providedIn: 'root',
  })
  export class TacheService implements OnInit {

    private isOnline : boolean = false ;


    constructor(private http : HttpClient , private networktService : NetworkService  ){

      this.networktService.online$.subscribe( isOnline => {
        this.isOnline = isOnline ;

        if(isOnline){
          this.http.get<void[]>('http://localhost:9002/synchro').subscribe();
        }
    });
    }

    ngOnInit(): void {

    }

    getAllTaches(): Observable<Tache[]>{
        return this.http.get<Tache[]>('http://localhost:9002/taches')
    }

    deleteTaches(id : number):  Observable<void> {


      var url ="" ;
      if(this.isOnline){

        url = `http://localhost:9002/synchro/taches/${id}` ;
      
        console.log(url)

      }else{

        url = `http://localhost:9002/taches/${id}` ;
      
        console.log(url)

      }


      return this.http.delete<void>(url);

      
    }


    addTaches( tache : Tache) : Observable<Tache> {

      if(this.isOnline){

        console.log("synchro")
        return this.http.post<Tache>("http://localhost:9002/synchro/taches", tache) ;

      }else{
        return this.http.post<Tache>("http://localhost:9002/taches", tache) ;
        console.log("non synchro")


      }

    }


  }