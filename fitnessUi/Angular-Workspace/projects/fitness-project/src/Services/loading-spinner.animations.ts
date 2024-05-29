
import { trigger, state, style, animate, transition } from '@angular/animations';

export const LoadingSpinnerAnimation = trigger('loadingSpinner', [
  state('void', style({ opacity: 0 })), // Initial state
  state('*', style({ opacity: 1 })), // Final state

  transition(':enter, :leave', [
    animate('300ms ease-in-out') // Animation duration and easing
  ])
]);

