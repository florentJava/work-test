import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NetworkService {
  // BehaviorSubject qui maintient l'état actuel de la connexion, initialisé avec navigator.onLine
  private onlineSubject: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(navigator.onLine);
  
  // Observable exposé à l'extérieur pour suivre l'état de la connexion
  public online$: Observable<boolean> = this.onlineSubject.asObservable();


  constructor() {
    // Ajout d'écouteurs d'événements pour les changements de connectivité du navigateur
    window.addEventListener('online', () => this.updateOnlineStatus(true));
    window.addEventListener('offline', () => this.updateOnlineStatus(false));
  }

  // Méthode privée pour mettre à jour l'état de la connexion
  private updateOnlineStatus(isOnline: boolean): void {
    this.onlineSubject.next(isOnline);
  }
}
