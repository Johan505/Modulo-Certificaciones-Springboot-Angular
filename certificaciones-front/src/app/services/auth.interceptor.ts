import { SolicitudService } from 'src/app/services/solicitud.service';
import { LoginService } from './login.service';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(
    private loginService:LoginService,
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.loginService.getToken();

    if (token != null) {
        // Clona la solicitud y agrega el encabezado de autorización
        req = req.clone({
            setHeaders: { Authorization: `Bearer ${token}` }
        });
    }

    return next.handle(req);
}

}

export const authInterceptorProviders = [
  {
    provide : HTTP_INTERCEPTORS,
    useClass : AuthInterceptor,
    multi : true
  }
]
