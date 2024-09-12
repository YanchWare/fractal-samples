import { Component, input } from '@angular/core';
import { CardConfig } from '../../models/card.model';

/**
 * Represents a card component.
 */
@Component({
  selector: 'app-card',
  standalone: true,
  imports: [],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  /**
   * Input with card config object.
   */
  cardModel = input<CardConfig>();
}
