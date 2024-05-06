import { Component, OnInit } from '@angular/core';
import { AdminGuard } from 'src/app/services/admin.guard';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private AdminGuard: AdminGuard) { }

  ngOnInit(): void {
    this.getSolicitudes();
  }

  totalsolicitudes: any;

  getSolicitudes() {
    this.AdminGuard.getSolicitudes().subscribe(
      (response) => {
        this.totalsolicitudes=response
        console.log(this.totalsolicitudes);
      },
      (error) => {
        console.error('Error al obtener usuario:', error);
      }
    );
  }

  cambiarEstado(id: number, solicitudActualizada: any) {
    this.AdminGuard.updateSolicitud(id, solicitudActualizada).subscribe(
      (response) => {
        console.log('Solicitud actualizada exitosamente:', response);
        window.location.reload();
      },
      (error) => {
        console.error('Error al actualizar solicitud:', error);
      }
    );
  }
}
