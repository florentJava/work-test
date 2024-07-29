import { Component, OnInit } from '@angular/core';
import { TacheComponent } from '../tache/tache.component';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { TacheService } from '../service/tache.service';
import { Tache } from '../modele/tache.model';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { DataService } from '../service/data.service';


@Component({
  selector: 'app-tache-list',
  standalone: true,
  imports: [TacheComponent , CommonModule,HttpClientModule , AsyncPipe],
  templateUrl: './tache-list.component.html',
  styleUrl: './tache-list.component.scss',
  providers: []
})
export class TacheListComponent implements OnInit {
  

  constructor( private tacheService : TacheService ,public data : DataService ){}

  taches!: Tache[];
  taches$!: Observable<Tache[]>;
  


  ngOnInit(): void {
      this.tacheService.getAllTaches().subscribe((tasks)=>{
        this.taches = tasks;
      });

      console.log("taches")
      console.log(this.taches)

  }

}
