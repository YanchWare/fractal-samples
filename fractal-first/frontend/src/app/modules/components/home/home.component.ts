import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { IMAGES } from '@src/app/constants/images';
import { ROUTE } from '@src/app/constants/route';
import { UserFacade } from '@src/app/core/store/user/user.facade';
import { NavBarComponent } from '@src/app/shared/components/navbar/navbar.component';
import { ButtonConfig } from '@src/app/shared/models/button.model';
import { NavbarConfig } from '@src/app/shared/models/navbar.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterOutlet, NavBarComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {


  /**
   * Array of button configurations for the navbar.
   * Each button configuration contains properties like classButtonType, typeButtonType, label, and customClass.
   */
  buttonsNavbar: ButtonConfig[] = [
    {
      id: 'homepage',
      classButtonType: 'btn-link',
      typeButtonType: 'button',
      label: 'Homepage',
      customClass: 'text-decoration-none text-white',
      routerLink: `/${ROUTE.DASHBOARD}`,
    },
    {
      id: 'about',
      classButtonType: 'btn-link',
      typeButtonType: 'button',
      label: 'Feel lucky!',
      customClass: 'text-decoration-none text-white',
      routerLink: `/${ROUTE.CITY}/${
        Math.floor(Math.random() * 10) + 1
      }`,
    },
  ];

  /**
   * Configuration for the logout button.
   */
  buttonLogOutConfig: ButtonConfig = {
    id: 'logout',
    classButtonType: 'btn-link',
    typeButtonType: 'button',
    label: 'Log out',
    customClass: 'text-decoration-none text-white',
  };

  /**
   * Configuration object for the navbar in the dashboard component.
   */
  navbarConfig: NavbarConfig = {
    imgLogo: IMAGES.YANCHWAREGO_MINI_LOGO,
    buttonsNavbarStart: this.buttonsNavbar,
    buttonsNavbarEnd: [this.buttonLogOutConfig],
  };

    /**
   * Inject the UserFacade Pattern.
   **/
    userFacade = inject(UserFacade);


  /**
   * Handles the click event of a button in the dashboard component.
   * @param button - The button configuration object.
   */
  onClickButton(button: ButtonConfig) {
    switch (button.id) {
      /**
       * Logs out the user through UserFacade Pattern.
       */
      case 'logout': {
        this.userFacade.logOut();
        break;
      }

      default: {
        break;
      }
    }
  }

}
