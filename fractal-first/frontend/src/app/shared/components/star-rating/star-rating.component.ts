import { Component, input } from '@angular/core';

/**
 * Represents a star rating component.
 */
@Component({
  selector: 'app-star-rating',
  standalone: true,
  imports: [],
  templateUrl: './star-rating.component.html',
  styleUrl: './star-rating.component.scss'
})
export class StarRatingComponent {
  /**
   * The rating value of the star rating component.
   */
  rating = input.required<number>();
}
