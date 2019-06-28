package com.cric.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.cric.model.CurrentMatch;


public interface IMatchRepository extends CrudRepository<CurrentMatch, Integer>{

	List<CurrentMatch> findByUserId(Integer userId);

	CurrentMatch findByCurrentMatchId(Integer userId);

	List<CurrentMatch> findByTeamNameLike(String search);
	
	List<CurrentMatch> findByTeamName(String teamName);

	Page<CurrentMatch> findAll(Pageable pageable);

}
