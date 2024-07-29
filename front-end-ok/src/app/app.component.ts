import { Component, OnInit , Renderer2} from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { TacheListComponent } from './tache-list/tache-list.component';
import { AsyncPipe , CommonModule } from '@angular/common';

import { NetworkService } from './service/network.service';
import { Observable } from 'rxjs';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Tache } from './modele/tache.model';
import { DataService } from './service/data.service';
import { TacheService } from './service/tache.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet , TacheListComponent , AsyncPipe  , CommonModule , ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  

  tacheForm!: FormGroup;



  online$ : Observable<boolean> ;


  constructor(private formBuilder: FormBuilder, private netwokService  : NetworkService ,  private renderer: Renderer2 , private data : DataService , private tacheService : TacheService){
    this.online$ = this.netwokService.online$;
  }
  
  ngOnInit(): void {

    this.online$.subscribe(isOnline => {
      this.updateStatus(isOnline);
    });


    this.tacheForm = this.formBuilder.group(
      {
        nom: [null, [Validators.required]],
        desc: [null, [Validators.required]]
      },
      {
        updateOn: 'blur',
      });


  } 


  onSubmit() : void {

    if (this.tacheForm.valid){
      const tachesData = this.tacheForm.value;
      const date = new Date();
      const newTask = new Tache(0,tachesData.nom , tachesData.desc , date.toISOString());

      this.tacheForm.reset();


      this.tacheService.addTaches(newTask).subscribe( tache =>{
          this.data.taches.push(tache)

      })
    }
  }



  updateStatus(isOnline: boolean): void {
    const statusIndicator = this.renderer.selectRootElement('#status-indicator');
    const statusText = this.renderer.selectRootElement('#status-text');
    
    if (isOnline) {
      this.renderer.addClass(statusIndicator, 'online');
      this.renderer.removeClass(statusIndicator, 'offline');
      this.renderer.setProperty(statusText, 'textContent', 'En ligne');
    } else {
      this.renderer.addClass(statusIndicator, 'offline');
      this.renderer.removeClass(statusIndicator, 'online');
      this.renderer.setProperty(statusText, 'textContent', 'Hors ligne');
    }
  }
}
