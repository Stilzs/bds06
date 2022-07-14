package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findReviewsMovie(Long id) throws ResourceNotFoundException {
		List<Review> list = repository.findReviewsMovie(id);
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Entity not found");
		}
		
		return list.stream().map(x -> new ReviewDTO(x, x.getUser())).collect(Collectors.toList());
	}
	
	
	
	
}
