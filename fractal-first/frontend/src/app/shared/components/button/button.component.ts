import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ButtonConfig } from '../../models/button.model';

/**
 * Represents a custom button component.
 */
@Component({
  selector: 'app-button',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './button.component.html',
  styleUrl: './button.component.scss'
})
export class ButtonComponent {

  /**
   * The configuration for the button.
   */
  buttonConfig = input.required<ButtonConfig>();

  /**
   * Indicates whether the button is disabled.
   */
  isDisabled = input<boolean>(false);
}
