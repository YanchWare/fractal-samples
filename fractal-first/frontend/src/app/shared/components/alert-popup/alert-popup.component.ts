import { Component, inject, input } from '@angular/core';
import { AlertPopupConfig, TIMER } from '../../models/alert-popup.model';
import { AlertPopupService } from '../../services/alert-popup.service';

/**
 * Component for displaying an alert popup.
 */
@Component({
  selector: 'app-alert-popup',
  standalone: true,
  imports: [],
  templateUrl: './alert-popup.component.html',
  styleUrl: './alert-popup.component.scss',
})
export class AlertPopupComponent {
  /**
   * The custom CSS class for the alert popup.
   */
  customClass: string = '';

  /**
   * The timer used to hide the alert popup after a certain duration.
   */
  private timer: ReturnType<typeof setTimeout> = setTimeout(() => {}, 0);

  /**
   * The configuration for the alert popup.
   */
  alertPopupConfig = input.required<AlertPopupConfig>();


  /**
   * Inject service for managing alert popups.
   */
  alertPopupService = inject(AlertPopupService);

  /**
   * Initializes the alert popup component and sets a timer to hide the popup.
   */
  constructor() {
    this.timer = setTimeout(() => {
      this.customClass = 'hide';
    }, TIMER);
  }

  /**
   * Resets the timer used to hide the alert popup.
   */
  resetTimer() {
    clearTimeout(this.timer);
  }
}
