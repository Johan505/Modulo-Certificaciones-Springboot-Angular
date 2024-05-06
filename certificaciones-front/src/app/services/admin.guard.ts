import { HttpClient } from '@angular/common/http';
import { LoginService } from './login.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private loginService:LoginService,private router:Router, private httpClient: HttpClient){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.loginService.isLoggedIn() && this.loginService.getUserRole() == 'ADMIN'){
      return true;
    }

    this.router.navigate(['login']);
    return false;
  }

  public getSolicitudes() {
    return this.httpClient.get<any>(`${baseUrl}/solicitudes/`);
  }


  public updateSolicitud(id: number, solicitudActualizada: any) {
    return this.httpClient.post<any>(`${baseUrl}/solicitudes/${id}`, solicitudActualizada);
  }

}
