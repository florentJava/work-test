import { Component, Input, OnInit } from '@angular/core';
import { Tache} from '../modele/tache.model';
import { TacheService } from '../service/tache.service';
import { DataService } from '../service/data.service';

@Component({
  selector: 'app-tache',
  standalone: true,
  imports: [],
  templateUrl: './tache.component.html',
  styleUrl: './tache.component.scss'
})
export class TacheComponent implements OnInit {

  @Input() tache! : Tache;

  constructor(private tacheService : TacheService , public data : DataService){}


  ngOnInit(): void {
  }


  deleteTache() : void{      

    this.tacheService.deleteTaches(this.tache.id).subscribe()
    this.data.taches =   this.data.taches.filter( item => item.id !== this.tache.id);
  }

}
