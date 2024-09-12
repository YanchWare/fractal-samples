import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { AlertPopupConfig, TIMER } from '../models/alert-popup.model';

@Injectable({
  providedIn: 'root',
})
export class AlertPopupService {
  /**
   * Timer used to control the visibility of the alert popup.
   */
  private timer: ReturnType<typeof setTimeout> = setTimeout(() => {}, 0);

  /**
   * Subject used to emit alert popup configurations.
   */
  alertPopupSubject: Subject<AlertPopupConfig> =
    new Subject<AlertPopupConfig>();

  /**
   * Shows the alert popup with the specified configuration.
   * @param alertPopupConfig The configuration for the alert popup.
   */
  show(alertPopupConfig: AlertPopupConfig) {
    this.alertPopupSubject.next({ ...alertPopupConfig, isVisible: true });
    this.timer = setTimeout(() => {
      this.hide();
    }, TIMER + 500);
  }

  /**
   * Hides the alert popup.
   */
  hide() {
    this.alertPopupSubject.next({
      ...({} as AlertPopupConfig),
      isVisible: false,
    });
    clearInterval(this.timer);
  }
}
