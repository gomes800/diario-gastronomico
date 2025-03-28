import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Restaurant } from '../../models/restaurant.model';
import { RestaurantService } from '../../services/restaurant.service';

@Component({
  selector: 'app-restaurants',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule],
  templateUrl: './restaurants.component.html',
  styleUrl: './restaurants.component.css'
})
export class RestaurantsComponent implements OnInit {
  restaurants: Restaurant[] = [];

  constructor(private restaurantService: RestaurantService) { }

  ngOnInit(): void {
    this.restaurantService.getRestaurants().subscribe({
      next: (data) => (this.restaurants = data),
      error: (err) => console.error('Erro ao buscar restaurantes', err),
    })
  }
}
