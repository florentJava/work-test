import { Injectable, OnInit } from '@angular/core';
import { Tache } from '../modele/tache.model';
import { TacheService } from './tache.service';
import { HttpClient } from '@angular/common/http';
import { Observable, map, switchMap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DataService implements OnInit {

    taches : Tache[] = [];


    constructor( private tachesService : TacheService){
        this.tachesService.getAllTaches().subscribe((tasks)=>{
            this.taches = tasks;

        });
    }


    ngOnInit(): void {
    }




}