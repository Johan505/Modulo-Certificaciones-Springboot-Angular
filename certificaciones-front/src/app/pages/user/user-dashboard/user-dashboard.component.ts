import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import pdfMake from 'pdfmake/build/pdfMake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import {
  JsonResponse,
  SolicitudService,
} from 'src/app/services/solicitud.service';
import Swal from 'sweetalert2';
pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
})
export class UserDashboardComponent implements OnInit {
  constructor(
    private http: HttpClient,
    private solicitudService: SolicitudService
  ) {}

  dataUser: any;
  userSolicitudes: any;

  userDataLoaded = true; // Bandera para indicar si los datos del usuario se han cargado

  ngOnInit(): void {
    this.getUserData();
    this.getSolicitudUserId();
  }

  getSolicitudUserId(): void {
    if (this.dataUser) {
      this.solicitudService.getSolicitudbyID(this.dataUser.id).subscribe(
        (response) => {
          this.userSolicitudes = response;
        },
        (error) => {
          console.error('Error al enviar la solicitud:', error);
        }
      );
    }
  }

  getUserData() {
    UserService.getUser(this.http).subscribe(
      (response) => {
        this.dataUser = response; // Asignar los datos del usuario recibidos del servicio
        this.userDataLoaded = true; // Marcar que los datos del usuario están cargados
      },
      (error) => {
        console.error('Error al obtener usuario:', error);
      }
    );
  }

  // Método para enviar la solicitud
  enviarSolicitud() {
    if (this.dataUser) {
      this.solicitudService.añadirSolicitud(this.dataUser.id).subscribe(
        (response: JsonResponse) => {
          Swal.fire(
            `Se ha generado la solicitud`,
            'Estara en revision para generarte el paz y salvo',
            'success'
          );
          console.log('Solicitud enviada con éxito', response);
        },
        (error) => {
          Swal.fire(
            'Tiene una solicitud pendiente',
            'Estará en revision para generar el paz y salvo',
            'error'
          );
          console.error('Error al enviar la solicitud:', error);
        }
      );
    } else {
      Swal.fire(
        'tiene una solicitud pendiente',
        'Estara en revision para generar el paz y salvo uwu',
        'error'
      );
    }
  }

  createCertificado() {
    if (!this.userDataLoaded) {
      return; // Salir si los datos del usuario aún no se han cargado
    }

    const pdfDefinition: any = {
      content: [
        {
          text: 'LA SUSCRITA DIRECTORA DE GESTION HUMANA DE',
          style: 'header',
          alignment: 'center',
        },
        {
          text: 'LA EMPRESA',
          style: 'header',
          alignment: 'center',
        },
        {
          text: '\n\n\n',
        },
        {
          text: 'CERTIFICA',
          style: 'header',
          alignment: 'center',
        },
        {
          text: '\n\n\n',
        },
        {
          text: `Que él(a) señor(a) ${this.dataUser.nombre} ${
            this.dataUser.apellido
          }, identificado(a) con cédula de ciudadania No ${
            this.dataUser.documento
          } ha trabajado en nuestra empresa, desde ${
            this.dataUser.fechaInicio
          } hasta ${
            this.dataUser.fechaFin ? this.dataUser.fechaFin : 'Actualidad'
          }, desempeñando el cargo de ${
            this.dataUser.cargo
          } con un contrato a término ${
            this.dataUser.tipo_contrato
          } y devegando un salario mensual de $${this.dataUser.salario}`,
          alignment: 'justify',
        },
        {
          text: '\n\n',
        },
        {
          text: 'Cordialmente,',
        },
        {
          text: '\n\n',
        },
        {
          text: `Fecha de emisión: ${new Date().toLocaleDateString()}`,
          alignment: 'right',
        },
      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true,
          margin: [0, 0, 0, 10], // Margen inferior de 10 puntos
        },
      },
    };

    const pdf = pdfMake.createPdf(pdfDefinition);
    pdf.open();
  }

  createPazySalvo() {
    if (!this.userDataLoaded) {
      return; // Salir si los datos del usuario aún no se han cargado
    }

    const pdfDefinition: any = {
      content: [
        {
          text: 'CERTIFICADO DE PAZ Y SALVO LABORAL',
          style: 'header',
          alignment: 'center',
        },
        {
          text: '\n\n\n',
        },
        {
          text: '\n\n\n',
        },
        {
          text: 'En la ciudad de Bogota D.C, a los _________ dias del mes de __________ del año 2024, el contrato laboral celebrado entre el empleador __________________________________, y trabajador ________________________________________________________ mayor de edad, identificado con la CC ______________________ de ___________________________, se da por terminado.',
          alignment: 'justify',
        },
        {
          text: '\n\n',
        },
        {
          text: 'En simbolo de asentamiento con la liquidación del contrato laboral, el trabajador suscribe el presente documento, y sostiene que ha recibido a satisfacción social el total de sus prestaciones.',
        },
        {
          text: '\n\n',
        },
        {
          text: '\n\n',
        },
        {
          text: '\n\n',
        },
        {
          text: 'TRABAJADOR',
        },
        {
          text: '\n\n',
        },
        {
          text: '\n\n',
        },
        {
          text: '_____________________(Firma)',
        },
        {
          text: '_____________________(C.C)',
        },
        {
          text: `Fecha de emisión: ${new Date().toLocaleDateString()}`,
          alignment: 'right',
        },
      ],
      styles: {
        header: {
          fontSize: 18,
          bold: true,
          margin: [0, 0, 0, 10], // Margen inferior de 10 puntos
        },
      },
    };

    const pdf = pdfMake.createPdf(pdfDefinition);
    pdf.open();
  }

}
