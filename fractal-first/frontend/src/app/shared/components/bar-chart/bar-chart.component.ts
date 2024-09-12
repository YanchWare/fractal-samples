import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';

@Component({
  selector: 'app-bar-chart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './bar-chart.component.html',
  styleUrl: './bar-chart.component.scss'
})
/**
 * Represents a bar chart component.
 */
export class BarChartComponent {

  /**
   * signal label input for the bar chart component.
   */
  label = input<string>();


  /**
   * signal input required value of the bar chart.
   */
  value = input.required<number>();
    
  /**
   * signal that Determines whether the bar chart should be displayed in reverse order.
   */
  isReverse = input<boolean>(false);

}
