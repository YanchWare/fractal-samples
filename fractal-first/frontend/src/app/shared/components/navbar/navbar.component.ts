import { Component, input, output } from '@angular/core';
import { NavbarConfig } from '../../models/navbar.model';
import { ButtonComponent } from '../button/button.component';
import { ButtonConfig } from '../../models/button.model';

const COMPONENTS = [
  ButtonComponent,
];

/**
 * Represents the navigation bar component.
 */
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    ...COMPONENTS
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavBarComponent {

  /**
   * The configuration for the navigation bar.
   */
  navbarConfig = input.required<NavbarConfig>();

  /**
   * Event emitted when a button in the navigation bar is clicked.
   */
  onClickButton = output<ButtonConfig>();

}
